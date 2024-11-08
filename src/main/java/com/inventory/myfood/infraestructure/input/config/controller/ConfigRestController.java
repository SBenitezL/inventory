package com.inventory.myfood.infraestructure.input.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("api/v1/config")
@NoArgsConstructor
@RefreshScope
public class ConfigRestController {
    @Value("${app.testProp}")
    private String testProp;

    @GetMapping("test-prop")
    public String getTestProp() {
        return testProp;
    }

}
