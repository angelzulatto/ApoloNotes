package Testing.ApoloNotes.Modelo;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;

@Entity


public class Notas extends Recurso {


    public Notas(){}

    public Notas(String nombre,String listag, LocalDateTime fechaCreacion,String contenido ) {
        super(nombre,listag, fechaCreacion, contenido);
    }
    

}
