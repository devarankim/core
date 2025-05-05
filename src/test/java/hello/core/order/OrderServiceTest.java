package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    //OrderService orderService = new OrderServiceImpl();

    //Appconfig이용한 테스트
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);


    }
    
    /*
    void fieldInjectionTest() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.createOrder(1, "Test", 1000);
        //NULL에러남
        //테스트하려면 SETTER를 만들어야함.
        //스프링컨테이너에서 관리하는 빈을 가져와야 함. 내가 직접 new로 생성하는 애는 오토와이어드가 안됨. 사용하지 말자
        // 스프링컨테이너를 사용하지 않고 new로 객체를 직접 생성했기 때문에 @autowried가 붙은 setter메서드는 호출되지 않았음.
           결국 null인채로 createOrder 메서드가 실행됨.
    }
    */
}
