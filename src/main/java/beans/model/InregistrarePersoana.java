package beans.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class InregistrarePersoana {
    @Schema(description = "numele persoanei", example = "1", required = true)
    private String nume;
    @Schema(description = "prenumele persoanei", example = "1", required = true)
    private String prenume;
    private Integer varsta;

}