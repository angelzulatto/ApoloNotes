package Testing.ApoloNotes.Modelo;

import java.time.LocalDateTime;

public class Eventos extends Recurso {

    LocalDateTime fechaDeEvento;
   
    public Eventos(){};

    public Eventos(String nombre, LocalDateTime fechaCreacion,String contenido,LocalDateTime fechaDeEvento ) {
        super(nombre, fechaCreacion, contenido);
        this.fechaDeEvento=fechaDeEvento;
    }
    
    public LocalDateTime getFechaDeEvento(){
        return fechaDeEvento;
    }

    public void setFechaDeEvento(LocalDateTime fechaDeEvento ){
        this.fechaDeEvento=fechaDeEvento;
    }

}
