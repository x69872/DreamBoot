package com.daydream.boot.Dreamboot.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;


/**
 * JSON data util, for parse and generate JSON data
 *
 * @author HelloWood
 * @date 2017-07-11 16:11
 * @Email hellowoodes@gmail.com
 */

@Slf4j
public class JSONUtil {


    /**
     * Transfer object to JSON string
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        String result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //set config of JSON
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);// can use single quote
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);//allow unquoted field names
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));//set date format

        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Generate JSON String error!" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}