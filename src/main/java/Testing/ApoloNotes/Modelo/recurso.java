package Testing.ApoloNotes.Modelo;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private LocalDateTime fechaCreacion;
    private Boolean Recurso_activo;
    private String Cuerpo_del_recurso;
    //private List <Etiqueta>;
    //private String ruta;

    public Recurso(String nombre,/*String etiqueta, String ruta,*/
                   LocalDateTime fechaCreacion) {
        this.nombre = nombre;
        this.Cuerpo_del_recurso = null;
        //this.etiqueta = etiqueta;
        //this.ruta = ruta;
        this.fechaCreacion = fechaCreacion;
        this.Recurso_activo=true;
    }

    public abstract void guardar();
    public void eliminar() {
        this.setRecurso_activo(false);
    }
}

//se agregaran etiquetas