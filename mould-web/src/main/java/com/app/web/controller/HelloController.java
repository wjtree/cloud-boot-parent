package com.app.web.controller;

import com.app.web.consumer.HelloConsumer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
public class HelloController {
//    @Autowired
//    LoadBalancerClient loadBalancerClient;
//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    HelloConsumer helloConsumer;

    @GetMapping(value = "hello")
    public String hello() {
        // 不使用 ribbon 调用
//        ServiceInstance instance = loadBalancerClient.choose("mould-server");
//        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/hello";
//
//        log.info("服务端接口地址：{}", url);
//
//        return restTemplate.getForObject(url, String.class);

        // 使用 ribbon 调用
//        return restTemplate.getForObject("http://mould-server/hello", String.class);

        // 使用 feign 调用
        return helloConsumer.hello();
    }

    /**
     * Spring Cloud Feign 文件上传实现
     *
     * @param file
     * @return
     */
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestPart("file") MultipartFile file) {
        return helloConsumer.upload(file);
    }
}
