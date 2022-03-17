package beans.persoana;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
// @SessionScope - tine cat o sesiune http
//@ApplicationScope - tine cat aplicatia web e asemanator cu un singleton legat de containerul aplicatiei
@RequestScope // tine o perioada scurta cat timp este procesata o cerere http
public class RequestScopeModel {
}
