package beans.persoana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersoanaRepository extends JpaRepository<Persoana, Integer> {
    List<Persoana> findByNume(String oldName);
}