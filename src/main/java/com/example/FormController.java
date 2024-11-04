/*
 * Copyright 2024 The Kingsway Digital Company Limited. All rights reserved.
 */
package com.example;

import io.micronaut.http.annotation.*;
import io.micronaut.views.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tony Field
 * @since 2024-11-04 07:21
 */
@Controller
public class FormController {

    @Get
    public ModelAndView<Map<String,String>> form() {
        var model = new HashMap<String,String>();
        model.put("title", "Multipart Form Test");
       return new ModelAndView<>("multipart-form.ftlh", model);
    }

    @Post("/post1")
    @Consumes("multipart/form-data")
    public ModelAndView<Map<String,String>> post1(@Part("text1") String text1, @Part("text2") String text2, @Part("text3") String text3) {
        var model = new HashMap<String,String>();
        model.put("title", "Multipart Form Post 1");
        model.put("msg", "Decoded as multipart strings");
        model.put("text1", text1);
        model.put("text2", text2);
        model.put("text3", text3);
        return new ModelAndView<>("multipart-form-result.ftlh", model);
    }

    @Post("/post2")
    @Consumes("multipart/form-data")
    public ModelAndView<Map<String,String>> post2(@Part("text1") byte[] text1bytes, @Part("text2") byte[] text2bytes, @Part("text3") byte[] text3bytes) {
        String text1 = new String(text1bytes, StandardCharsets.UTF_8);
        String text2 = new String(text2bytes, StandardCharsets.UTF_8);
        String text3 = new String(text3bytes, StandardCharsets.UTF_8);

        var model = new HashMap<String,String>();
        model.put("title", "Multipart Form Post 2");
        model.put("msg", "Decoded as byte arrays then converted back to strings via UTF-8");
        model.put("text1", text1);
        model.put("text2", text2);
        model.put("text3", text3);
        return new ModelAndView<>("multipart-form-result.ftlh", model);
    }

}
