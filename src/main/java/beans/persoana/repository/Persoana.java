package beans.persoana.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Persoana {
    @Id
    private Integer id;
    private String nume;
    private String prenume;
    private Integer varsta;
}
