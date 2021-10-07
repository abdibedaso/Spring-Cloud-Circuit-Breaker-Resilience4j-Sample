package com.geekcolab.development.servicetwoapplication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceTwoController {
    @RequestMapping("/text")
    public String getText() {
        return "World";
    }
}
