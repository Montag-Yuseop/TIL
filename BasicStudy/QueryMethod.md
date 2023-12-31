# Query Method

## JPQL(Java Persistence Query Language)

테이블이 아닌 엔티티 객체를 대상으로 사용되는 객체지향 쿼리

JPA는 JPQL을 분석한 후 연동되어 있는 데이터베이스에 맞는 SQL로 가공하여 사용

## 쿼리 메서드

Spring Data JPA의 핵심 기능

JpaRepository에서 제공하는 기본 메서드의 한계점 존재

Repository 내 정의되는 메서드 이름만으로 쿼리 생성 가능

네이밍 컨벤션 존재, 쿼리 자동 생성

## 문법

주제(Subject)와 서술어(Predicate)로 구분</br>
find...by 또는 exists...by 등</br>
서술어 영역은 검색 및 정렬 조건 작성

## 주제 키워드

- 조회: </br>
  find by, read by, get by, query by

- 존재: </br>
  exsist by - boolean type return

- 개수: </br>
  count by - long type return

- 삭제: </br>
  delete by, remove by - void or delete count return

- 제한:</br>
  ...First<number>, ...Top<number> - number 생략하면 하나만(정렬 후 사용)

## 조건자 키워드

- Is: </br>
  값의 일치를 위한 조건자 키워드</br>
  Equals와 동일한 기능 수행</br>
  생략하여 사용

- (Is)Not: </br>
  값의 불일치, Is는 생략하고 Not만 사용 가능

- (Is)Null, (Is)NotNull: </br>
  컬럼이 Null인지 체크

- (Is)True, (Is)False: </br>
  boolean 타입으로 지정되어 있는 컬럼의 값을 확인

- And, Or: </br>
  여러 조건을 묶을 때

- (Is)GreaterThan, (Is)LassThan, (Is)Between: </br>
  숫자, DateTime 컬럼에서 사용할 수 있는 비교 연산 키워드</br>
  경계값을 포함하기 위해서는 Eqaul 키워드 추가

- (Is)StartingWith(==StartsWith), (Is)EndingWith(==EndsWith), (Is)Containing(==Contains), (Is)Like: </br>
  컬럼 값의 일부가 일치하는지 확인하는 키워드</br>
  Containing 키워드는 양 끝에 % ('%값%')</br>
  StartingWith는 앞에 % ('%값')</br>
  EndingWith는 뒤에 %('값%')

# 정렬과 페이징

## 정렬

### 쿼리문에서 정렬

ORDER BY 구문을 사용하여 오름차순 / 내림차순의 데이터를 받는다

메서드 이름으로 정렬 처리(Asc, Desc)</br>
ex) findByNameOrderByStockAsc</br>
여러개 ex) findByNameOrderByStockAscPriceDesc(1순위, 2순위 정렬)

### Sort를 활용한 정렬

Sort 객체를 활용하여 정렬

ex) findByName(String name, Sort sort)</br>
findByName("pen", Sort.by(Order.asc("price")));

## 페이징

데이터베이스의 레코드를 개수로 나눠서 페이지를 구분

리턴 타입으로 Page를 설정하고, 매개변수로 Pageable 사용

ex) findByName(String name, Pageable pageable); </br>
findByName("공책", PageRequest.of(0,2));

| of 메서드                                                | 매개변수 설명                                            | 비고                                       |
| -------------------------------------------------------- | -------------------------------------------------------- | ------------------------------------------ |
| of(int page, int size)                                   | 페이지 번호(zero-based), 페이지 당 데이터 개수           | 정렬x                                      |
| of(int page, int size, Sort)                             | 페이지 번호, 페이지 당 데이터 개수, 정렬                 | sort에 의한 정렬                           |
| of(int page, int size, Direction, String ... properties) | 페이지 번호, 페이지 당 데이터 개수, enum 정렬 방향, 컬럼 | Sort.by(direction, properties)에 의해 정렬 |
