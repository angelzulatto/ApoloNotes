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
    //private List <Etiqueta>;
    //private String ruta;

    public Recurso(String nombre,/*String etiqueta, String ruta,*/
                   LocalDateTime fechaCreacion) {
        this.nombre = nombre;
        this.contenido = null;
        //this.etiqueta = etiqueta;
        //this.ruta = ruta;
        this.fechaCreacion = fechaCreacion;
        this.recursoActivo=true;
    }

    public Recurso(){}
    
   
     public void eliminar() {
        this.recursoActivo = false;
    }
    
    // public void eliminar() {
    //     this.setRecursoActivo(false);
    // }
}

//se agregaran etiquetas