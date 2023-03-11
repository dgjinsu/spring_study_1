## IP 인터넷 프로토콜

###역할
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

