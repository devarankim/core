package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); //클래스레벨에 딱 하나만 존재.
                                                                             // 자바 뜰 떄(클래스가 최초로 메모리에 로딩될 떄) 올라감

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    public static void main(String args[]) {
        SingletonService singletonService1 = new SingletonService();
        SingletonService singletonService2 = new SingletonService();
    }

}
