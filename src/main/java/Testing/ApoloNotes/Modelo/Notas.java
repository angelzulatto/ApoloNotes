package Testing.ApoloNotes.Modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity

@Setter
@Getter
public class Notas extends Recurso {

    public Notas(String nombre, LocalDateTime fechaCreacion) {
        super(nombre, fechaCreacion);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void guardar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }
    
  
}
