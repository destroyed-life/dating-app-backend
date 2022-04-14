package com.destroyedlife.dateingappbackend.controller;

import com.destroyedlife.dateingappbackend.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    private TestService testService;

    public DefaultController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/")
    public String index()
    {
        return this.testService.getTest();
    }
}
