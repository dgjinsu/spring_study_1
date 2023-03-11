## IP 인터넷 프로토콜

### 역할
* 지정한 IP주소에 데이터 전달
* 패킷(Packet)이라는 통신 단위로 데이터를 전달한다.

* IP 패킷 정보
- 패킷은 전송하고자 하는 데이터의 한 블록(payload)과 주소지 정보(발신지 주소, 목적지 주소), 관리정보(Header, IPv6와 같이 망이 패킷을 목적지까지 전달하는데 필요한)로 구성된다. 

### 한계
* 비연결성 : 패킷을 받을 수 없거나 서비스 불능 상태여도 패킷 전송
* 비신뢰성 : 패킷이 중간에 사라지거나 / 순서대로 오지 않을 경우(보낼 데이터가 크면 두개로 끊어서 보냄)
* 프로그램 구분 : 같은 IP를 사용하느 ㄴ서버에서 통신하는 애플리케이션이 둘 이상일 경우(게임, 영화를 같이 하는 경우)

## TCP/UDP

* 프로토콜 계층
![image](https://user-images.githubusercontent.com/97269799/224468234-504d473d-390f-4742-a4db-4fc6ff4fdafb.png)

* IP 패킷
  * 출발지 IP, 목적지 IP, 기타..

* TCP
  * 출발지 PORT, 목적지 PORT, 전송 제어, 순서, 검증

TCP 특징
1. 연결지향 : 연결됐는지 확인 (TCP 3 way handshake)
2. 데이터 전달 보증 : 중간에 누락됐는지를 알 수 있음
3. 순서 보장
4. 신뢰할 수 있는 프로토콜

* TCP 3 way handshake
![image](https://user-images.githubusercontent.com/97269799/224468339-063e3620-ec1a-4a4a-bca5-5559c4259c6c.png)
위와 같이 3번 주고 받음
`SYN` : 접속 요청
`ACK` : 요청 수락

UDP 특징
* TCP 에 비해 거의 기능이 없음
* 몹시 단순하기에 속도가 빠르다. 
* IP 와 거의 같다. +PORT + 체크섬 정도만 추가
* 애플리케이션에서 추가 작업 필요

PORT
* PORT : 내가 해당 IP에 도착하여 해당 서버를 만났을때 이 서버에서 돌아가고 있는 애플리케이션이 2개 이상이라면 이 중 어떤 애플리케이션에 데이터를 전송해야 할까? 를 해결해줌 
* 0 ~ 65535 할당 가능
* 0 ~ 1023: 잘 알려진 포트이기에 사용하지 않는 것을 추천
  * FTP - 20, 21
  * TELNET - 23
  * HTTP - 80
  * HTTPS - 443

### DNS
* IP는 변경될 수 있고 어렵고 가독성이 떨어지기 때문에 DNS 사용
* 일종의 전화번호부와 같으며 도메인 명을 IP 주소로 변환한다. 


## URI 
![image](https://user-images.githubusercontent.com/97269799/224469060-c6fa2981-f43e-47b5-bb05-7e7f29403aab.png)
* Uniform:리소스를 식별하는 통일된 방식
* Resource: 자원, URI로 식별할 수 있는 모든 것(제한이 없다.)
* Identifier: 다른 항목과 구분하는데 필요한 정보

### URL 분석
`scheme://[userinfo@]host[:port][/port][/path][?query][#fragment]`

`https://google.com/search?q=hello&hl=ko`
* 스키마 : 주로 프로토콜 사용
 * 프로토콜 : 어떤 방식으로 자원에 접근할 것인가 하는 약속 규칙(http, https ftp 등)
* http:80포트 / https:443포트 / 
* https 를 적으면 뒤에 port는 생략 가능
* userinfo는 거의 사용 x
* host : 보통 도메인 명 (naver.com)
* query : key=value 형태 / ?로 시작 / &로 추가 가능

### 웹 브라우저 요청 흐름
![image](https://user-images.githubusercontent.com/97269799/224471513-97e2dfef-a0b1-4934-badc-04503686221d.png)

1. 웹 브라우저가 HTTP 메세지를 생성
2. SOCKET 라이브러리를 통해 TCP/IP계층에 전달한다. 이전단계에서 찾은 IP와 PORT정보를 가지고 SYN, SYN+ACK, ACK 과정을 통해 서버와 연결을 한다. 연결이 성공되면 TCP/IP 계층으로 데이터를 전달한다.
3. TCP/IP 패킷을 생성한다. HTTP 메세지도 포함된다. 
4. 서버는 패킷이 도착하면 패킷의 내부 HTTP 메서드를 해석해서 정보에 맞는 동작을 한다.
5. 서버에서 HTTP 응답 메세지를 생성한다. (HTML 데이터)
6. 클라이언트에서는 응답메세지를 받아 맞는 동작(ex: 렌더링)을 한다. 


## HTTP
HTTP(Hyper Text Transfer Protocol) : 문서간에 링크를 통해 연결할 수 있는 프로토콜
* HTML, TEXT
* IMAGE, 음성, 영상, 파일
* JSON, XML(API)
* 거의 모든 형태의 데이터가 전송 가능하다.
* 서버간에 데이터를 주고 받을 때도 대부분 HTTP를 사용한다.

## HTTP 특징
* 클라이언트 서버 구조로 동작한다. 
* 무상태 프로토콜(stateless), 비연결성
* HTTP 메세지를 통해서 통신을 한다(송/수신 모두)
* 단순함, 확장 가능

> 클라이언트 서버 구조
* request / response 구조
* 클라이언트는 서버에 요청을 보내고, 응답을 대기
* 서버가 요청에 대한 결과를 만들어서 응답

> sateless
* 서버가 클라이언트의 상태를 보존하지 않는다. 그렇기에 매번 요청에 모든 상태값들을 전달해줘야 한다. 
* 상태 유지: 중간에 서버가 변경되면 안된다. (만약 서버가 변경되야 한다면 상태 정보를 전부 다른 서버에게 미리 알려줘야 한다.)
* 무상태: 중간에 서버가 변경되도 된다.
* 클라이언트가 폭증해도 **서버를 대거 늘릴 수 있다.**
* 무상태는 응답 서버를 변경하기가 쉽기에 **서버 증설**이 쉽다. 


* 무상태의 한계
 * 로그인한 사용자의 경우 해당 상태를 서버에 유지해야한다.
 * 일반적으로 브라우저 쿠키와 서버 세션등을 사용해 상태를 유지한다.
 * 상태 유지는 최소한으로만 사용한다.
 * 보내야 하는 데이터가 너무 많다. ⇒ 매번 요청에 필요한 데이터를 전부 작성해야 하기 때문.

> 비연결성
* 클라이언트별로 서버와 연결을 유지하지 않을경우 필요할때만 연결을 하며 그외에는 연결을 유지하지 않기 때문에 서버에서 사용해야 하는 자원이 훨씬 줄어든다. 
* HTTP는 기본이 연결을 유지하지 않는 모델이다.
* 일반적으로 초 단위 이하의 빠른 속도로 응답한다.
* 1시간동안 수천명 이상이 서비스를 사용해도 실제 서버에서 동시에 처리하는 요청은 수십개 이하로 적다. 
 * EX: 웹 브라우저 검색페이지에서 검색버튼만 연타하면서 이용하지는 않는다.
* 서버 자원을 매우 효율적으로 사용할 수 있다.

* 비연결성의 한계
 * TCP/IP 연결을 새로 맺어야 한다. - 3 way handshake 시간 추가
  * 지금은 HTTP 지속 연결(Persistent Connections)로 문제를 해결한다.
![image](https://user-images.githubusercontent.com/97269799/224472558-ba8bda2d-c6a1-4364-b076-fe8bce86f721.png)


**스테이스리스를 기억하자.**
실무상황에서 특정 시간에 발생하는 대용량 트래픽에 대해서 대응해야 하는 경우가 생긴다.
(Ex: 선착순 이벤트, 명절 KTX예약, 개강전 수업 등록)

이럴 경우 수천, 수만명 이상이 동시에 접속을 하면서 서버에 과부하가 걸리는 경우가 있는데,  이 경우 무상태 페이지를 활용해 페이지 접속인원을 분산해서 대용량 트래픽을 분산시키면 좋다


## HTTP 메시지
* 요청 메시지
![image](https://user-images.githubusercontent.com/97269799/224472649-e02d557e-c117-4634-a48b-e4ee953aaa65.png)

* 응답 메시지
![image](https://user-images.githubusercontent.com/97269799/224472669-d4888ed4-922f-44e5-8768-451fe2c5b5bc.png)

HTTP 헤더엔 HTTP 전송에 필요한 모든 부가 정보를 담고 있음.
HTTP 메시지 바디엔 실제 전송할 데이터 (html문서, json, 이미지..)


## HTTP 메서드
* 회원 목록 조회 : /members
* 회원 조회: /members/{id}
* 회원 등록: /members/{id}
* 회원 수정: /members/{id}
* 회원 삭제: /members/{id}
참고: 계층 구조상 상위를 컬렉션으로 보고 복수산어를 사용하기를 권장한다(member→ members)

### HTTP 메서드 종류
* GET: 리소스 조회
 * 서버에 전달하고 싶은 데이터는 query(쿼리 파라미터, 쿼리 스트링)를 통해 전달한다.
* POST: 요청 데이터 처리, 주로 등록에 사용한다.
 * 메시지 바디를 통해 서버로 요청데이터를 전달한다.
 * 새 리소스 생성(등록)
 * 요청 데이터 처리
* PUT: 리소스를 대체하며 해당 리소스가 없으면 생성한다.
 * 리소스를 대체하는 메서드로 리소스가 있을경우 대체하고 없을 경우 생성한다. (Overwrite)
 * 클라이언트가 리소스를 식별한다 (ex. 게시글 100번째에 put 하겠다.)
 * **기존의 리소스를 완전히 대체함.**
* PATCH: 리소스 부분 변경
 * put의 리소스를 완전히 대체하는 기능과 다르게 리소스를 일부 변경함. 
* DELETE: 리소스 삭제

### HTTP 메서드의 속성
![image](https://user-images.githubusercontent.com/97269799/224476459-71295ed1-8795-48ca-b398-52b48b106fda.png)

* 안전(Safe Methods)
 * 호출해도 리소스가 변경되지 않는다. 
 * GET같은경우에 조회만해서 리소스를 변경하지 않기에 안전하다. 
 * POST,PUT,DELETE,PATCH같은경우 리소스를 변경하기에 안전하지 않다.
* 멱등
 * GET: 몇 번을 조회하더라도 같은 결과가 조회된다. ⇒ 회원 정보를 몇번을 조회한다고 정보가 달라지지 않는다. 
 * PUT: 결과를 대체한다. 따라서 같은 요청을 여러번해도 최종 결과는 같다.
 * DELETE: 결과를 삭제한다. 같은 요청을 여러번 해도 삭제된 결과는 같다.
 * POST: **멱등이 아니다.** 두 번 호출하면 에러가 발생할수 있다. ⇒ POST로 주문을 두 번 호출하면 결제가 중복될 수 있다. 
 * 멱등의 활용
  * 서버가 TIMEOUT 등으로 정상 응답을 하지 못했을때 클라이언트에서 **같은 요청을 해도 되는가에 대한 판단근거**가 된다. 
* **캐시가능**(Cacheable Methods)
 * 응답 결과 리소스를 캐시해서 사용 가능
 * 실제로는 GET, HEAD 정도만 캐시로 사용한다.



## 클라이언트에서 서버로 데이터 전송

* 쿼리 파라미터를 통한 데이터 전송 
 * :GET 에서 많이 사용하고 정렬필터나 검색어를 사용할 때 이 쿼리 파라미터를 많이 사용한다. 
* 메시지 바디를 통한 데이터 전송
 * : POST, PUT, PATCH에서 사용되며 회원 가입, 상품 주문과 같이 리소스를 등록하거나 변경하는데에 사용한다. 

클라이언트에서 서버로 데이터를 전송하는 4가지 상황
1. 정적 데이터 조회
2. 동적 데이터 조회
3. HTML Form 을 통한 데이터 전송
![image](https://user-images.githubusercontent.com/97269799/224477275-e059016e-9458-498e-9159-aad8b875eb33.png)
   - multipart form data
![image](https://user-images.githubusercontent.com/97269799/224477329-a7638c6f-42e8-4193-a6bb-9adc1fb1058d.png)
multipart/form-data 형식이라면 HTTP 메세지에 임의의 구분자(boundary=——-XXX) 가 Form 데이터간 구분을 지어준다. 
4. HTTP API를 통한 데이터 전송
  - Content-Type: application/json을 주로 사용(사실상 표준)

## HTTP API 설계 에시
> POST 신규 등록
![image](https://user-images.githubusercontent.com/97269799/224477748-7d51de79-1544-468d-a735-8f86e5d51d57.png)

* POST - 신규 자원 등록 특징
 * 클라이언트는 등록될 리소스의 URI를 모른다. 
 * 서버가 새로 등록된 리소스 URI를 생성해준다.
 * `컬랙션` 이라고 함


> PUT 신규 등록 (파일 같은 경우 put 을 씀)
![image](https://user-images.githubusercontent.com/97269799/224478041-9e1435bb-13dd-4a19-87b9-b1cfd2be411d.png)


* put - 신규 자원 등록 특징
 * 이땐 클라이언트가 리소스 URI 를 알고 있어야 한다.
  * /files/{filename} -> put
 * `스토어` 라고 

> HTML FORM 사용
![image](https://user-images.githubusercontent.com/97269799/224478231-f36a9412-635b-417e-8422-b0f0c60cfec6.png)
* post 의 /new, /edit, /delete 같은 걸 컨트롤 URI 라고 함

### 참고하면 좋은 URI 설계 개념
* 문서(document)
 * 단일 개념(파일 하나, 객체 인스턴스, 데이터베이스 row)
  * 예) /members/100, /file/star.jpg
* 컬렉션(collection)
 * 서버가 관리하는 리소스 디렉토리
 * 서버가 리소스의 URI를 생성하고 관리
  * 예) /members
* 스토어(store)
 * 클라이언트가 관리하는 자원 저장소
 * 클라이언트가 리소스의 URI를 알고 관리
  * 예) /files
* 컨트롤러(controller), 컨트롤 URI
 * 문서, 컬렉션, 스토어로 해결하기 어려운 추가 프로세스 실행
 * 동사를 사용한다.
  *예) /members/{id}/delete
