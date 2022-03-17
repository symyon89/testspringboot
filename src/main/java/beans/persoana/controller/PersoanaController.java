package beans.persoana.controller;

import beans.aop.LogExecution;
import beans.persoana.model.PersoanaDto;
import beans.persoana.service.PersoanaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("persoana")
@RequiredArgsConstructor
@LogExecution
public class PersoanaController {

    private final PersoanaService persoanaService;

    @GetMapping
    public List<PersoanaDto> findAll() {
        return persoanaService.findAll();
    }

    @GetMapping("{id}")
    public PersoanaDto findByID(@PathVariable("id") Integer id) {
        return persoanaService.findById(id);
    }

    @DeleteMapping
    public void deleteAll(HttpServletRequest request, HttpServletResponse response) {
        persoanaService.deleteAll();
    }

    @PutMapping
    public void save(@RequestBody PersoanaDto persoanaDto) {
        persoanaService.save(persoanaDto);
    }

    @PatchMapping(value = "/{old}/{new}")
    public PersoanaDto updateName(@PathVariable("old") String oldName, @PathVariable("new") String newName) {
        return persoanaService.updateName(oldName, newName);
    }

    @GetMapping(value = "/transaction")
    public String testTransaction(){
        return persoanaService.testTransaction();
    }

    @GetMapping(value = "/transactionBadPractice")
    public String transactionBadPractice(){
        return persoanaService.transactionBadPractice();
    }
}
