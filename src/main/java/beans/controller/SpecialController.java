package beans.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("special")
public class SpecialController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public ResponseEntity<String> sayHello() {
       // return ResponseEntity.status(201).contentType(MediaType.TEXT_PLAIN).body("Hello World");
        byte[] bytes = {1,2,3,4};
        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_OCTET_STREAM).body(bytes.toString());
    }
}
