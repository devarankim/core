package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")

    void pureContainer() {

        AppConfig appConfig = new AppConfig();

        //1.조회: 호출할 떄 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2.조회 : 호출할 떄 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        // //-> 위 코드 실행하면 각자 다른 객체가 생성이 됨. 이렇게 하면 실행할 떄 마다 jvm메모리에 객체가 생성이 되어서 올라감
        // 요즘은 컴퓨터 사양도 좋고 해서 gc도 빠르게 하지만 그래도 효율적인 방법으로 개발하는게 더 좋다.
        // 싱글톤을 쓴다!

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singleTonService()  {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2); //인스턴스 비교
        //same 는 == //인스턴스가 같은지 참조 비교
        //equal는  .equals

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
      //  AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); //스프링컨테이너 사용

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2 확인. 스프링 컨테이너에서 싱글톤 관리해주는지 체크
        assertThat(memberService1).isSameAs(memberService2);
    }

}
