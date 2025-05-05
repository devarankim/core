package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor //생성자에 final변수 자동으로 들어오면서 의존관계 주입해줌
public class LogDemoController {

    private final LogDemoService logDemoService;
    //private final ObjectProvider<MyLogger> myLoggerPrivider;
    private final MyLogger mylogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        //MyLogger mylogger = myLoggerPrivider.getObject();

        mylogger.setRequestURL(requestURL);
        mylogger.log("controller test");

        logDemoService.logic("testId");

        return "0k";
    }



}
