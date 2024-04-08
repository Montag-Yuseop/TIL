# SQL 연습장(프로그래머스 문제)

## Level 1

### 특정 형질을 가지는 대장균 찾기

#### 이해

테이블 내의 GENOTYPE에 숫자가 나온 것을 2진수로 변경하면 1111과 같이 네 자리의 2진수가 등장한다

해당 2진수는 각각 (4)(3)(2)(1)의 모양을 가지고, 0이면 형질이 없는 것, 1이면 형질이 있는 것을 의미한다

따라서 숫자를 2진수로 변경해서 자리수를 가져오는 방법을 알면 되겠다 생각했다

하지만, SQL문 초보라 저런 2진수 변경 등은 해본 적이 없다...

힌트로 얻은 방법은 알고리즘에서도 풀었던 비트마스킹 방법(자리수에 & 연산을 통해 각 자리수 여부를 확인하는 방법)

굳이 10진수 수를 이진수로 바꾸지 않아도 이진수 해당 자리의 10진수 값을 가지고 연산이 가능했다

#### 코드

```sql
SELECT
    COUNT(ID) AS COUNT
FROM 
    ECOLI_DATA
WHERE
    (GENOTYPE & 2 = 0) 
    AND
    (GENOTYPE & 1 > 0 OR GENOTYPE & 4 > 0)
;
```

### 가장 큰 물고기 10마리 구하기

#### 이해

`LENGTH` 컬럼을 내림차순으로 정렬한 뒤 상위 10개만 뽑으면 되겠다 생각했다

혹시나 NULL인 경우가 있을까 했으나, 상위 물고기의 길이가 10cm 이하인 경우는 없다고 했다

정렬은 `LEGNTH`를 기준으로, 혹시 동일한 값이라면 `ID`를 기준으로 오름차순 정렬이 조건

참고로 출력하는 컬럼명 또한 동일해서 그대로 출력했다

#### 코드

```sql
SELECT
    id, length
FROM
    FISH_INFO
ORDER BY
    length DESC, id ASC
LIMIT
    10
;
```

### 한 해에 잡은 물고기 수 구하기

#### 이해

2021년에 잡은 물고기의 수를 구하는 문제

`LENGTH`는 `NULL`인 경우가 있지만, 다른 컬럼 값은 `NULL`인 경우가 없다

ID 값을 기준으로 COUNT를 해주었고, `WHERE` 절에서 조건을 비교하기 위해 `YEAR`라는 방법을 썼다

`DATE TYPE`의 경우 `YEAR`, `MONTH` 등 날짜 관련 명령어를 사용할 수 있었다

#### 코드

```sql
SELECT
    COUNT(ID) AS FISH_COUNT
FROM
    FISH_INFO
WHERE
    YEAR(TIME) = '2021'
;
```

### 잡은 물고기 중 가장 큰 물고기 길이 구하기

#### 이해

일단 `MAX` 함수를 통해 가장 `LENGTH` 값이 높은 경우를 가져오면 될 것이라 생각했다

그런데, `cm`라는 단위를 붙이기 위해서는 `CONCAT`을 써야 할 것 같았다

`CONCAT`은 254글자 까지 합칠 수 있다고 한다

그런데 `+` 연산자 만으로도 문자열 합치기가 가능해서 간단하게 쓰려면 `+`도 좋을 것 같았다

다만 `+` 연산자를 사용할 때, 문자 + 숫자 같이 다른 타입은 `CAST(숫자 AS VARCHAR)`의 형태로 변경하여 사용하자

또한 `CONCAT_WS`를 통해 구분자를 넣어 합칠 수 있다

`CONCAT_WS('구분자', '합칠 문자1', '합칠 문자2' ...)`

연습을 통해 다양하게 사용할 수 있어야겠다

#### 코드

```sql
SELECT
    CONCAT(MAX(LENGTH), "cm") AS MAX_LENGTH
FROM
    FISH_INFO
;
```

### 잡은 물고기의 평균 길이 구하기

#### 이해

물고기 `LENGTH`의 평균을 구하는 문제라 일단 `AVG`를 사용했다

여기서 소수점 세 번째 자리에서 반올림을 시도해야 했기 때문에, 반올림 함수 `ROUND`를 밖에 묶어 주었다

`ROUND(AVG(LENGTH), 2)` 두 번째 자리에서 반올림 + `LENGTH`의 평균

여기에 추가 조건으로 만약 `NULL`인 경우(10cm 미만인 경우) 모두 10cm로 처리해 주어야 했다

`IFNULL` 명령어를 통해 만약 `LENGTH`가 `NULL`이면 10으로 값을 정해주어야 했다

`LENGTH` 자체를 다시 묶어서 `IFNULL(LENGTH, 10)` 형태로 만일 `NULL`이 아니면 `LENGTH`를, `NULL`이면 뒤의 인자값 10을 넣어줬다

#### 코드 

```sql
SELECT
    ROUND(AVG(IFNULL(LENGTH, 10)), 2) AS AVERAGE_LENGTH
FROM
    FISH_INFO
;
```

### 잔챙이 잡은 수 구하기

#### 이해

`NULL`값인 경우만 조회하는 방법이다

- `COUNT(*)` : NULL을 포함한 모든 필드의 수를 반환한다
- `COUNT(컬럼명)` : NULL을 제외한 모든 필드의 수를 반환한다

따라서 `COUNT(*)` - `COUNT(LENGTH)` 의 방식을 사용할 수도 있고

전체를 카운트 한 뒤 `WHERE` 구문에 `LENGTH IS NULL`을 추가하여 NULL인 경우로 추려내는 방법이 있다

#### 코드
```sql
 SELECT
    COUNT(*) AS FISH_COUNT
FROM
    FISH_INFO
WHERE
    LENGTH IS NULL
;
 ```
### 특정 옵션이 포함된 자동차 리스트 구하기

#### 이해
`OPTIONS` 컬럼에 네비게이션이 포함된 차량을 ID 순서 내림차순 정렬해야 하는 문제

정확히 네비게이션을 포함한 문제가 아니기 때문에 `LIKE` 문을 사용해서 문자열 내에 네비게이션이 있는지를 확인해야 했다

`IN`은 한 컬럼 내에서 여러 조건을 검색하기 위해 사용했고, `=`도 비슷하지만 `=`은 여러 조건이 한 번에 검색이 안됐다

#### 코드

```sql
SELECT
    *
FROM
    CAR_RENTAL_COMPANY_CAR
WHERE
    OPTIONS LIKE ("%네비게이션%")
ORDER BY
    CAR_ID DESC
;
```

### 자동차 대여 기록에서 장기/단기 대여 구분하기

#### 이해

`2022-09`년 기록 중 날짜가 `30`일 이상인 항목은 `장기 대여`로, 미만인 경우는 `단기 대여`로 새로운 필드를 만들어 입력해줘야 한다

정렬은 대여 기록 ID(`HISTORY_ID`)를 기준으로 내림차순 정렬한다

`IF`, 또는 `CASE` 문을 사용 가능하다 -> 나는 `IF`문을 사용했다(장/단기 구분이 아닌 더 많은 경우로 나누려면 `CASE`문을 사용해야 할 듯 하다)

또한 날짜를 어떻게 계산할지 몰랐었는데, `DATEDIFF`라는 함수가 있었다

해당 함수는 `DATEDIFF(${종료날짜}, ${시작날짜})` 형태로 사용해 주면 그 차이 값을 반환한다

`2022-09-01`에 빌려서 `2022-09-02`에 반납한다면 -> `DATEDIFF('2022-09-02', '2022-09-01)`로 나타낼 수 있다

그런데 이 경우 대여 날짜가 1일, 2일인데 결과문은 `1`을 반환한다

따라서 `DATEDIFF`가 `29` 이상(+1)인 경우에 `장기대여`로 처리해주면 되었다

`START_DATE`가 `2022-09`인 경우만 찾기 위해 자주 사용하는 `YEAR()`과 `MONTH()`를 사용했다

다른 방법도 있는 듯 했는데, `START_DATE LIKE '2022-09-%'` 형태로 사용도 가능했다

또 출력 형식을 맞춰주기 위해 `DATE_FORMAT()`을 사용했다

#### 코드

```sql
SELECT
    HISTORY_ID,
    CAR_ID,
    DATE_FORMAT(START_DATE, "%Y-%m-%d") AS START_DATE,
    DATE_FORMAT(END_DATE, "%Y-%m-%d") AS END_DATE,
    IF(DATEDIFF(END_DATE, START_DATE) >= 29, "장기 대여", "단기 대여") AS RENT_TYPE
FROM
    CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE
    YEAR(START_DATE) = '2022' AND
    MONTH(START_DATE) = '09'
ORDER BY
    HISTORY_ID DESC
;
```
추가로 `CASE WHEN THEN`을 사용한 방법도 있다

```sql
SELECT
    HISTORY_ID,
    CAR_ID,
    DATE_FORMAT(START_DATE, "%Y-%m-%d") AS START_DATE,
    DATE_FORMAT(END_DATE, "%Y-%m-%d") AS END_DATE,
CASE
    WHEN DATEDIFF(END_DATE, START_DATE) >= 29 THEN '장기 대여'
    ELSE '단기 대여'
END AS RENT_TYPE
FROM
    CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE
    YEAR(START_DATE) = '2022' AND
    MONTH(START_DATE) = '09'
ORDER BY
    HISTORY_ID DESC
;
```

## Level 2

### 이름에 el이 들어가는 동물 찾기

#### 이해

`WHERE`문에 `LIKE`문을 활용하면 쉽게 찾을 수 있을 것 같았다

문제는 대소문자 구별을 안한다는 것

어떤 함수가 있을까 살펴봤다

`UPPER` 또는 `LOWER`를 사용해서 `NAME` 컬럼을 모두 소문자로 바꾼 뒤 일치하는지 검색하면 된다

추가로 기르던 개라는 조건도 넣어줘야 했다

#### 코드

```sql
SELECT
    ANIMAL_ID, NAME
FROM
    ANIMAL_INS
WHERE
    ANIMAL_TYPE = 'Dog' AND
    LOWER(NAME) LIKE '%el%'
ORDER BY
    NAME
;
```

### DATETIME에서 DATE로 형 변환

#### 이해

DATE_FORMAT을 사용하면 되는 문제였다

DATE_FORMAT은 자주 쓰기 때문에 알아두자

#### 코드

```sql
SELECT
    ANIMAL_ID,
    NAME,
    DATE_FORMAT(DATETIME, "%Y-%m-%d") AS "날짜"
FROM
    ANIMAL_INS
;
```

### 카테고리 별 상품 개수 구하기

#### 이해

SQL 내의 문자열을 쪼개서 그룹화 할 줄 알아야 하는 문제였다

`PRODUCT_CODE` 앞 두 자리를 `CATEGORY`로 만든 뒤 해당 `CATEGORY`로 `GROUP BY` 해줘야 했다

문자열을 쪼개는 것은 기본적으로 `SUBSTR`(오라클) 또는 `SUBSTRING`(MySQL)으로 사용한다

`SUBSTRING(문자, 시작, 종료)`로 사용하면 되는 듯 하다

`LEFT`, `RIGHT`, `MID`도 있다

`LEFT(문자, 종료)`는 말 그대로 왼쪽부터 가져오는 것이고</br>
`RIGHT(문자, 종료)`는 오른쪽 부터 가져오는 것이다

`MID`는 SUBSTRING과 똑같이 쓰면 되겠다

```sql
SELECT
    SUBSTR(PRODUCT_CODE, 1, 2) AS CATEGORY,
    COUNT(PRODUCT_ID) AS PRODUCTS
FROM
    PRODUCT
GROUP BY
    CATEGORY
;
```

### 중성화 여부 파악하기

#### 이해
저번에 했던 것과 비슷하게 `IF` 또는 `WHEN`으로 조건을 입력해서 O, X로 나타내면 됐다

#### 코드

```sql
SELECT
    ANIMAL_ID,
    NAME,
    CASE
        WHEN SEX_UPON_INTAKE LIKE '%Neutered%' THEN 'O'
        WHEN SEX_UPON_INTAKE LIKE '%Spayed%' THEN 'O'
        ELSE 'X'
    END AS '중성화'
FROM
    ANIMAL_INS
ORDER BY
    ANIMAL_ID ASC
;    
```