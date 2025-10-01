package  Testing.ApoloNotes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Testing.ApoloNotes.Modelo.Notas;
import Testing.ApoloNotes.Service.NotasService;
import Testing.ApoloNotes.repsitory.Notas_repository;

class NotasServiceTest {

    @Mock
    private Notas_repository notasRepository;

    @InjectMocks
    private NotasService notasService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // crear nota correctamente
    @Test
    void crearNota_DeberiaGuardarNotaSiDatosSonValidos() {
        
        String nombre = "Nota de prueba";
        String contenido = "Contenido de prueba";
        LocalDateTime fecha = LocalDateTime.now();

        Notas notaMock = new Notas(nombre, fecha, contenido);
        when(notasRepository.save(any(Notas.class))).thenReturn(notaMock);

        Notas resultado = notasService.crearNota(nombre, fecha, contenido);

        assertNotNull(resultado);
        assertEquals(nombre, resultado.getNombre());
        assertEquals(contenido, resultado.getContenido());
        verify(notasRepository, times(1)).save(any(Notas.class));
    }

    // nombre vacío
    @Test
    void crearNota_DeberiaLanzarExcepcionSiNombreEsVacio() {

        String nombre = "   ";
        String contenido = "Contenido válido";
        LocalDateTime fecha = LocalDateTime.now();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> notasService.crearNota(nombre, fecha, contenido)
        );

        assertEquals("El nombre de la nota es obligatorio", exception.getMessage());
        verify(notasRepository, never()).save(any(Notas.class));
    }

    // contenido vacío
    @Test
    void crearNota_DeberiaLanzarExcepcionSiContenidoEsVacio() {
        String nombre = "Nombre válido";
        String contenido = "   ";
        LocalDateTime fecha = LocalDateTime.now();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> notasService.crearNota(nombre, fecha, contenido)
        );

        assertEquals("La descripción (contenido) es obligatoria", exception.getMessage());
        verify(notasRepository, never()).save(any(Notas.class));
    }
}
