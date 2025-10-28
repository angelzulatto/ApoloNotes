package Testing.ApoloNotes.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Testing.ApoloNotes.Modelo.Tag;
import Testing.ApoloNotes.repsitory.TagRepository;

@Service
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // Crear un nuevo Tag
    public Tag crearTag(Tag tag) {
        return tagRepository.save(tag);
    }

    // Consultar todos los Tags
    public List<Tag> obtenerTodosTags() {
        return tagRepository.findAll();
    }

    // Consultar un Tag por ID
    public Optional<Tag> obtenerTagPorId(Long id) {
        return tagRepository.findById(id);
    }

    // Consultar un Tag por nombre
    public Tag obtenerTagPorNombre(String nombreTag) {
        return tagRepository.findByNombreTag(nombreTag);
    }

    // Modificar un Tag existente
    public Tag actualizarTag(Long id, Tag tagActualizado) {
        return tagRepository.findById(id)
            .map(tag -> {
                tag.setNombreTag(tagActualizado.getNombreTag());
                return tagRepository.save(tag);
            })
            .orElseThrow(() -> new RuntimeException("Tag no encontrado con id " + id));
    }

    // Eliminar un Tag por ID
    public void eliminarTag(Long id) {
        if(!tagRepository.existsById(id)) {
            throw new RuntimeException("Tag no encontrado con id " + id);
        }
        tagRepository.deleteById(id);
    }
}
