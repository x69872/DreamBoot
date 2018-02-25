package com.daydream.boot.Dreamboot;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

public class ConfigToolsDemo
{

    //上述生成的私钥
    private static final String PRIVATE_KEY_STRING = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAL1mXVDmWo8Pr/IBHShN82UKfnVzTj2m/RY0/nAkmAMGMl5KcacQ/4Z2rYW+0tk1VcIZnf/qqIarZnTVK3TbXgomnSNM9HsW0mi5wd99J7o49abrxlxQ91F+1VQUJN+36zYsutm3Y+amhChOrl/FlMHlJFwF2lpSJGCbW17kAMADAgMBAAECgYBsyX4x+I3tfDo4S9F+k/+IetWWCqvXF7Jo7flzWmGB4y5NIH9VUZsjlckneCrNnifq/CHXm+Y+q/aGhuaWEWZaeXeO3UqR+apaqqrd3ybKvj1zh6iW118MgzzpYNqfq9t4b29ndzU3HlZenBQYNEW8eNXL1zKs0fXLY6TcJqmA+QJBAPhZMHiiZqgY9kkEFXFStPdM1CXNeUVkvbUgQWUAekwTOL+DVQohRYKYFilpWVTfWYFafs1Ddo91BUIl6/vL1K0CQQDDPDo7NneO82oKlXwDtSA1I1OV1DpAU5wvU+ncZqpujKf/F/vjtYhM33L27nMsuNuD+X3sI4yT25YfzVZ7tc1vAkBpaDGSghtgLXga9YjEWH5GmG1SvitCsMd0IQSggdtmfqIPZwapDgYar8J4QSaRR1sN9ALtzDx+FwTsVnwamyDhAkBLQjk0QB/AVCapY8xFZZm/whljZUb7Kv/G4rMAifbi3IeZ9vXbZkvfTjdz5qExOSUtb4xR7bYv/PKW2cGfuIozAkA/6nMr3cZQe3o7iW/yDv12+F70l7yXnf6GIL/2pFtLPT64rbwC64/bdnRn89/QfGxZ0IMk12/4o1G28QClfqH5";

    @Test
    public void demo() throws Exception
    {
        //密码明文，也就是数据库的密码
        String plainText = "Gj#1397539";
        System.out.printf(ConfigTools.encrypt(PRIVATE_KEY_STRING, plainText));
    }
}