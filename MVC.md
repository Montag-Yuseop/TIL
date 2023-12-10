# MVC(Model View Controller)

디자인 패턴 중 하나인 MVC 패턴은 Model, View, Controller의 줄임말로 어플리케이션을 구성할 때, 구성요소를 세 가지 역할로 구분한 패턴

사용자 인터페이스로부터 비즈니스 로직을 분리하여 서로 영향 없이 쉽게 고칠 수 있는 설계가 가능

Model -> View (updates)</br>
Model -> Controller(Manipulates)</br>
View <-> Controller

## 구성 요소

### Controller

Model, View 사이에서 브릿지 역할 수행

앱의 사용자로부터 입력에 대한 응답으로 모델 및 뷰를 업데이트하는 로직을 포함

사용자 요청은 모두 컨트롤러를 통해 진행

컨트롤러로 들어온 요청을 어떻게 처리할지 결정하여 모델로 요청 전달

Client -(Request)-> Dispatcher Servlet -> Handler Mapping -> Controller -> Message Converter -> HTTP Response -(Response)-> Clinet

#### @RestController

Spring Framework 4 부터 사용 가능</br>
@Controller에 @ResponseBody가 결합된 Annotation</br>
컨트롤러 클래스 하위 메서드에 @ResponseBody Annotation을 붙이지 않아도 문자열, JSON 전송 가능</br>
View를 거치지 않고 HTTP ResponseBody에 직접 Return값을 담아 보냄

#### @RequsetMapping

MVC Handler Mapping을 위해서 DefaultAnnotationHandlerMapping을 이용</br>
DefaultAnnotationHandlerMapping 매핑정보로 @RequestMapping Annotation을 이용

value: url 설정</br>
method: GET, POST, DELETE, PUT, PATCH 등</br>
Spring 4.3부터 사용 가능 (@GetMapping, @PostMapping, @DeleteMapping, @PutMapping, @PatchMapping)

### Model

데이터 처리 영역

데이터베이스와 연동을 위한 DAO(Data Access Object)와 데이터의 구조를 표현하는 DO(Data Object)로 구성됨

#### DAO(Data Access Object)

Database의 Data에 접근하기 위한 객체

#### DTO(Data Transfer Object)

계층 간 데이터 교환을 하기 위해 사용하는 객체</br>
로직을 가지지 않는 순수한 데이터 객체(Getter, Setter만 가진 클래스)

#### VO(Value Object)

값 오브젝트로 Read-Only 특징을 가진다(사용 도중 변경 불가능)</br>
DTO와 유사하지만 DTO는 Setter가 있다

### View

데이터를 보여주는 화면</br>
UI 포함</br>
별도 데이터를 저장하지 않음

### MVC 패턴 특징

각 영역이 분리되어있어 서로 간의 의존성이 낮음</br>
분업 및 협업이 원활</br>
한 영역을 업데이트 하더라도 다른 곳에 영향이 없음

대규모 프로젝트 진행시 한 영역에 밀집되는 경우가 생김 -> MVVM, MVP 등의 모델도 존재
