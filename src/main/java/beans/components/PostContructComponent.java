package beans.components;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PostContructComponent {

    @PostConstruct
    public void init() {
        System.out.println("hello world");
    }
}
