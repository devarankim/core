package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
