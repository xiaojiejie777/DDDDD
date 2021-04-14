package com.pdd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 */
@RestController
public class TestController {

    @GetMapping("/test1")
    public String test1(){
        return "test1";
    }

    @GetMapping("/employee/basic/test2")
    public String test2(){
        return "/employee/basic/test2";
    }

    @GetMapping("/employee/advanced/test3")
    public String test3(){
        return "/employee/advanced/test3";
    }
}
