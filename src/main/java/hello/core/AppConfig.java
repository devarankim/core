package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Bean 메서드가 포함된 클래스에서 사용하는 어노테이션
//참고로 스프링은 @Bean메서드를 통해 스프링 빈을 생성한다.
//내부 CGLIB이라는 바이트라이브러리르 이용하여 각각 빈들에 대해 싱글톤 보장해줌
@Configuration
public class AppConfig {

    //역할
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); //생성자 주입
    }

    //역할
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); //구현클래스가 무엇인지 한 눈에 파악됨
    }

    //역할
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy()); //구현클래스가 무엇인지 한 눈에 파악됨
    }
    //DIP 준수

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy(); //구현클래스가 무엇인지 한 눈에 파악됨
    }



}
