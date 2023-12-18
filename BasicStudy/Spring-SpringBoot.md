# Spring vs SpringBoot

## Spring?

Spring Framework의 줄임말

자바에서 가장 많이 사용되는 Framework

- 의존성 주입(DI, Dipendency Injection)
- 제어 역전(IOC, Inversion of Control)
- 관점 지향 프로그래밍(AOP, Aspect Oriented Programming)

위 요소를 통해 느슨한 결합(Loose Coupling) 달성 가능 -> 단위 테스트에 용이함

## Spring의 대표 모듈

- Spring JDBC
- Spring MVC
- Spring AOP
- Spring ORM
- Spring Test
- Spring Expression Language(SpEL)

## Spring Boot가 나온 이유?

Spring Boot makes it easy to create stand-alone, production-grade Spring based</br> Applications that you can "just run".

스프링은 다양한 기능을 제공하지만, 설정에 많은 시간이 걸린다

## Spring Boot가 제공하는 기능

Spring Boot는 자동 설정(Auto Configuration)을 이용

- Application 개발에 필요한 모든 Dependency를 Framework에서 관리
- jar 파일이 Class path에 있는 경우, Spring Boot는 Dispatcher Servlet으로 자동 구성
- Spring Boot는 미리 설정되어 있는 Starter Project를 제공(Dependency 호환성)
- xml 설정 없이 자바 코드를 통한 설정
