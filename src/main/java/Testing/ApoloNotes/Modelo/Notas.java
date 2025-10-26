package Testing.ApoloNotes.Modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity


public class Notas extends Recurso {


    public Notas(){}

    public Notas(String nombre, LocalDateTime fechaCreacion,String contenido ) {
        super(nombre, fechaCreacion, contenido);
    }
    

}
