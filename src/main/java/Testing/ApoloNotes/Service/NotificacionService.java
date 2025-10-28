package Testing.ApoloNotes.Service;

import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    public void enviarNotificacion(String mensaje) {
        // En un futuro: enviar email, push o mensaje a Discord, etc.
        System.out.println(" NOTIFICACIÓN: " + mensaje);
    }
}
