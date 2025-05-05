package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class); //해당 스프링컨테이너는 TestConfig만 쓰는거다
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        
        statefulService1.order("userA", 10000);
        //무상태 : int userAPrice = statefulService1.order("userA", 10000); 하면 됨. 지역변수라서 a랑 b의 가격이 다름
        statefulService1.order("userB", 20000);
        
        // ThreadA : 사용자A 주문금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); //20000. 같은 객체를 참조하기 때문에 값 변함

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}