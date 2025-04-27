package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    /* 1)
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //이 방식은 DIP를 지킨 것은 아니다. 추상(인터페이스)뿐만 아니라 구체(구현)클래스에도 의존하고 있다. DIP위반
    //또한 서비스임플 소스도 변경해야 하기 때문에 OCP위반
    // 2) 방법으로 사용해야함.
     */

    /* 2)
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy; //인터페이스만 의존
     */

    //3) 관심사주입으로 인터페이스에만 의존하도록 처리 --> 이렇게 처리해야함
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
