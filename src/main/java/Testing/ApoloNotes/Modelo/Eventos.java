package Testing.ApoloNotes.Modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity


public class Eventos extends Recurso {

    LocalDateTime fechaDeEvento;
   
    public Eventos(){};

    public Eventos(String nombre,String listag, LocalDateTime fechaCreacion,String contenido,LocalDateTime fechaDeEvento ) {
        super(nombre,listag, fechaCreacion, contenido);
        this.fechaDeEvento=fechaDeEvento;
    }
    
    public LocalDateTime getFechaDeEvento(){
        return fechaDeEvento;
    }

    public void setFechaDeEvento(LocalDateTime fechaDeEvento ){
        this.fechaDeEvento=fechaDeEvento;
    }

}
