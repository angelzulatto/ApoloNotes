package Testing.ApoloNotes.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import Testing.ApoloNotes.repsitory.Notas_repository;
import Testing.ApoloNotes.repsitory.TagRepository;
import Testing.ApoloNotes.Modelo.Notas;
import Testing.ApoloNotes.Modelo.Tag;

@Service
 public class NotasService {

    private final Notas_repository notasRepository;
    private final TagRepository tagRepository;

    public NotasService(Notas_repository notasRepository, TagRepository tagRepository) {
    this.notasRepository = notasRepository;
    this.tagRepository = tagRepository;
}

    // Crear una nueva nota
    public Notas crearNota(String nombre,String listag , LocalDateTime fechaCreacion, String contenido) {
    if (nombre == null || nombre.trim().isEmpty()) {
        throw new IllegalArgumentException("El nombre de la nota es obligatorio");
    }
    if (contenido == null || contenido.trim().isEmpty()) {
        throw new IllegalArgumentException("La descripción (contenido) es obligatoria");
    }

    Notas nota = new Notas(nombre, listag , fechaCreacion, contenido);
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

    //agregar tag
    // public Notas agregarEtiquetas(Long notaId, List<String> nombresEtiquetas) {
    // Notas nota = notasRepository.findById(notaId)
    //     .orElseThrow(() -> new RuntimeException("Nota no encontrada"));

    // for (String nombre : nombresEtiquetas) {
    //     Tag etiqueta = tagRepository.findByNombre(nombre);
    //     if (etiqueta == null) {
    //         etiqueta = new Tag();
    //         etiqueta.setNombre_tag(nombre);
    //         tagRepository.save(etiqueta);
    //     }
    //     nota.getEtiquetas().add(etiqueta);
    // }
    //     return notasRepository.save(nota);
    // }


        public Notas agregarEtiquetas(Long eventoId, List<String> nombresEtiquetas) {
    Notas nota = notasRepository.findById(eventoId)
        .orElseThrow(() -> new RuntimeException("Nota no encontrada"));

    for (String nombre : nombresEtiquetas) {
        Tag etiqueta = tagRepository.findByNombreTag(nombre);
        if (etiqueta == null) {
            etiqueta = new Tag();
            etiqueta.setNombreTag(nombre);
            tagRepository.save(etiqueta);
        }

        // Agregar nombre al string taglist
        String actual = nota.getTaglist();
        if (actual == null || actual.isEmpty()) {
           nota.setTaglist(nombre);
        } else if (!actual.contains(nombre)) {
            nota.setTaglist(actual + "," + nombre);
        }
    }

    return notasRepository.save(nota);
}

    
}
