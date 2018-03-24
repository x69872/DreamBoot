package com.daydream.boot.Dreamboot.config;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.util.DruidPasswordCallback;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * 数据库密码回调解密 （未用到）
 */
@SuppressWarnings("serial")
public class DBPasswordCallback extends DruidPasswordCallback
{

    /**
     * 上述生成的公钥
     */
    public static final String PUBLIC_KEY_STRING = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYYMXpeG9v1yCzq8+9dvPgbknfCv34N1vyieshBjLyI3x/DOZVBSBAzeWWx920b0fp5rXQnswAi7wxyFi8OJuLQoPx3PVKjhevJsnFEylDoRJHGoS0tJR8K68VTC+5/1c9TIEu9ckRE6/Q/i8qLVLVT0EyEchBc21XQ6cCRibbRwIDAQAB";

    @Override
//    @Bean
    public void setProperties(Properties properties)
    {
        super.setProperties(properties);
        String pwd = properties.getProperty("spring.datasource.druid.password");
        if (StringUtils.isNotBlank(pwd))
        {
            System.out.println("");
            try
            {
                //这里的password是将jdbc.properties配置得到的密码进行解密之后的值
                //所以这里的代码是将密码进行解密
                //TODO 将pwd进行解密;
                String password = ConfigTools.decrypt(PUBLIC_KEY_STRING, pwd);
                setPassword(password.toCharArray());
            }
            catch (Exception e)
            {
                setPassword(pwd.toCharArray());

            }
        }
    }

    // 请使用该方法加密后，把密文写入classpath:/config/jdbc.properties
    public static void main(String[] args)
    {
//        System.out.println(SecurityUtil.encryptDes("", key));
}
}