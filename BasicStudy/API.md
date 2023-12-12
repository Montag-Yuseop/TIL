# Controller

## Get Method

api/v1은 application.yml에 입력해두었다.

```yml
server:
  servlet:
    context-path: /api/v1
```

```java
package com.test.tddtest.controller;

import com.test.tddtest.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/get-api")
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    // {variable}에 넣은 값을 출력해준다
    // http://localhost:8080/api/v1/get-api/variable1/hello -> hello 출력
    @GetMapping("/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        return variable;
    }

    // String 파라미터의 값이 {}에 있는 객체와 이름이 다를 경우, PathVariable에 값을 명시해준다
    // http://localhost:8080/api/v1/get-api/variable2/hello -> hello 출력
    @GetMapping("/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

    // ?뒤에 쿼리문 뒤의 값이 출력된다.
    //http://localhost:8080/api/v1/get-api/request1?name=hello&age=20&email=hello@hello.com
    @GetMapping("/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String age,
            @RequestParam String email
    ) {
        return "name: " + name + "\n" + "age: " + age + "\n" + "email: " + email;
    }

    // 어떤 값이 들어올 지 모를 때 Map을 사용
    //http://localhost:8080/api/v1/get-api/request1?name=hello&age=20&email=hello@hello.com
    @GetMapping("/request2")
    public String getRequestParam2(
            @RequestParam Map<String, String> params
            ) {
        StringBuilder sb = new StringBuilder();

        params.entrySet().forEach(
                map -> {
                    sb.append(map.getKey() + " : " + map.getValue() +"\n");
                }
        );

        return sb.toString();
    }

    // DTO를 받아서 사용
    // http://localhost:8080/api/v1/get-api/request3?name=hello&email=hello.hello&organization=hello.org
    @GetMapping("/request3")
    public String getRequestParam3(MemberDTO memberDto) {

        return memberDto.toString();
    }

}

```

## Post Method

```java
package com.test.tddtest.controller;

import com.test.tddtest.dto.MemberDTO;
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
    public String postMemberDto(@RequestBody MemberDTO memberDTO) {
        return memberDTO.toString();
    }

}
```

Put, Patch, Delete도 유사하다
