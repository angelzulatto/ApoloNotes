package Testing.ApoloNotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Testing.ApoloNotes.Modelo.Notas;
import Testing.ApoloNotes.Service.NotasService;

@RestController
@RequestMapping("/notas")
public class NotasController {

    @Autowired
    private NotasService notasService;

    // Crear nota
    @PostMapping
    public ResponseEntity<Notas> crearNota(@RequestBody Notas notas) {
        Notas nuevaNota = notasService.crearNota(
                notas.getNombre(),
                notas.getFechaCreacion()
        );
        return ResponseEntity.ok(nuevaNota);
    }

    // Eliminar nota (marcar como inactiva)
    @DeleteMapping("/{id}")
    public ResponseEntity<Notas> eliminarNota(@PathVariable Long id) {
        Notas notaEliminada = notasService.eliminarNota(id);
        return ResponseEntity.ok(notaEliminada);
    }

    // Buscar nota por ID
    @GetMapping("/{id}")
    public ResponseEntity<Notas> obtenerNotaPorId(@PathVariable Long id) {
        Notas nota = notasService.obtenerNotaPorId(id);
        return ResponseEntity.ok(nota);
    }

    // Listar todas las notas
    @GetMapping
    public ResponseEntity<List<Notas>> listarTodasNotas() {
        List<Notas> notas = notasService.listarTodasNotas();
        return ResponseEntity.ok(notas);
    }

    // Listar solo notas activas
    @GetMapping("/activas")
    public ResponseEntity<List<Notas>> listarNotasActivas() {
        List<Notas> notasActivas = notasService.listarNotasActivas();
        return ResponseEntity.ok(notasActivas);
    }
}


