package Testing.ApoloNotes.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Testing.ApoloNotes.Modelo.Tag;
import Testing.ApoloNotes.Service.TagService;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    // Crear un nuevo Tag
    @PostMapping
    public ResponseEntity<Tag> crearTag(@RequestBody Tag tag) {
        Tag nuevoTag = tagService.crearTag(tag);
        return ResponseEntity.ok(nuevoTag);
    }

    // Obtener todos los Tags
    @GetMapping
    public ResponseEntity<List<Tag>> obtenerTodosTags() {
        List<Tag> tags = tagService.obtenerTodosTags();
        return ResponseEntity.ok(tags);
    }

    // Obtener un Tag por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tag> obtenerTagPorId(@PathVariable Long id) {
        Optional<Tag> tag = tagService.obtenerTagPorId(id);
        return tag.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // Obtener un Tag por nombre
    @GetMapping("/nombre/{nombreTag}")
    public ResponseEntity<Tag> obtenerTagPorNombre(@PathVariable String nombreTag) {
        Tag tag = tagService.obtenerTagPorNombre(nombreTag);
        if (tag != null) {
            return ResponseEntity.ok(tag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Modificar un Tag existente
    @PutMapping("/{id}")
    public ResponseEntity<Tag> actualizarTag(@PathVariable Long id, @RequestBody Tag tagActualizado) {
        try {
            Tag tag = tagService.actualizarTag(id, tagActualizado);
            return ResponseEntity.ok(tag);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un Tag por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTag(@PathVariable Long id) {
        try {
            tagService.eliminarTag(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
