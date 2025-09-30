package Testing.ApoloNotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Testing.ApoloNotes.Modelo.Notas;
import Testing.ApoloNotes.Service.NotasService;

@RestController
@RequestMapping("/notas")
public class NotasController {
    @Autowired
    private NotasService notasService;

    //crear nota
    @PostMapping
    public ResponseEntity <Notas> crearNota(@RequestBody Notas notas) {
        
        Notas nuevNotas= notasService.crearNota(
                notas.getNombre(),           // obtengo el nombre de la entidad
                notas.getFechaCreacion()     // obtengo la fecha de la entidad
        );
        return ResponseEntity.ok(nuevNotas);
        
    }
}

