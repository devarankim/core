package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final붙은거를 가지고 생성자를 만들어준다. ctrl+f12 누르면 확인 가능
public class OrderServiceImpl implements OrderService{

    /* 1)
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //이 방식은 DIP를 지킨 것은 아니다. 추상(인터페이스)뿐만 아니라 구체(구현)클래스에도 의존하고 있다. DIP위반
    //또한 서비스입플 소스도 변경해야 하기 때문에 OCP위반
    // 2) 방법으로 사용해야함.
     */

    /* 2)
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy; //인터페이스만 의존
     */

    //3) 관심사주입으로 인터페이스에만 의존하도록 처리 --> 이렇게 처리해야함
    private final MemberRepository memberRepository; //한번 생성할 때 정해지면 안 바뀜. 컴파일 시점에 오류도 내줌
    private final DiscountPolicy discountPolicy;


    //@RequiredArgsConstructor 를 사용하면 생성자를 직접 만들 필요가 없다. final붙은 클래스를 가지고 주입관계 만들어줌. 주석처리한 클래스와 동일하게 해줌
    @Autowired
    //public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
   // public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("fixDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }

    //테스트 용도
    //MemberRepository를 조회할 수 있는 기능을 추가한다. 기능 검증을 위해 잠깐 사용하는 것이니 인터페이스에 조회기능까지 추가하지는 말자.
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
