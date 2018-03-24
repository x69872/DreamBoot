package com.daydream.boot.Dreamboot.controller;

import com.daydream.boot.Dreamboot.entity.Product;
import com.daydream.boot.Dreamboot.service.ProductService;
import com.daydream.boot.Dreamboot.utils.CommonResponse;
import com.daydream.boot.Dreamboot.utils.ResponseUtil;
import com.daydream.boot.Dreamboot.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Product controller
 *
 * @author HelloWood
 * @date 2017-07-11 11:38
 * @Email hellowoodes@gmail.com
 */

@RestController
@RequestMapping("/product")
public class ProductController
{

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public CommonResponse getProduct(@PathVariable("id") Long productId) throws ServiceException
    {
        return ResponseUtil.generateResponse(productService.select(productId));
    }

    @GetMapping
    public CommonResponse getAllProduct()
    {
        return ResponseUtil.generateResponse(productService.getAllProduct());
    }

    @PutMapping("/{id}")
    public CommonResponse updateProduct(@PathVariable("id") Long productId, @RequestBody Product newProduct) throws ServiceException
    {
        return ResponseUtil.generateResponse(productService.update(productId, newProduct));
    }

    @DeleteMapping("/{id}")
    public CommonResponse deleteProduct(@PathVariable("id") long productId) throws ServiceException
    {
        return ResponseUtil.generateResponse(productService.delete(productId));
    }

    @PostMapping
    public CommonResponse addProduct(@RequestBody Product newProduct) throws ServiceException
    {
        return ResponseUtil.generateResponse(productService.add(newProduct));
    }
}