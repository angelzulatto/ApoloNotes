package Testing.ApoloNotes.sutbymok;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import Testing.ApoloNotes.Modelo.Tag;
import Testing.ApoloNotes.Service.TagService;
import Testing.ApoloNotes.repsitory.TagRepository;

public class TagServiceMockTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearTag() {
        Tag tag = new Tag();
        tag.setNombreTag("Importante");

        when(tagRepository.save(any(Tag.class))).thenReturn(new Tag());


        Tag resultado = tagService.crearTag(tag);

        assertEquals("Importante", resultado.getNombreTag());
        verify(tagRepository, times(1)).save(tag);
    }

    @Test
    void actualizarTag() {
        Tag existente = new Tag();
        existente.setId(1L);
        existente.setNombreTag("Viejo");

        Tag nuevo = new Tag();
        nuevo.setNombreTag("Nuevo");

        when(tagRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(tagRepository.save(any(Tag.class))).thenReturn(new Tag());


        Tag actualizado = tagService.actualizarTag(1L, nuevo);

        assertEquals("Nuevo", actualizado.getNombreTag());
        verify(tagRepository).findById(1L);
        verify(tagRepository).save(existente);
    }

    @Test
    void eliminarTag_SiExiste() {
        when(tagRepository.existsById(1L)).thenReturn(true);

        tagService.eliminarTag(1L);

        verify(tagRepository).deleteById(1L);
    }

    @Test
    void eliminarTag_SiNoExiste() {
        when(tagRepository.existsById(99L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            tagService.eliminarTag(99L);
        });

        assertEquals("Tag no encontrado con id 99", ex.getMessage());
        verify(tagRepository, never()).deleteById(any());
    }
}
