# Vaildation(유효성 검사 / 데이터 검증)

## 유효성검사?

서비스의 비즈니스 로직이 올바르게 동작하기 위해 사용되는 데이터에 대한 사전 검증 작업

들어오는 데이터에 대해 의도한 형식의 값이 제대로 들어오는지 체크하는 과정

## 일반적인 Vaildation의 문제점

- 어플리케이션 전체적으로 분산되어 존재
- 코드의 중복이 심함(코드가 복잡)
- 비즈니스 로직에 섞여있어 검사 로직 추적이 어려움

## 해결 방안

2009년부터 Java에서 Bean Vaildation이라는 데이터 유효성 검사 프레임워크 제공

Bean Vaildation은 어노테이션을 통해 다양한 데이터 검증 기능 제공

Hibernate Vaildator는 Bean Validation 명세에 대한 구현체

Spring Boot의 유효성 검사 표준은 Hibernate Validator를 채택

2.3버전부터 starter-vaildation을 추가해야 함

## Vaildation Annotation

- @Size: 문자 길이조건
- @NotNull: null 불가
- @NotEmpty: @NotNull + "" 값 불가
- @NotBlank: @NotEmpty + " " 값 불가

</br>

- @Past: 과거 날짜
- @PastOrPresent: @Past + 오늘 날짜
- @Future: 미래 날짜
- @FutureOrPresent: @Future + 오늘 날짜

</br>

- @Pattern: 정규식 조건

</br>

- @Max: 최댓값 조건 설정
- @Min: 최솟값 조건 설정
- @AssertTrue / AssertFalse: 참/거짓 조건 설정

</br>

- @Valid: 해당 객체 유효성 검사
