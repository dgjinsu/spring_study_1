package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//proxyMode를 사용해서 가짜 프록스 객체를 생성해서 스프링 컨테이너에 등록함. 의존관계주입도 가짜 객체가 주입됨.
//실제 MyLogger를 사용하는 시점이 오면 진짜 객체로 대체됨!
public class MyLogger {
    private String uuid; //http요청 당 하나씩 생성되므로, uuid를 저장해농으면 다른 http요청과 구분할 수 있다
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestUrl + "]   " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString(); //생성될떄 uuid 랜덤으로 저장
        System.out.println("[" + uuid + "] request scope bean create" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close" + this);

    }
}