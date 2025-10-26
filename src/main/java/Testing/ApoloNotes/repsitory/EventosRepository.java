package Testing.ApoloNotes.repsitory;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Testing.ApoloNotes.Modelo.Eventos;

public interface EventosRepository extends JpaRepository<Eventos, Long> {

     List<Eventos> findByRecursoActivoTrue();
     List<Eventos> findByFechaDeEventoBetween(LocalDateTime start, LocalDateTime end);

}
