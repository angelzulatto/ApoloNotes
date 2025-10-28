package Testing.ApoloNotes.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import Testing.ApoloNotes.Modelo.Eventos;
import Testing.ApoloNotes.Modelo.Tag;
import Testing.ApoloNotes.repsitory.EventosRepository;
import Testing.ApoloNotes.repsitory.TagRepository;

@Service
public class EventosService {

    private final EventosRepository eventosRepository;
    private final TagRepository tagRepository;
    private final NotificacionService notificacionService; // Servicio para enviar avisos

    public EventosService(EventosRepository eventosRepository, 
                          TagRepository tagRepository,
                          NotificacionService notificacionService) {
        this.eventosRepository = eventosRepository;
        this.tagRepository = tagRepository;
        this.notificacionService = notificacionService;
    }

    // Crear un nuevo evento
    public Eventos crearEvento(String nombre,String listag, LocalDateTime fechaCreacion, String contenido, LocalDateTime fechaEvento) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del evento es obligatorio");
        }
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido del evento es obligatorio");
        }
        if (fechaEvento == null || fechaEvento.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha del evento debe ser futura");
        }

        Eventos evento = new Eventos(nombre, listag , fechaCreacion, contenido, fechaEvento);
        return eventosRepository.save(evento);
    }

    // Eliminar (marcar como inactivo)
    public Eventos eliminarEvento(Long id) {
        Eventos evento = eventosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));
        evento.eliminar();
        return eventosRepository.save(evento);
    }

    // Obtener por ID
    public Eventos obtenerEventoPorId(Long id) {
        return eventosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));
    }

    // Listar todos
    public List<Eventos> listarTodos() {
        return eventosRepository.findAll();
    }

    // Listar solo activos
    public List<Eventos> listarActivos() {
        return eventosRepository.findByRecursoActivoTrue();
    }

    // Actualizar un evento
    public Eventos actualizarEvento(Long id, String nuevoNombre,String nuevoTaglist, String nuevoContenido, 
                                    LocalDateTime nuevaFecha, Boolean nuevoEstado) {
        Eventos evento = eventosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + id));

        if (nuevoNombre != null && !nuevoNombre.isEmpty()) evento.setNombre(nuevoNombre);
        if (nuevoTaglist != null && !nuevoTaglist.isEmpty()) evento.setTaglist(nuevoTaglist);
        if (nuevoContenido != null && !nuevoContenido.isEmpty()) evento.setContenido(nuevoContenido);
        if (nuevaFecha != null) evento.setFechaDeEvento(nuevaFecha);
        if (nuevoEstado != null) evento.setRecursoActivo(nuevoEstado);

        return eventosRepository.save(evento);
    }

    // Agregar etiquetas
    // gregarEtiquetas(Long eventoId, List<String> nombresEtiquetas) {
    //     Eventos evento = eventosRepository.findById(eventoId)
    //         .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

    //     for (String nombre : nombresEtiquetas) {
    //         Tag etiqueta = tagRepository.findByNombre(nombre);
    //         if (etiqueta == null) {
    //             etiqueta = new Tag();
    //             etiqueta.setNombre_tag(nombre);
    //             tagRepository.save(etiqueta);
    //         }
    //         evento.getTaglist(etiqueta);
    //     }

    //     return eventosRepository.save(evento);
    // }
  public Eventos agregarEtiquetas(Long eventoId, List<String> nombresEtiquetas) {
    Eventos evento = eventosRepository.findById(eventoId)
        .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

    for (String nombre : nombresEtiquetas) {
        Tag etiqueta = tagRepository.findByNombreTag(nombre); 
        if (etiqueta == null) {
            etiqueta = new Tag();
            etiqueta.setNombreTag(nombre); 
            tagRepository.save(etiqueta);
        }

        // Agregar nombre al string taglist
        String actual = evento.getTaglist();
        if (actual == null || actual.isEmpty()) {
            evento.setTaglist(nombre);
        } else if (!actual.contains(nombre)) {
            evento.setTaglist(actual + "," + nombre);
        }
    }

    return eventosRepository.save(evento);
}


    // 🔔 Verificar y enviar notificaciones automáticamente
    // se ejecuta todos los días a las 6 AM
    @Scheduled(cron = "0 0 6 * * *") 
    public void notificarEventosDelDia() {
    LocalDateTime inicioDia = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
    LocalDateTime finDia = inicioDia.plusDays(1).minusNanos(1);

    List<Eventos> eventosDelDia = eventosRepository.findByFechaDeEventoBetween(inicioDia, finDia);

    for (Eventos evento : eventosDelDia) {
        notificacionService.enviarNotificacion(
            "Hoy tenés el evento '" + evento.getNombre() + "' a las " + evento.getFechaDeEvento()
        );
    }
}

public List<Eventos> obtenerEventosDeHoy(LocalDateTime fecha) {
    LocalDateTime inicioDia = fecha.withHour(0).withMinute(0).withSecond(0).withNano(0);
    LocalDateTime finDia = inicioDia.plusDays(1).minusNanos(1);

    return eventosRepository.findByFechaDeEventoBetween(inicioDia, finDia);
}


}
