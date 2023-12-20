package com.hub.toy.controller;

import com.hub.toy.data.dto.response.ShortUrlResponseDto;
import com.hub.toy.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/short-url")
public class ShortUrlController {
    private final Logger log = LoggerFactory.getLogger(ShortUrlController.class);
    final String  SUCCESS = "SUCCESS";
    final String FAIL = "FAIL";

    // 생성자를 final로 만들거면 RequiredArgsConstructor를 사용
    private final ShortUrlService shortUrlService;

    @Value("${hub.toy.project.short.url.id}")
    private String CLIENT_ID;

    @Value("${hub.toy.project.short.url.secret}")
    private String CLIENT_SECRET;

    // ShortUrl 생성 API
    @PostMapping("")
    public ShortUrlResponseDto generateShortUrl(String originalUrl) {
        log.info("[generateShortUrl] perform API");

        return shortUrlService.generateShortUrl(CLIENT_ID, CLIENT_SECRET, originalUrl);
    }

    // ShortUrl 조회 API
    // /를 넣고 안넣고의 차이가 있다...
    @GetMapping("")
    public ShortUrlResponseDto getShortUrl(String originalUrl) {

        ShortUrlResponseDto shortUrlResponseDto = shortUrlService.getShortUrl(CLIENT_ID, CLIENT_SECRET, originalUrl);

        return shortUrlResponseDto;
    }

    // ShortUrl 수정 API
    @PutMapping("")
    public ShortUrlResponseDto updateShortUrl(String originalUrl) { return null; }

    // ShortUrl 삭제 API
    @DeleteMapping("/")
    public ResponseEntity<String> deleteShortUrl(String url) {
        try {
            log.info("url@@@={}", url);
            shortUrlService.deleteShortUrl(url);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(SUCCESS);
    }


}
