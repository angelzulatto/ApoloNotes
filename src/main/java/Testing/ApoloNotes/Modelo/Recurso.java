package Testing.ApoloNotes.Modelo;

import java.time.LocalDateTime;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private String taglist;
    //private String ruta;

    public Recurso(String nombre, /*String ruta,*/String taglist,
                   LocalDateTime fechaCreacion,String contenido) {
        this.nombre = nombre;
        this.contenido = contenido;
        //this.ruta = ruta;
        this.fechaCreacion = fechaCreacion;
        this.recursoActivo=true;
        this.taglist=taglist;

    }

    public Recurso(){}
    
   
     public void eliminar() {
        this.recursoActivo = false;
    }
    
    // @ManyToMany
    // @JoinTable(
    // name = "Recurso_etiqueta",
    // joinColumns = @JoinColumn(name = "recurso_id"),
    // inverseJoinColumns = @JoinColumn(name = "etiqueta_id")
    // )
    // private Set<Tag> etiquetas = new HashSet<>();


    
}

//C