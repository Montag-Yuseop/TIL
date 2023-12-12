# Lombok

반복되는 메서드를 Annotation을 활용해 자동 생성해주는 라이브러리

일반적으로 VO, DTO, Model, Entity 등의 데이터 클래스에서 주로 사용

많이 사용되는 Annotation 종류

- @Getter: Getter Method 생성
- @Setter: Setter Method 생성
- @NoArgConstructor: 파라미터가 없는 생성자 생성(기본 생성자인가보다)
- @AllArgConstructor: 모든 필드값을 파라미터로 갖는 생성자 생성
- @RequiredArgsConstructor: 필드값 중 final이나 @NotNull인 값을 갖는 생성자 생성
- @Data: @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode를 한번에 추가
- @ToString: toString 메서드 자동 생성, exclude를 통해 제외 가능</br>

```java
@ToString(exclude="email")
public class MemberDTO {
    private name;
    private email;
    private organization;
}

```

- @EqualsAndHashCode: equals, hashCode 메서드 자동 생성</br>
  equals: 두 객체의 내용이 같은지 동등성(equality)을 비교하는 연산자</br>
  hashCode: 두 객체가 같은 객체인지 동일성(identity)를 비교하는 연산자

  callSuper 속성을 통해 메서드 생성시 부모 클래스 필드까지 고려할지 여부 설정 가능</br>
  : callSuper = true -> 부모 클래스 필드 값들도 동일한지 체크
