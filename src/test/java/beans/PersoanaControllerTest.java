package beans;

import beans.persoana.model.PersoanaDto;
import beans.persoana.repository.Persoana;
import beans.persoana.repository.PersoanaRepository;
import beans.persoana.service.PersoanaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PersoanaControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    PersoanaService persoanaService;

    @Autowired
    PersoanaRepository persoanaRepository;

    @BeforeEach
    public void cleanupDatabase() {
        persoanaService.deleteAll();
    }

    @Test
    public void testCRUD() throws Exception {
        mvc.perform(get("/persoana")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
        PersoanaDto persoanaDto = PersoanaDto.builder().id(1).nume("ion").prenume("vasile").varsta(30).build();
        mvc.perform(put("/persoana")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(persoanaDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
        mvc.perform(get("/persoana/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(persoanaDto)));
        mvc.perform(delete("/persoana"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
        mvc.perform(get("/persoana")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    public void testPatch() throws Exception {

        Persoana persoana = new Persoana();
        persoana.setId(1);
        persoana.setNume("ion");
        persoana.setPrenume("vasile");
        persoanaRepository.save(persoana);

        // GET by ID and expect something
        mvc.perform(get("/persoana/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(persoana)));

        // PATCH update name and expect result
        mvc.perform(patch("/persoana/ion/marin"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "  \"id\" : 1,\n" +
                        "  \"nume\" : \"marin\",\n" +
                        "  \"prenume\" : \"vasile\",\n" +
                        "  \"varsta\" : null\n" +
                        "}"));
    }

    @Test
    public void testPatchFail() throws Exception {

        {
            Persoana persoana = new Persoana();
            persoana.setId(1);
            persoana.setNume("ion");
            persoana.setPrenume("vasile");
            persoanaRepository.save(persoana);
        }
        {
            Persoana persoana = new Persoana();
            persoana.setId(2);
            persoana.setNume("ion");
            persoana.setPrenume("marcu");
            persoanaRepository.save(persoana);
        }

        // PATCH update name and expect error
        mvc.perform(patch("/persoana/ion/florea"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("too many persons with same name"));

    }
}