# HolidayKeeper

### 빌드 & 실행 방법
> ./gradlew bootRun

### 설계한 REST API 명세 요약
1. GET /api/v1/holidays
> 연도별, 국가별 필터 기반 공휴일 조회
> 
**Request**
```
1. 연도 및 국가 코드를 파라미터로 입력한다.
    1-1. 연도, 국가, [연도,국가]가 반드시 입력되어야 한다.

/api/v1/holidays?year=2025&countryCode=KR

2. 자동으로 페이징이 설정되어 있다.
    2-2. size = 10 , page = 0
```
**Response**
```
{
  "success": true,
  "data": {
    "content": [
      {
        "date": "2025-12-03",
        "localName": "새해",
        "name": "New Year's Day",
        "countryCode": "KR",
        "fixed": false,
        "global": true,
        "counties": [
          "US-TX",
          "US-VA"
        ],
        "launchYear": 0,
        "types": [
          "Public",
          "Optional"
        ]
      }
    ],
    "page": 0,
    "size": 20,
    "totalElements": 1624,
    "totalPages": 65
  }
}
```

2. PUT /api/v1/holidays
> 특정 연도, 국가 데이터를 재호출하여 덮어쓰기

**Request**
```
1. 연도와 국가 코드는 반드시 입력되어야 한다.
{
  "year": 2025,
  "countryCode": "KR"
}
```
**Response**
```
{
  "success": true,
  "data": {}
}
```

3. POST /api/v1/holidays 
> 최근 5년의 공휴일을 외부 API에서 수집하여 저장
> 
**Response**
```
{
  "success": true,
  "data": {}
}
```

4. DELETE /api/v1/holidays 
> 특정 연도, 국가의 공휴일 레코드 전체 삭제

**Request**
```
1. 연도와 국가 코드는 반드시 입력되어야 한다.
{
  "year": 2025,
  "countryCode": "KR"
}
```
**Response**
```
{
  "success": true,
  "data": {}
}
```



### Swagger UI 확인 방법
> 로컬 환경에서 해당 URL 접속
```
http://localhost:8080/swagger-ui/index.html
```