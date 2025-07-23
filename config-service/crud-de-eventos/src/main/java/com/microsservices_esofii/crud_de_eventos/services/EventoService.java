package com.microsservices_esofii.crud_de_eventos.services;

import com.microsservices_esofii.crud_de_eventos.model.Evento;
import com.microsservices_esofii.crud_de_eventos.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventoService {

private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento criarEvento(Evento evento){
        return eventoRepository.save(evento);
    }

    public List<Evento> listarTodos(){
        return eventoRepository.findAll();
    }

    public Evento atualizarEvento(Long id, Evento eventoAtualizado){

        Optional<Evento> optionalEvento = eventoRepository.findById(id);

        if (optionalEvento.isPresent()) {
            Evento evento = optionalEvento.get();

            // Verifica e atualiza apenas os campos que foram modificados
            // Usando Objects.equals para evitar NullPointerException
            if(eventoAtualizado.getTitulo() != null && !Objects.equals(evento.getTitulo(), eventoAtualizado.getTitulo())){
                evento.setTitulo(eventoAtualizado.getTitulo());
            }
            if(eventoAtualizado.getDescricao() != null && !Objects.equals(evento.getDescricao(), eventoAtualizado.getDescricao())){
                evento.setDescricao(eventoAtualizado.getDescricao());
            }
            if(eventoAtualizado.getDataInicio() != null && !Objects.equals(evento.getDataInicio(), eventoAtualizado.getDataInicio())){
                evento.setDataInicio(eventoAtualizado.getDataInicio());
            }
            if(eventoAtualizado.getHorarioInicio() != null && !Objects.equals(evento.getHorarioInicio(), eventoAtualizado.getHorarioInicio())){
                evento.setHorarioInicio(eventoAtualizado.getHorarioInicio());
            }
            if(eventoAtualizado.getLocalEvento() != null && !Objects.equals(evento.getLocalEvento(), eventoAtualizado.getLocalEvento())){
                evento.setLocalEvento(eventoAtualizado.getLocalEvento());
            }

            return eventoRepository.save(evento);
        } else {
            // Se o evento não for encontrado, lança uma exceção ao invés de retornar null
            throw new RuntimeException("Evento não encontrado com ID: " + id);
        }
    }

}
