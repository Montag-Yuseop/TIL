# TDD <p>(Test Driven Delvelopment)</p>

## TDD?

TDD = 테스트 주도 개발(Test Driven Developement)
</br>테스트를 먼저 설계 및 구축한 뒤 실제 비즈니스 로직을 작성하는 것
</br>애자일 개발 방식 중 하나인 xp(eXtream Programming)에 포함된다
</br>단계는 RED, GREEN, BLUE로 진행된다

- RED: 실패 테스트 추가
- GREEN: 테스트 통과, 최소한의 코딩
- BLUE: 구현 설계 개선, 테스트 통과 유지

## TDD의 장점

불필요한 설계를 피하고, 코드를 최소화 하여 코드를 간단명료하게 나타낸다

의견 충돌을 최소화 한다

코드 안정성 향상

Side-Effect 최소화

> Side-Effect:
> </br> 함수 내의 어떠한 구현이 함수 결과값 이외에 다른 상태를 변경시키는 경우, 즉 의도하지 않은 결과가 나타는 것

## JUnit?

Java의 Test Framework

단위 테스트(Unit Test)를 위한 도구 제공

> 단위 테스트:
> </br> 코드의 특정 모듈이 의도된 대로 동작하는지 테스트
> </br> 모든 함수와 메소드에 대한 각각의 Test Case를 작성

Annotation을 기반으로 테스트 진행

Assert(단정문)으로 테스트 기대값에 대한 결과 확인 가능(일치, 불일치)

JUnit 5 버전을 사용하며, Jupiter, Platform, Vintage 모듈로 구성

### Juni Module

JUnit Jupiter
</br> TestEngin APi 구현체로 JUnit 5를 구현
Jupiter API를 통해 작성한 테스트 코드를 발견하고 실행하는 역할 수행
</br> 개발자가 테스트 코드를 작성할 때 사용

JUnit Platform
</br> Test를 실행하기 위한 뼈대
</br> Test를 발견하고 테스트 계획을 생성하는 TestEngine 인터페이스를 가지고 있음
</br> TestEngine을 통해 Test를 발견하고 수행 및 결과 보고
</br> 각종 IDE 연동을 보조하는 역할(콘솔 출력 등)
</br>Platform = TestEngine API + Console Launcher + JUnit 4 Based Runner 등

JUnit Vintage
</br> TestEngine API 구현체로 JUnit 3, 4를 구현하고 있음
</br> JUnit 3, 4 버전으로 작성된 테스트 코드 실행시 사용
</br> Vintage-Engine Module을 포함

**Platform은 인터페이스, Jupiter, Vintage는 구현체**

## JUnit LifeCycle Annotation

| Annotation  | Description                                                   |
| ----------- | ------------------------------------------------------------- |
| @Test       | 테스트용 메서드를 표현하는 어노테이션                         |
| @BeforeEach | 각 테스트 메서드가 **시작되기 전**에 실행되어야 하는 메서드   |
| @AfterEach  | 각 테스트 메서드가 **시작된 후** 실행되어야 하는 메서드       |
| @BeforeAll  | 테스트 **시작 전**에 실행되어야 하는 메서드(static 처리 필요) |
| @AfterAll   | 테스트 **종료 후**에 실행되어야 하는 메서드(static 처리 필요) |

## JUnit Main Annotation

### @SpringBootTest

- 통합 테스트 용도로 사용
- @SpringBootApplication을 찾아가 하위의 모든 Bean을 스캔하여 로드
- 그 후 Test용 Application Context를 만들어 Bean을 추가하고, MockBean을 찾아 교체

### @ExtendWith

- Junit4에서 @RunWith로 사용되던 어노테이션이 변경된 모습
- 메인으로 실행될 Class를 지정할 수 있음
- @SpringBootTest에는 기본적으로 추가되어 있음

### @WebMvcTest(Class명.class)

- ()에 작성된 클래스만 실제로 로드하여 테스트 진행
- 매개 변수를 지정해주지 않으면, @Controller, @RestController, @RestControllerAdvice 등 컨트롤러와 관련된 Bean이 모두 로드
- 컨트롤러 관련 코드만 테스트할 때 사용

### @Autowired about Mockbean

- Controller API 테스트 용도인 MockMvc 객체를 주입 받음
- preform() 메서드를 활용하여 컨트롤러의 동작을 확인할 수 있음
- andExpect(), andDo(), andReturn() 등의 메서드를 같이 활용

### @MockBean

- 테스트할 클래스에서 주입 받고 있는 객체에 대해 가짜 객체를 생성해주는 Annotation
- 실제 행위를 하지 않음
- given() 메서드를 활용하여 각자 객체 동작에 대해 정의하여 사용

### @AutoConfigureMockMvc

- spring.test.mockmvc의 설정을 로드하면서 MockMvc의 의존성을 자동으로 주입
- MockMvc 클래스는 REST API 테스트를 할 수 있음

### @import

- 필요한 Class를 Configuration으로 만들어 사용 가능
- Configuration Component 클래스도 의존성 설정 가능
- Import된 클래스는 주입으로 사용

## 통합 테스트

통합테스트는 여러 기능을 조합하여 전체 비즈니스 로직이 제대로 동작하는지 확인하는 것</br>
통합테스트는 @SpringBootTest Annotation을 활용하여 진행</br>
@SpringBootTest는 @SpringBootApplication을 찾아가 모든 Bean을 로드

이 방법을 대규모 프로젝트에서 사용할 경우 테스트 실행마다 모든 Bean을 스캔하고 로드해야 하기 때문에 작업이 무거워짐 -> 따라서 단위 테스트를 진행하게 된다

## 단위 테스트

단위 테스트는 프로젝트에 필요한 모든 기능에 대한 테스트를 **각각** 진행하는 것을 의미

일반적으로 스프링 부트에서는 'org.springframework.boot:spring-boot-starter-test' Dependency만으로 의존성을 모두 가질 수 있음

### F.I.R.S.T 원칙

- Fast: 테스트 코드의 실행은 빠르게 진행되어야 함
- Independent: 독립적인 테스트가 가능해야 함
- Repeatable: 테스트는 매번 같은 결과를 만들어야 함
- Self-Validating: 테스트는 그 자체로 실행하여 결과를 확인할 수 있어야 함
- Timely: 단위 테스트는 비즈니스 코드가 완성되기 전에 구성하고 테스트가 가능해야 함</br>
  (TDD의 원칙)

# Test Coverage 확인하기

## 코드 커버리지?

소프트웨어 테스트 수준이 충분한지 표현할 수 있는 지표 중 하나

테스트를 진행했을 때 해당 코드가 실행되었는지를 표현하는 방법

## Jacoco?

Java Code Coverage를 체크하는 라이브러리

작성된 코드의 테스트 커버리지를 측정하는 도구

Runtime으로 Test Code를 실행하여 Coverage를 체크하는 방식

html, xml, csv 등의 Report 제공

## 블랙박스 테스트

소프트웨어 내부 구조나 작동 원리를 모르는 상태에서 동작 검사

다양한 값을 입력하고, 올바른 출력이 나오는지 테스트

사용자 관점 테스트

## 화이트박스 테스트

소프트웨어 내부 구조, 동작을 검사하는 테스트 방식

소프트웨어 내부 소스 코드 테스트

개발자 관점 테스트

## Jacoco pom.xml

Excution 내부 사용 값

- prepare-agent: </br>
  테스트 중인 어플리케이션에서 인수를 전달하는 Jacoco Runtime Agent에 대한 property 준비

- merge: </br>
  여러 실행 데이터 파일들을 하나로 통합하는 명령어

- report: </br>
  하나의 프로젝트 테스트에 대한 Code Coverage Report를 생성

- check: </br>
  code coverage metric이 충돌하는지 확인

## Jacoco Rule

### Element type - 코드 커버리지 체크 기준

- BUNDLE(default): 패키지 번들
- PACKAGE: 패키지
- CLASS: 클래스
- SOURCEFILE: 소스 파일
- METHOD: 메서드

### Counter - 코드 커버리지 측정할 때 사용하는 지표

- LINE: 빈 줄을 제외한 실제 코드 라인 수
- BRANCH: 조건문 등의 분기 수
- CLASS: 클래스 수
- METHOD: 메서드 수
- INSTRUCTION(default): Java Bite 코드 명령 수
- COMPLEXITY: 복잡도

### Value - 커버리지 정도를 나타내는 지표

- TOTALCOUNT: 전체 수
- MISSEDCOUNT: 커버되지 않은 수
- MISSDRATIO: 커버되지 않은 비율(0 ~ 1)
- OCVERDRATIO(default): 커버된 비율(0 ~ 1)

특정 클래스를 테스트 대상에서 제외하기 위한 설정

```xml
<configuration>
  <excludes>
    <exclude> **/파일명.class </exclude>
  </excludes>
</configuration>
```

## 참고

유튜브 영상 - 어라운드 허브 스튜디오
