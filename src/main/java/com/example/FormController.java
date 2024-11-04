/*
 * Copyright 2024 The Kingsway Digital Company Limited. All rights reserved.
 */
package com.example;

import io.micronaut.http.annotation.*;
import io.micronaut.views.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tony Field
 * @since 2024-11-04 07:21
 */
@Controller
public class FormController {

    @Get
    public ModelAndView<Map<String, String>> form() {
        var model = new HashMap<String, String>();
        model.put("title", "Multipart Form Test");
        return new ModelAndView<>("multipart-form.ftlh", model);
    }

    @Post("/post1")
    @Consumes("multipart/form-data")
    public ModelAndView<Map<String, String>> post1(@Part("text1") String text1, @Part("text2") String text2, @Part("text3") String text3) {
        var model = new HashMap<String, String>();
        model.put("title", "Multipart Form Post 1");
        model.put("msg", "Decoded as multipart strings");
        model.put("text1", text1);
        model.put("text2", text2);
        model.put("text3", text3);
        return new ModelAndView<>("multipart-form-result.ftlh", model);
    }

    @Post("/post2")
    @Consumes("multipart/form-data")
    public ModelAndView<Map<String, String>> post2(@Part("text1") byte[] text1bytes, @Part("text2") byte[] text2bytes, @Part("text3") byte[] text3bytes) {
        String text1 = new String(text1bytes, StandardCharsets.UTF_8);
        String text2 = new String(text2bytes, StandardCharsets.UTF_8);
        String text3 = new String(text3bytes, StandardCharsets.UTF_8);

        var model = new HashMap<String, String>();
        model.put("title", "Multipart Form Post 2");
        model.put("msg", "Decoded as byte arrays then converted back to strings via UTF-8");
        model.put("text1", text1);
        model.put("text2", text2);
        model.put("text3", text3);
        return new ModelAndView<>("multipart-form-result.ftlh", model);
    }

    @Post("/post3")
    @Consumes("multipart/form-data")
    public ModelAndView<Map<String, String>> post3(@Body Map<String, List> form) {
        String text1 = (String) form.get("text1").getFirst();
        String text2 = (String) form.get("text2").getFirst();
        String text3 = (String) form.get("text3").getFirst();

        var model = new HashMap<String, String>();
        model.put("title", "Multipart Form Post 3");
        model.put("msg", "Using @Body and extracting strings");
        model.put("text1", text1);
        model.put("text2", text2);
        model.put("text3", text3);
        return new ModelAndView<>("multipart-form-result.ftlh", model);
    }

    @Post("/post4")
    @Consumes("multipart/form-data")
    public ModelAndView<Map<String, String>> post4(@Body Map<String, List> form) {
        String text1 = new String((byte[]) form.get("text1").getFirst());
        String text2 = new String((byte[]) form.get("text2").getFirst());
        String text3 = new String((byte[]) form.get("text3").getFirst());

        var model = new HashMap<String, String>();
        model.put("title", "Multipart Form Post 4");
        model.put("msg", "Using @Body and extracting bytes");
        model.put("text1", text1);
        model.put("text2", text2);
        model.put("text3", text3);
        return new ModelAndView<>("multipart-form-result.ftlh", model);
    }

    @Post("/post5")
    @Consumes("multipart/form-data")
    public ModelAndView<Map<String, String>> post5(@Body Map<String, List> form) {
        String text1 = extractStringForPost5(form.get("text1").getFirst());
        String text2 = extractStringForPost5(form.get("text2").getFirst());
        String text3 = extractStringForPost5(form.get("text3").getFirst());

        var model = new HashMap<String, String>();
        model.put("title", "Multipart Form Post 5");
        model.put("msg", "Using @Body and fixing extracted bytes");
        model.put("text1", text1);
        model.put("text2", text2);
        model.put("text3", text3);
        return new ModelAndView<>("multipart-form-result.ftlh", model);
    }

    private String extractStringForPost5(Object o) {
        String badString = (String) o;
        byte[] bytes = badString.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
