package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A용자가 10000원 주문
        int userA = statefulService1.order("userA", 10000);
        //ThreadA: B용자가 20000원 주문
        int userB = statefulService2.order("userB", 20000);

        //ThreadA: A용자가 주문 금액 조회
        System.out.println("price = " + userA);

        //특정 클라이언트가 값을 변경할수 있는 필드가 존재함! .. -> 스프링은 항상 무상태로 설계해야한다.

        Assertions.assertThat(userA).isEqualTo(10000);

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}