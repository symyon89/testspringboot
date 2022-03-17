package beans.controller;

import beans.model.ConfirmareInregistrarePersoana;
import beans.model.InregistrarePersoana;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@OpenAPIDefinition(info = @Info(title = "Simple Controller", description = "simple controller used as example", version = "1"))
@RestController
@RequestMapping(value = "/simple")
public class SimpleController {
    @GetMapping(value = "hello/{nume}")
    public String replyHello(@PathVariable(name = "nume") String nume) {
        return "hello " + nume;
    }

    @GetMapping(value = "goodbye")
    public String replyGoodbye(@RequestParam(name = "nume") String nume, @RequestParam(name = "prenume") String prenume) {
        return "goodbye " + nume + " " + prenume;
    }

    @Operation(summary = "POST request body", description = "POST with request body example")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all good"),
            @ApiResponse(responseCode = "400", description = "s-a stricat ceva")
    }
    )
    @PostMapping(value = "inregistrare")
    public ResponseEntity<ConfirmareInregistrarePersoana> inregistrare(@RequestBody InregistrarePersoana inregistrarePersoana) {
//        if(inregistrarePersoana.getVarsta()<10) {
//            return new ResponseEntity<>("age too low ", HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(ConfirmareInregistrarePersoana.builder()
                .nume(inregistrarePersoana.getNume())
                .prenume(inregistrarePersoana.getPrenume())
                .varsta(inregistrarePersoana.getVarsta())
                .numarInregistrare(new Date().getTime())
                .build(), HttpStatus.ACCEPTED);
    }
}
