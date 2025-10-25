package Testing.ApoloNotes.Modelo;

import java.util.Set;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Tag {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String nombre_tag;

    @ManyToMany(mappedBy = "etiquetas")
    private Set<Recurso> recursos;

}
