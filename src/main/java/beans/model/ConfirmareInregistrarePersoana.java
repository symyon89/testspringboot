package beans.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfirmareInregistrarePersoana {
    @Schema(description = "numarul cererii de inregistrare", example = "1", required = true)
    private String nume;
    private String prenume;
    private Integer varsta;
    private Long numarInregistrare;
}
