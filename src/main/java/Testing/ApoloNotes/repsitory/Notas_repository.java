package Testing.ApoloNotes.repsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Testing.ApoloNotes.Modelo.Notas;

@Repository
public interface Notas_repository extends JpaRepository<Notas, Long> {

    List<Notas> findByRecursoActivoTrue();
    
}
