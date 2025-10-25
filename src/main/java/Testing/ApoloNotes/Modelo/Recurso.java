package Testing.ApoloNotes.Modelo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass                 // Esto le dice a JPA que las subclases heredan estos campos en la BD
public abstract class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private LocalDateTime fechaCreacion;
    private Boolean recursoActivo;
    private String contenido;
    //private List <Etiqueta>;
    //private String ruta;

    public Recurso(String nombre, /*String ruta,*/
                   LocalDateTime fechaCreacion,String contenido) {
        this.nombre = nombre;
        this.contenido = contenido;
        //this.ruta = ruta;
        this.fechaCreacion = fechaCreacion;
        this.recursoActivo=true;
    }

    public Recurso(){}
    
   
     public void eliminar() {
        this.recursoActivo = false;
    }
    
    @ManyToMany
    @JoinTable(
    name = "Recurso_etiqueta",
    joinColumns = @JoinColumn(name = "recurso_id"),
    inverseJoinColumns = @JoinColumn(name = "etiqueta_id")
    )
    private Set<Tag> etiquetas = new HashSet<>();


    
}

//C