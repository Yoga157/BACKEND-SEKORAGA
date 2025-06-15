package com.skrg.sekoraga;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class WelcomeController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/welcome")
    public Map<String, Object> welcome() {
        return Map.of(
                "status", 200,
                "message", "Success",
                "data", "Hello, welcome to " + applicationName);
    }

    @GetMapping("/welcome/{name}")
    public Map<String, Object> welcome(@PathVariable String name) {
        return Map.of(
                "status", 200,
                "message", "Success",
                "data", "Hello, welcome " + name);
    }
}
