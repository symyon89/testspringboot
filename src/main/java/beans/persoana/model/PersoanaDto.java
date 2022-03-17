package beans.persoana.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersoanaDto {

    private Integer id;
    private String nume;
    private String prenume;
    private Integer varsta;
}
