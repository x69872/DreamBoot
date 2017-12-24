package com.daydream.boot.Dreamboot;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author GaoJian
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.daydream.boot.Dreamboot.mapper")
public class DreambootApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DreambootApplication.class, args);
        log.info("=====================SpringBoot Running Started===================");

    }
}
