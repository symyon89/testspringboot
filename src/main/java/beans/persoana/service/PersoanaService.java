package beans.persoana.service;

import beans.aop.LogExecution;
import beans.persoana.exceptions.MyBusinessException;
import beans.persoana.model.PersoanaDto;
import beans.persoana.repository.Persoana;
import beans.persoana.repository.PersoanaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@LogExecution
public class PersoanaService {

    private final PersoanaRepository persoanaRepository;

    public List<PersoanaDto> findAll() {
        return persoanaRepository.findAll()
                .stream()
                .map(persoana ->PersoanaDto.builder()
                        .id(persoana.getId())
                        .nume(persoana.getNume())
                        .prenume(persoana.getPrenume())
                        .varsta(persoana.getVarsta())
                        .build())
                .toList();
    }
    public PersoanaDto findById(Integer id) {
        Optional<Persoana> optionalPersoana = persoanaRepository.findById(id);
        if (optionalPersoana.isEmpty()) return null;
        Persoana persoana = optionalPersoana.get();
        return PersoanaDto.builder()
                .id(persoana.getId())
                .nume(persoana.getNume())
                .prenume(persoana.getPrenume())
                .varsta(persoana.getVarsta())
                .build();
    }

    public void deleteAll() {
        persoanaRepository.deleteAll();
    }

    public void save(PersoanaDto persoanaDto) {
        Persoana persoana = new Persoana();
        persoana.setId(persoanaDto.getId());
        persoana.setNume(persoanaDto.getNume());
        persoana.setPrenume(persoanaDto.getPrenume());
        persoana.setVarsta(persoanaDto.getVarsta());
        persoanaRepository.save(persoana);
    }

    public PersoanaDto updateName(String oldName, String newName) {
        List<Persoana> list = persoanaRepository.findByNume(oldName);
        if (list.isEmpty()) return null;
        if (list.size() > 1) throw new MyBusinessException();
        Persoana persoana = list.get(0);
        persoana.setNume(newName);
        return PersoanaDto.builder()
                .id(persoana.getId())
                .nume(persoana.getNume())
                .prenume(persoana.getPrenume())
                .build();
    }

    @Transactional
    public String testTransaction() {
        {
            Persoana persoana = new Persoana();
            persoana.setId(1);
            persoana.setNume("ion");
            persoanaRepository.save(persoana);
        }

        if(true) throw new RuntimeException("beleaua");

        {
            Persoana persoana = new Persoana();
            persoana.setId(2);
            persoana.setNume("vasile");
            persoanaRepository.save(persoana);
        }

        return "All good";
    }
    public String transactionBadPractice() {
        part1();
        if (true) throw new RuntimeException("beleaua");
        part2();
        return "All good";
    }

    @Transactional
    public void part1() {
        Persoana persoana = new Persoana();
        persoana.setId(1);
        persoana.setNume("ion");
        persoanaRepository.save(persoana);
    }

    @Transactional
    public void part2() {
        Persoana persoana = new Persoana();
        persoana.setId(2);
        persoana.setNume("vasile");
        persoanaRepository.save(persoana);
    }
}
