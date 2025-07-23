package com.microsservices_esofii.crud_de_eventos.controller;
import com.microsservices_esofii.crud_de_eventos.model.Evento;
import com.microsservices_esofii.crud_de_eventos.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    /**
     * API para criar um novo evento no sistema.
     * Recebe os dados do evento (título, descrição, data, horário, local) e salva no banco de dados.
     * 
     * @param evento Dados completos do evento a ser criado
     * @return Evento criado com ID gerado automaticamente
     */
    @PostMapping("/salvar")
    public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento) {
        Evento salvo = eventoService.criarEvento(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    /**
     * API para listar todos os eventos cadastrados no sistema.
     * Retorna uma lista com todos os eventos com suas informações completas.
     * 
     * @return Lista de todos os eventos cadastrados
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Evento>> listarTodos() {
        List<Evento> eventos = eventoService.listarTodos();
        return ResponseEntity.ok(eventos);
    }

    /**
     * API para atualizar um evento existente no sistema.
     * Recebe os dados atualizados do evento e modifica apenas os campos que foram alterados.
     * 
     * @param eventoAtualizado Dados do evento com as alterações (deve incluir o ID)
     * @return Evento atualizado com as modificações aplicadas
     */
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarEvento(@RequestBody Evento eventoAtualizado) {
        try {
            Evento salvo = eventoService.atualizarEvento(eventoAtualizado.getId(), eventoAtualizado);
            return ResponseEntity.ok(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
