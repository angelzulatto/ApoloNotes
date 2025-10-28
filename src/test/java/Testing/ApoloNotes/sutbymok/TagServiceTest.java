package Testing.ApoloNotes.sutbymok;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Testing.ApoloNotes.Modelo.Tag;
import Testing.ApoloNotes.sutbymok.TagServiceStub;
import Testing.ApoloNotes.Service.TagService;

import java.util.List;

public class TagServiceTest {

    private TagService tagService;

    @BeforeEach
    public void setup() {
        tagService = new TagServiceStub(); 
    }

    @Test
    public void testCrearTag() {
        Tag nuevo = new Tag();
        nuevo.setNombreTag("NuevoTag");
        Tag creado = tagService.crearTag(nuevo);
        
        assertNotNull(creado.getId());
        assertEquals("NuevoTag", creado.getNombreTag());
    }

    @Test
    public void testObtenerTodosTags() {
        List<Tag> tags = tagService.obtenerTodosTags();
        assertTrue(tags.size() >= 2); 
    }

    @Test
    public void testActualizarTag() {
        Tag actualizado = new Tag();
        actualizado.setNombreTag("UrgenteModificado");
        Tag tag = tagService.actualizarTag(1L, actualizado);
        assertEquals("UrgenteModificado", tag.getNombreTag());
    }

    @Test
    public void testEliminarTag() {
        tagService.eliminarTag(1L);
        assertTrue(tagService.obtenerTodosTags().stream()
                   .noneMatch(t -> t.getId().equals(1L)));
    }
}
