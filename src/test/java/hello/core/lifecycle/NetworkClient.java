package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient {
    //public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close: "+ url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetowrkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }
    @PreDestroy
    public void close() {
        System.out.println("NetowrkClient.close");
        disconnect();
    }

/*
    @Override
    public void afterPropertiesSet() throws Exception { //의존관계 주입 끝나고 실행
        System.out.println("NetowrkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");

    }

    @Override
    public void destroy() throws Exception { //빈이 종료될때호출
        disconnect();
    }
 */
}
