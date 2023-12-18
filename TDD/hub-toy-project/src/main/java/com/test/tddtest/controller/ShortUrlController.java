package com.test.tddtest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/short-url")
public class ShortUrlController {
    private final Logger log = LoggerFactory.getLogger(ShortUrlController.class);

    @Value("${hub.toy.project.short.url.id}")
    private String CLIENT_ID;

    @Value("${hub.toy.project.short.url.secret}")
    private String CLIENT_SECRET;




}
