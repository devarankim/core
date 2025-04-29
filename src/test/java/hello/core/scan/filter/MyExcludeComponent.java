package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //타겟이 중요함. TYPE는 클래스 레벨에 붙는거
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
