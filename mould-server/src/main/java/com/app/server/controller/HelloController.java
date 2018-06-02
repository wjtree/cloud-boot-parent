package com.app.server.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class HelloController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("hello")
    public String hello() {
        String services = "Services: " + discoveryClient.getServices();

        if (log.isInfoEnabled())
            log.info(services);

        return services;
    }
}
