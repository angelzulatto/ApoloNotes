package Testing.ApoloNotes.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import Testing.ApoloNotes.repsitory.Notas_repository;
import Testing.ApoloNotes.Modelo.Notas;

@Service
 public class NotasService {

    private final Notas_repository notasRepository;

    public NotasService(Notas_repository notasRepository) {
        this.notasRepository = notasRepository;
    }

    // Crear una nueva nota
    public Notas crearNota(String nombre, LocalDateTime fechaCreacion) {
        Notas nota = new Notas(nombre, fechaCreacion);
        return notasRepository.save(nota);
    }

    // Eliminar nota (marcar como inactiva)
    public Notas eliminarNota(Long id) {
        Notas nota = notasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada con ID: " + id));
        nota.eliminar(); // cambia recursoActivo a false
        return notasRepository.save(nota);
    }

    // Buscar nota por ID
    public Notas obtenerNotaPorId(Long id) {
        return notasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada con ID: " + id));
    }

    // Listar todas las notas
    public List<Notas> listarTodasNotas() {
        return notasRepository.findAll();
    }

     // Listar solo notas activas
     public List<Notas> listarNotasActivas() {
         return notasRepository.findByRecursoActivoTrue();
     }


     // Modificar una nota existente
    public Notas actualizarNota(Long id, String nuevoNombre, String nuevoContenido, Boolean nuevoEstado) {
    Notas notaExistente = notasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Nota no encontrada con ID: " + id));

    // Actualizamos los campos solo si se pasan
    if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
        notaExistente.setNombre(nuevoNombre);
    }
    if (nuevoContenido != null && !nuevoContenido.isEmpty()) {
        notaExistente.setContenido(nuevoContenido);
    }
    if (nuevoEstado != null) {
        notaExistente.setRecursoActivo(nuevoEstado);
    }
    
    // Guardamos los cambios
    return notasRepository.save(notaExistente);
    }   
}
