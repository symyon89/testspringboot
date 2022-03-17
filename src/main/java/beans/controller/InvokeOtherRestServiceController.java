package beans.controller;

import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("external")
public class InvokeOtherRestServiceController {

    @GetMapping("example")
    public String returnSomething() {
        return "some dummy data";
    }

    @GetMapping("callExternal")
    public String callExternal() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/external/example", String.class);
        return response.getBody() + " extra something added";
    }
    @GetMapping("callExternalWithFeign")
    public String callExternalWithFeign() {
        ExternalServiceClient client = Feign.builder()
                .client(new OkHttpClient())
//                .encoder(new GsonEncoder())
//                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ExternalServiceClient.class))
                .logLevel(Logger.Level.FULL)
                .target(ExternalServiceClient.class, "http://localhost:8080");
        return client.invokeExternalService() + " extra something added";
    }
}
