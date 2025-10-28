package Testing.ApoloNotes.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import Testing.ApoloNotes.Modelo.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByNombreTag(String nombreTag);
}
