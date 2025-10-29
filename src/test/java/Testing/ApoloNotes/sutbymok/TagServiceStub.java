package Testing.ApoloNotes.sutbymok;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Testing.ApoloNotes.Modelo.Tag;
import Testing.ApoloNotes.Service.TagService;

public class TagServiceStub extends TagService {

    private final List<Tag> tags = new ArrayList<>();

    public TagServiceStub() {
        super(null); 
        tags.add(new Tag() {{ setId(1L); setNombreTag("Urgente"); }});
        tags.add(new Tag() {{ setId(2L); setNombreTag("Importante"); }});
    }

    @Override
    public Tag crearTag(Tag tag) {
        tag.setId((long) (tags.size() + 1));
        tags.add(tag);
        return tag;
    }

    @Override
    public List<Tag> obtenerTodosTags() {
        return new ArrayList<>(tags);
    }

    @Override
    public Optional<Tag> obtenerTagPorId(Long id) {
        return tags.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    @Override
    public Tag obtenerTagPorNombre(String nombreTag) {
        return tags.stream()
                   .filter(t -> t.getNombreTag().equals(nombreTag))
                   .findFirst()
                   .orElse(null);
    }

    @Override
    public Tag actualizarTag(Long id, Tag tagActualizado) {
        Tag tag = tags.stream().filter(t -> t.getId().equals(id)).findFirst()
                      .orElseThrow(() -> new RuntimeException("Tag no encontrado con id " + id));
        tag.setNombreTag(tagActualizado.getNombreTag());
        return tag;
    }

    @Override
    public void eliminarTag(Long id) {
        tags.removeIf(t -> t.getId().equals(id));
    }
}

