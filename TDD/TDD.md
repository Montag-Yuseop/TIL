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

## 참고

유튜브 영상 - 어라운드 허브 스튜디오
