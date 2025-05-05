package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    //private final ObjectProvider<MyLogger> myloggerProvider;
    private final MyLogger mylogger; //프록시모드로 설정할떄도 이렇게 사용

    public void logic(String id) {
        //MyLogger Mylogger = myloggerProvider.getObject();
        mylogger.log("service id = " + id);
    }
}
