// package  Testing.ApoloNotes;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// import java.time.LocalDateTime;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import Testing.ApoloNotes.Modelo.Notas;
// import Testing.ApoloNotes.Service.NotasService;
// import Testing.ApoloNotes.repsitory.Notas_repository;

// class NotasServiceTest {
//     @Mock
//     private Notas_repository notasRepository;

//     @InjectMocks
//     private NotasService notasService;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     // crear nota correctamente
//     @Test
//     void crearNota_DeberiaGuardarNotaSiDatosSonValidos() {
        
//         String nombre = "Nota de prueba";
//         String contenido = "Contenido de prueba";
//         LocalDateTime fecha = LocalDateTime.now();

//         Notas notaMock = new Notas(nombre, fecha, contenido);
//         when(notasRepository.save(any(Notas.class))).thenReturn(notaMock);

//         Notas resultado = notasService.crearNota(nombre, fecha, contenido);

//         assertNotNull(resultado);
//         assertEquals(nombre, resultado.getNombre());
//         assertEquals(contenido, resultado.getContenido());
//         verify(notasRepository, times(1)).save(any(Notas.class));
//     }

//     // nombre nulo
//     @Test
//     void crearNota_DeberiaLanzarExcepcionSiNombreEsNulo() {
//         String nombre = null;
//         String contenido = "Contenido válido";
//         LocalDateTime fecha = LocalDateTime.now();

//         IllegalArgumentException exception = assertThrows(
//                 IllegalArgumentException.class,
//                 () -> notasService.crearNota(nombre, fecha, contenido)
//         );

//         assertEquals("El nombre de la nota es obligatorio", exception.getMessage());
//         verify(notasRepository, never()).save(any(Notas.class));
//     }

//     // nombre vacío
//     @Test
//     void crearNota_DeberiaLanzarExcepcionSiNombreEsVacio() {

//         String nombre = "   ";
//         String contenido = "Contenido válido";
//         LocalDateTime fecha = LocalDateTime.now();

//         IllegalArgumentException exception = assertThrows(
//                 IllegalArgumentException.class,
//                 () -> notasService.crearNota(nombre, fecha, contenido)
//         );

//         assertEquals("El nombre de la nota es obligatorio", exception.getMessage());
//         verify(notasRepository, never()).save(any(Notas.class));
//     }

//     // contenido nulo
//     @Test
//     void crearNota_DeberiaLanzarExcepcionSiContenidoEsNulo() {
//         String nombre = "Nombre válido";
//         String contenido = null;
//         LocalDateTime fecha = LocalDateTime.now();

//         IllegalArgumentException exception = assertThrows(
//                 IllegalArgumentException.class,
//                 () -> notasService.crearNota(nombre, fecha, contenido)
//         );

//         assertEquals("La descripción (contenido) es obligatoria", exception.getMessage());
//         verify(notasRepository, never()).save(any(Notas.class));
//     }


//     // contenido vacío
//     @Test
//     void crearNota_DeberiaLanzarExcepcionSiContenidoEsVacio() {
//         String nombre = "Nombre válido";
//         String contenido = "   ";
//         LocalDateTime fecha = LocalDateTime.now();

//         IllegalArgumentException exception = assertThrows(
//                 IllegalArgumentException.class,
//                 () -> notasService.crearNota(nombre, fecha, contenido)
//         );

//         assertEquals("La descripción (contenido) es obligatoria", exception.getMessage());
//         verify(notasRepository, never()).save(any(Notas.class));
//     }

//     // buqueda de notas existente
//     @Test
//     void obtenerNotaPorId_DeberiaRetornarNotaSiExiste() {
//         Long id = 1L;
//         Notas notaMock = new Notas("Nota encontrada", LocalDateTime.now(), "Contenido");
//         notaMock.setId(id);

//         when(notasRepository.findById(id)).thenReturn(java.util.Optional.of(notaMock));

//         Notas resultado = notasService.obtenerNotaPorId(id);

//         assertNotNull(resultado);
//         assertEquals(id, resultado.getId());
//         assertEquals("Nota encontrada", resultado.getNombre());
//         verify(notasRepository, times(1)).findById(id);
//     }

//     //busqueda de notas no existentes
//     @Test
//     void obtenerNotaPorId_DeberiaLanzarExcepcionSiNoExiste() {
//         Long id = 99L;

//         when(notasRepository.findById(id)).thenReturn(java.util.Optional.empty());

//         RuntimeException exception = assertThrows(
//                 RuntimeException.class,
//                 () -> notasService.obtenerNotaPorId(id)
//         );

//         assertEquals("Nota no encontrada con ID: " + id, exception.getMessage());
//         verify(notasRepository, times(1)).findById(id);
//     }

//     //eliminacion de nota existente
//     @Test
//     void eliminarNota_DeberiaMarcarNotaComoInactiva() {
//         Long id = 1L;
//         Notas notaMock = new Notas("Nota a eliminar", LocalDateTime.now(), "Contenido");
//         notaMock.setId(id);
//         notaMock.setRecursoActivo(true);

//         when(notasRepository.findById(id)).thenReturn(java.util.Optional.of(notaMock));
//         when(notasRepository.save(any(Notas.class))).thenAnswer(invocation -> invocation.getArgument(0));

//         Notas resultado = notasService.eliminarNota(id);

//         assertNotNull(resultado);
//         assertFalse(resultado.getRecursoActivo(), "La nota debería estar inactiva después de eliminarla");
//         verify(notasRepository, times(1)).findById(id);
//         verify(notasRepository, times(1)).save(notaMock);
//     }

//     //eliminacion de nota no existente
//     @Test
//     void eliminarNota_DeberiaLanzarExcepcionSiNoExiste() {
//         Long id = 100L;

//         when(notasRepository.findById(id)).thenReturn(java.util.Optional.empty());

//         RuntimeException exception = assertThrows(
//                 RuntimeException.class,
//                 () -> notasService.eliminarNota(id)
//         );

//         assertEquals("Nota no encontrada con ID: " + id, exception.getMessage());
//         verify(notasRepository, times(1)).findById(id);
//         verify(notasRepository, never()).save(any(Notas.class));
//     }


// }
