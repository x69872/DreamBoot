package com.daydream.boot.Dreamboot.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @author GaoJian
 */
@Data
@ConfigurationProperties(prefix = "my")
@Component
public class DemoConfig
{
    private List<String> servers = new ArrayList<>();

    public static void main(String[] args)
    {
        System.out.println(new DemoConfig().getServers());
    }
}

