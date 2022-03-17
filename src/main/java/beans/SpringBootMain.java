package beans;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMain implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.err.println("starting up");
    }
}
