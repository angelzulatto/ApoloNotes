package Testing.ApoloNotes.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Testing.ApoloNotes.Modelo.Eventos;
import Testing.ApoloNotes.Service.EventosService;

@RestController
@RequestMapping("/eventos")
public class EventosController {

    @Autowired
    private EventosService eventosService;

    // Crear evento
    @PostMapping
    public ResponseEntity<Eventos> crearEvento(@RequestBody Eventos evento) {
        Eventos nuevoEvento = eventosService.crearEvento(
                evento.getNombre(),
                evento.getTaglist(),
                evento.getFechaCreacion(),
                evento.getContenido(),
                evento.getFechaDeEvento()
        );
        return ResponseEntity.ok(nuevoEvento);
    }

    // Eliminar evento (marcar como inactivo)
    @DeleteMapping("/{id}")
    public ResponseEntity<Eventos> eliminarEvento(@PathVariable Long id) {
        Eventos eventoEliminado = eventosService.eliminarEvento(id);
        return ResponseEntity.ok(eventoEliminado);
    }

    // Obtener evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Eventos> obtenerEventoPorId(@PathVariable Long id) {
        Eventos evento = eventosService.obtenerEventoPorId(id);
        return ResponseEntity.ok(evento);
    }

    // Listar todos los eventos
    @GetMapping
    public ResponseEntity<List<Eventos>> listarTodos() {
        List<Eventos> eventos = eventosService.listarTodos();
        return ResponseEntity.ok(eventos);
    }

    // Listar solo eventos activos
    @GetMapping("/activos")
    public ResponseEntity<List<Eventos>> listarActivos() {
        List<Eventos> eventosActivos = eventosService.listarActivos();
        return ResponseEntity.ok(eventosActivos);
    }

    // Actualizar evento
    @PutMapping("/{id}")
    public ResponseEntity<Eventos> actualizarEvento(
            @PathVariable Long id,
            @RequestBody Eventos eventoActualizado) {

        Eventos evento = eventosService.actualizarEvento(
                id,
                eventoActualizado.getNombre(),
                eventoActualizado.getTaglist(),
                eventoActualizado.getContenido(),
                eventoActualizado.getFechaDeEvento(),
                eventoActualizado.getRecursoActivo()
        );
        return ResponseEntity.ok(evento);
    }

    // Agregar etiquetas a un evento
    @PutMapping("/{id}/etiquetas")
    public ResponseEntity<Eventos> agregarEtiquetas(
            @PathVariable Long id,
            @RequestBody List<String> etiquetas) {
        return ResponseEntity.ok(eventosService.agregarEtiquetas(id, etiquetas));
    }

    @GetMapping("/eventos/hoy")
    public List<Eventos> eventosDelDia() {
    LocalDateTime hoy = LocalDateTime.now();
    return eventosService.obtenerEventosDeHoy(hoy);
}


}
