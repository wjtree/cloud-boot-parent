package com.app.server.provider;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
public class HelloProvider {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${info.profile}")
    private String profile = "World";

    @GetMapping("hello")
    public String hello() {
        String services = StringUtils.join(discoveryClient.getServices(), ",");

        log.info("服务节点列表:{}，参数：{}", services, profile);

        return services;
    }

    /**
     * Spring Cloud Feign 文件上传实现
     *
     * @param file
     * @return
     */
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestPart("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();

        log.info("上传文件名：{}", fileName);

        return fileName;
    }
}
