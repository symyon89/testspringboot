package beans.controller.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
@Profile("!uk")
@Component
public class TaxServiceNoTax implements TaxService {

    @Override
    public String calculateTax() {
        return "tax calculated NO TAX";
    }
}
