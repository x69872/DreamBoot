package com.daydream.boot.dreamboot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Product bean
 *
 * @author HelloWood
 * @date 2017-07-11 11:09
 * @Email hellowoodes@gmail.com
 */
@Data
public class Product implements Serializable
{
    private static final long serialVersionUID = 1435515995276255188L;

    private long id;
    private String name;
    private long price;


}