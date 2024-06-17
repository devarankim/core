package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() { //junit5부턴 public 설정 안해도 됨.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { //iter 하고 탭 누르면 자동으로 for문 만들어 줌
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName +  = " + beanDefinitionName + " object= " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() { //junit5부턴 public 설정 안해도 됨.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { //iter 하고 탭 누르면 자동으로 for문 만들어 줌
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하느 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){ //개발자가 애플리케이션 주로 개발하가ㅣ 위해 등록한 빈들을 출력하게만 함
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName +  = " + beanDefinitionName +" object= "+ bean);
            }
        }
    }
}
