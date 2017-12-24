package com.daydream.boot.Dreamboot.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "users")
public class User
{

    private String name;

    public static void main(String[] args)
    {
        System.out.println(new User().getName());
    }
}