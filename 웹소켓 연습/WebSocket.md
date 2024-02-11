# 웹 소켓 연습

## gradle에 종속성 추가(시큐리티를 쓰기 때문에)

`implementation 'org.springframework.security:spring-security-messaging'`

## Websocket config 추가

``` java
package thx.dragon.thxdragon.global.config.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/message/test")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }

}

```

## WebsocketHandler 추가

``` java
package thx.dragon.thxdragon.global.config.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class WebsocketHandler extends TextWebSocketHandler {

    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS =
            new ConcurrentHashMap<String, WebSocketSession>();

    // websocket 시작
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("websocket connect : session id = {}", session.getId());
        CLIENTS.put(session.getId(), session);
    }

    // websocket 종료시
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("websocket : session id = {}", session.getId());
        CLIENTS.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String id = session.getId();
        log.info("{} : websocket message = {}", id, message);
        CLIENTS.entrySet().forEach(
                arg -> {
                    if(!arg.getKey().equals(id)) {
                        try {
                            arg.getValue().sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}
```

## SecurityWebsocketConfig 추가

``` java

package thx.dragon.thxdragon.global.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;

@EnableWebSocketSecurity
@Configuration
public class SecurityWebSocketConfig {

    @Bean
    AuthorizationManager<Message<?>> messageAuthorizationManager(
            MessageMatcherDelegatingAuthorizationManager.Builder messages) {
        messages.simpDestMatchers("/user/queue/errors").permitAll()
                .simpDestMatchers("/admin/**").hasRole("JOIN")
                .anyMessage().authenticated();
        return messages.build();
    }

    @Bean("csrfChannelInterceptor") // for disable csrf
    public ChannelInterceptor csrfChannelInterceptor() {
        return new ChannelInterceptor() {
        };
    }
}

```
이제 안에 내용들을 하나씩 수정해보자

현재 문제... STOMP는 테스트를 해볼 수가 없다..

https://jxy.me/websocket-debug-tool/

이거 찾아냈다

https://eoneunal.tistory.com/17



## 현수가 만든 프론트엔드

### MessageReop.js

```javascript
import { fetchMessages, postMessage } from "./request";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

let stompClient = null;

export const connect = (onMessageReceived, onConnect, onDisconnect) => {
  const socket = new SockJS("http://localhost:8080/api/v1/ws");
  stompClient = Stomp.over(socket);

  stompClient.connect(
    {},
    (frame) => {
      onConnect();
      stompClient.subscribe("/topic/messages", (message) => {
        onMessageReceived(JSON.parse(message.body));
      });
    },
    (error) => {
      onDisconnect();
      console.log(error);
    }
  );
};

export const send = (message) => {
  if (stompClient && stompClient.connected) {
    const messageBody = JSON.stringify({ content: message });
    stompClient.send("/app/send", {}, messageBody);
  }
};

export const disconnect = () => {
  if (stompClient) {
    stompClient.disconnect();
  }
};

export const getMessages = async () => {
  try {
    const messages = await fetchMessages();
    return messages;
  } catch (e) {
    throw e;
  }
};

export const createMessage = async (messageData) => {
  try {
    const newMessage = await postMessage(messageData);
    return newMessage;
  } catch (e) {
    throw e;
  }
};
```

### 테스트용으로 조금 수정한 Main

```javascript
import React, { useEffect } from "react";
import { connect, disconnect, send } from "../../api/MessageRepo";

const Main = () => {
  useEffect(() => {
    connect(
      (message) => {
        console.log("받은 메시지: ", message);
      },
      () => {
        // 연결 성공 핸들러
        console.log("Websocket Connected");
      },
      () => {
        // 연결 종료
        console.log("Websocket disconnected");
      }
    );

    // 컴포넌트 언마운트시 웹소켓 해제
    return() => {
      disconnect();
    };
  }, []);

  const handleSendMessage = () => {
    const message = "Test message";
    send(message);
  };

  return (
  <div>
  <div>메인</div>  
    <button onClick={handleSendMessage}>Send Message</button>
  </div>
  );
};

export default Main;
```

### net과 관련된 오류가 나온다면?

`yarn add net -S` 를 입력하라

### 메세지의 구조

```console
<<< CONNECTED
version:1.1
heart-beat:0,0
```

일단 내가 켠 서버에서는 참고한 블로그와 다르게 user-name이라는 필드가 없다

[참고 블로그](https://wondongho.tistory.com/73)

```console
>>> SUBSCRIBE
id:sub-0
destination:/topic/messages
```

```console
>>> SEND
destination:/pub/send
content-length:26

{"content":"Test message"}
```

콘솔은 이렇게 두 개가 뜬다

content의 메시지는 프론트엔드에서 설정한 것이다

그리고 id:sub-0이 유저인 것 같고, destination은

```javascript
stompClient.subscribe("/topic/messages", (message) => {
        onMessageReceived(JSON.parse(message.body));
```

이것과 관련된 것 보면, 구독의 위치인가?

충격! MessageMapping은 RequestMapping에 달아준 것의 영향을 받지 않는다



### 이렇게 된 이유는?

Websocket config에 나는 `configureMessageBroker` 메서드에 `MessageBrokerRegistry`에 
`enableSimpleBroker`를 `/topic`과 `/queue`를 달아주었다(일대다 일대일)

그리고 구독 도착 경로의 prefix는 `/pub`로 바꾸어 주었다

```javascript
stompClient.subscribe("/queue", (message) => {
        onMessageReceived(JSON.parse(message.body));
      }

///

stompClient.send("/pub/write", {}, messageBody);

```

이렇게 되면, 프론트엔드에서는 위에 코드처럼 subscribe는 `/queue` 또는 `/topic`을 구독한다.

그리고 send를 backend의 controller에  MessageMapping과 연결시켜주면?

```java
    @MessageMapping(value = "/write") // 경로
    public void sendMessage(@Payload MessageWriteReqDto message) {

        log.info("sendMessage 호출");
        log.info("message의 내용 = {}", message.getContent());
    }
```

value에 맞는 주소로 보내게 될 경우 해당 구독자들에게 내용을 전달하는 방식인 것 같다

오 그리고 시큐리티를 쓸 때에 혹시 시큐리티 설정을 허용해주고 싶다면

requestMatchers에 "${endpoint}/**" 이렇게 달아주면 웹소켓과 관련된 요청을 허용해준다

### 그럼 객체는 어떻게 받을 수 있는가?

그냥 받으면 된다. 다만 기존 코드가

```javascript
export const send = (message) => {
  if (stompClient && stompClient.connected) {
    // const messageBody = JSON.stringify({ content: message });
    const messageBody = JSON.stringify(message);
    stompClient.send("/pub/write", {}, messageBody);
  }
};
```

주석으로 처리된 부분이 작동하고 있었는데, 객체를 다시 객체 안에 담아서 보내니까 파싱을 못하는 오류가 생긴 것

stomp 헤더 추가 방법

https://ppaksang.tistory.com/18