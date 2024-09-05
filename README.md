# [musinsa] Java(Kotlin) Backend Engineer - 과제

## About Project
### 프로젝트 환경
> Spring Boot 3.3.3
> Java 22
> Graddle 8.8
> 
### 라이브러리
> Spring Data JPA,
> QueryDSL,
> h2 database,
> Swagger,
> Lombok,
> modelmapper, 

### 패키지 구조
> **1Depth Domain & Global**
>* brand
>* codi
>* global
>* item
>> **2Depth controller, entitiy, dto, service, repository**
>>* api : Contoller, Api Uri mapping
>>* service : Business Logic을 담당 Data를 Modify하여 Presentation, Persistance Tier에 전달
>>* dto : Spring Tier 간 Data 이동을 위해 사용되는 Class를 구현
>>* domain : DB 테이블과 매칭되는 Entity Object
>>* repository : 데이터베이스에 CRUD 커넥션 처리

## 테스트 방법
1. Run을 통한 서버기동 시 다음의 SQL문으로 "query/testDataInsert.sql" 과제에서 제시된 Test Data 일괄 Insert
2. http://localhost:8080/swagger-ui/index.html Swagger를 이용하여 개별 API Test 가능
3. 과제 1~3번의 경우 codi-controller API영역에 구현
4. 과제 4번 브랜드 및 상품 추가/업데이트/삭제 기능은 각각 item-controller, brand-controller API영역에 구현


## 기타
### 프로제트 구현 범위
* 명세서에 적시된 1 ~ 4번 API 구현
* Swagger 적용으로 API Documentation 및 테스트 지원


### 프로젝트 구현 방향
* 도메인을 기반으로 한 packge 구성 (brand, item, codi) -> (api, domain, dto, repository, service)
* 공통 사용영역은 global 패키지로 구성 (config, util, GlobalExcpetionHandler 등)
* JPA를 도입하였으나 경험 부족으로 제대로 된 개념의 ORM을 구현하지는 못하였음 (1:N, N:1 Entity Mapping 등)
* QueryDSL을 사용하여 Native SQL 쿼리문 특정 기능에 적용
* Tier간 Data 오염을 막기 위해 Entity의 Data를 직접 Handling하지 않고 Dto로 전환 후 처리
* 상호참조를 피하기 위해 Service Layer의 참조 대신 Repository 레벨의 참조로 구성
* Dto의 과다생성을 피하기 위한 Inner Class 형태의 Dto 구성
* modelmapper를 통한 Entity -> DTO Mapping 일부 적용


