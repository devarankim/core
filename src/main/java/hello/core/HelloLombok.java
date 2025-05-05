package hello.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class HelloLombok {

    private String name;
    private int age;

}
