package com.test.tddtest.controller;

import com.test.tddtest.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/post-api")
@RestController
public class PostController {

    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(
                map -> {
                    sb.append(map.getKey() + " : " + map.getValue() +"\n");
                }
        );

        return sb.toString();
    }

    @PostMapping("/member2")
    public String postMemberDto(@RequestBody MemberDto memberDTO) {
        return memberDTO.toString();
    }

}
