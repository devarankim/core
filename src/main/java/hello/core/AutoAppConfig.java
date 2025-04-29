package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", //설정한 패키지 하위 클래스들만 빈 등록해주는 옵션?
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
) //FilterType.ANNOTATION = 애노테이션과 관련된 필터를 설정
  //AppConfig.class가 자동으로 빈 등록되지 않도록 (스캔되지  않도록) 임의로 뻈음.
  //Component 애노테이션이 붙은 클래스들을 자동으로 빈 등록해줌
public class AutoAppConfig {

    @Bean(name="memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
