package beans.controller.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("uk")
public class TaxServiceUk implements TaxService {

    @Override
    public String calculateTax() {
        return "tax calculated in UK";
    }
}
