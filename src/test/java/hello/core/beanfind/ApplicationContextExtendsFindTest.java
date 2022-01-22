package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {
     AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

     @Test
     @DisplayName("부모 타입으로 조회, 자식이 둘 이상 있으면 중복 오류가 발생한다.")
     void findBeanByParentTypeDuplicate(){
         //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
         Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
     }

    @Test
    @DisplayName("부모 타입으로 조회, 자식이 둘 이상 있으면 빈 이름 지정.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
        }

    @Test
    @DisplayName("부모 타입으로 전부 조회.")
    void findBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 전부 조회.(object)")
    void  findBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String s : beansOfType.keySet()) {
            System.out.println("key = " + s + " value = " + beansOfType.get(s));
        }
    }

     @Configuration
    static class TestConfig{
         @Bean
         public DiscountPolicy rateDiscountPolicy(){
             return new RateDiscountPolicy();
         }
         @Bean
         public DiscountPolicy fixDiscountPolicy(){
             return new FixDiscountPolicy();
         }
     }
}
