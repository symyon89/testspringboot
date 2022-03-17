package beans.controller.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;



@Component
@Profile("ro")
//@Primary ser poate pune asa
public class TaxServiceRo implements TaxService {
    @Override
    public String calculateTax() {
        return "tax calculated in Romania";
    }
}
