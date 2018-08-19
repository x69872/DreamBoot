package com.daydream.boot.dreamboot.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author GaoJian
 * 动态XA多数据源配置类
 */
@Configuration
@Slf4j
public class DynamicDataSourceConfiguration {
    @Autowired
    private DruidProperties properties;

    private DataSource castXADatasource(String uniqueResourceName, Environment env, String prifix) {

        AtomikosDataSourceBean atomikosDataSource = new AtomikosDataSourceBean();
        atomikosDataSource.setUniqueResourceName(uniqueResourceName);
        atomikosDataSource.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        atomikosDataSource.setTestQuery("select 1");
        atomikosDataSource.setPoolSize(5);
        atomikosDataSource.setMaxPoolSize(25);
        atomikosDataSource.setMaxLifetime(20000);
        atomikosDataSource.setXaProperties(build(env, prifix));
        return atomikosDataSource;
    }

    /**
     * master DataSource
     *
     * @return data source
     * <p>
     * 注解用于标识默认使用的 DataSource Bean，因为有5个 DataSource Bean，该注解可用于 master
     * 或 slave DataSource Bean, 但不能用于 dynamicDataSource Bean, 否则会产生循环调用
     * 注解用于从 application.properties 文件中读取配置，为 Bean 设置属性
     */
    @Bean("master")
    @Primary
    public DataSource master(Environment env) {

        return castXADatasource("master", env, "spring.datasource.druid.master.");
    }

    /**
     * Slave alpha data source.
     *
     * @return the data source
     */
    @Bean("slaveAlpha")
    public DataSource slaveAlpha(Environment env) {
        return castXADatasource("slaveAlpha", env, "spring.datasource.druid.slave-alpha.");
    }

    /**
     * Slave beta data source.
     *
     * @return the data source
     */
    @Bean("slaveBeta")
    public DataSource slaveBeta(Environment env) {
        return castXADatasource("slaveBeta", env, "spring.datasource.druid.slave-beta.");
    }

    /**
     * Slave gamma data source.
     *
     * @return the data source
     */
    @Bean("slaveGamma")
    public DataSource slaveGamma(Environment env) {
        return castXADatasource("slaveGamma", env, "spring.datasource.druid.slave-gamma.");
    }

    /**
     * 构建datasource属性
     *
     * @param prefix
     * @return
     */
    private Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driver-class-name", ""));
        prop.put("initialSize", properties.getInitialSize());
        prop.put("maxActive", properties.getMaxActive());
        prop.put("minIdle", properties.getMinIdle());
        prop.put("maxWait", properties.getMaxWait());
        prop.put("poolPreparedStatements", properties.getPoolPreparedStatements());
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                properties.getMaxPoolPreparedStatementPerConnectionSize());
        prop.put("validationQuery", properties.getValidationQuery());
        prop.put("testOnBorrow", properties.getTestOnBorrow());
        prop.put("testOnReturn", properties.getTestOnReturn());
        prop.put("testWhileIdle", properties.getTestWhileIdle());
        prop.put("timeBetweenEvictionRunsMillis",
                properties.getTimeBetweenEvictionRunsMillis());
        prop.put("minEvictableIdleTimeMillis", properties.getMinEvictableIdleTimeMillis());
        prop.put("filters", properties.getFilters());
        log.info("#########prop###########:{}",prop);
        return prop;
    }

    /**
     * Dynamic data source.
     *
     * @return the data source
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("master") DataSource master, @Qualifier("slaveAlpha") DataSource slaveAlpha, @Qualifier("slaveBeta") DataSource slaveBeta, @Qualifier("slaveGamma") DataSource slaveGamma) {

        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put(DataSourceConsts.master.name(), master);
        dataSourceMap.put(DataSourceConsts.slaveAlpha.name(), slaveAlpha);
        dataSourceMap.put(DataSourceConsts.slaveBeta.name(), slaveBeta);
        dataSourceMap.put(DataSourceConsts.slaveGamma.name(), slaveGamma);

        // 将 master 数据源作为默认指定的数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(master);
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());

        // 将 Slave 数据源的 key 放在集合中，用于轮循
        log.info("将Slave数据源的 key 放在集合中，用于轮循...");
        DynamicDataSourceContextHolder.slaveDataSourceKeys.addAll(dataSourceMap.keySet());
        DynamicDataSourceContextHolder.slaveDataSourceKeys.remove(DataSourceConsts.master.name());
        return dynamicRoutingDataSource;
    }

    /**
     * 配置 SqlSessionFactoryBean
     *
     * @return the sql session factory bean
     * <p>
     * 在这里是为了将 MyBatis 的 mapper 位置和持久层接口的别名设置到
     * Bean 的属性中，如果没有使用 *.xml 则可以不用该配置，否则将会产生 invalid bond statement 异常
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        return sqlSessionFactoryBean;
    }

    /**
     * 注入 DataSourceTransactionManager 用于事务管理
     */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}