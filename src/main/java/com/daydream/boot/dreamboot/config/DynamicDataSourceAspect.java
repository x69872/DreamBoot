package com.daydream.boot.dreamboot.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect
{

    private final String[] QUERY_PREFIX = {"select"};

    @Pointcut("execution( * com.daydream.boot.dreamboot.dao.*.*(..))")
    public void daoAspect()
    {
    }

    @Before("daoAspect()")
    public void switchDataSource(JoinPoint point)
    {
        Boolean isQueryMethod = isQueryMethod(point.getSignature().getName());
        if (isQueryMethod)
        {
            DynamicDataSourceContextHolder.useSlaveDataSource();
            log.info("Switch DataSource to [{}] in Method [{}]", DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        }
    }

    @After("daoAspect())")
    public void restoreDataSource(JoinPoint point)
    {
        DynamicDataSourceContextHolder.clearDataSourceKey();
        log.info("Restore DataSource to [{}] in Method [{}]", DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
    }

    private Boolean isQueryMethod(String methodName)
    {
        for (String prefix : QUERY_PREFIX)
        {
            if (methodName.startsWith(prefix))
            {
                return true;
            }
        }
        return false;
    }

}