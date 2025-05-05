package hello.core.singleton;

public class StatefulService {
    private int price; //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
        //return price; //무상태로 하려면 void를 int로 바꾸고 할당이 아니고 바로 리턴만 해주면 됨.
    }

    public int getPrice() {
        return price;
    }

}
