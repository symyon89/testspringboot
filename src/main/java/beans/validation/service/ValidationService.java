package beans.validation.service;

import beans.validation.exceptions.ValidateNameMarinBusinessException;
import beans.validation.model.Model;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated// in order to tell Spring to validate this service
public class ValidationService {

    public Model invoke(@Valid Model model) {
        if(model.getName().equalsIgnoreCase("marin")){
            throw new ValidateNameMarinBusinessException("nu imi place de marin");
        }
        return model;
    }

}
