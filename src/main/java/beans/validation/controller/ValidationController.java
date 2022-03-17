package beans.validation.controller;

import beans.validation.model.Model;
import beans.validation.service.ValidationService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.Validator;

@RestController
@RequestMapping(value = "validate")
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService service;


    @PutMapping(value = "rest")
    public Model invokeRest(@Parameter(description = "Model to validate in REST") @Valid @RequestBody Model model) {
        return model;
    }

    @PutMapping(value = "service")
    public Model invokeService(@Parameter(description = "Model to validate in Service", required = true) @RequestBody Model model) {
        return service.invoke(model);
    }

}
