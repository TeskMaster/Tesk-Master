package com.microsservices_esofii.crud_de_eventos.service;

import com.microsservices_esofii.crud_de_eventos.model.Evento;
import com.microsservices_esofii.crud_de_eventos.repository.EventoRepository;
import com.microsservices_esofii.crud_de_eventos.services.EventoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoService eventoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarEventoComSucesso() {
        // Usando o construtor correto de Evento
        Evento evento = new Evento(null, "Hackathon", "Evento de tecnologia",
                LocalDate.now(), LocalTime.of(10, 0), "Auditório Central");
        Evento eventoSalvo = new Evento(1L, "Hackathon", "Evento de tecnologia",
                LocalDate.now(), LocalTime.of(10, 0), "Auditório Central");

        when(eventoRepository.save(evento)).thenReturn(eventoSalvo);

        Evento resultado = eventoService.criarEvento(evento);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(eventoRepository, times(1)).save(evento);
    }

    @Test
    void deveAtualizarEventoQuandoIdExistir() {
        Evento original = new Evento(1L, "Palestra", "Evento antigo",
                LocalDate.of(2025, 1, 1), LocalTime.of(9, 0), "Sala 100");

        Evento atualizado = new Evento(1L, "Palestra Atualizada", "Evento novo",
                LocalDate.of(2025, 1, 2), LocalTime.of(10, 0), "Sala 101");

        when(eventoRepository.findById(1L)).thenReturn(Optional.of(original));
        when(eventoRepository.save(any(Evento.class))).thenReturn(atualizado);

        Evento resultado = eventoService.atualizarEvento(1L, atualizado);

        assertNotNull(resultado);
        assertEquals("Palestra Atualizada", resultado.getTitulo());
        assertEquals("Evento novo", resultado.getDescricao());
        assertEquals(LocalDate.of(2025, 1, 2), resultado.getDataInicio());
        assertEquals(LocalTime.of(10, 0), resultado.getHorarioInicio());
        assertEquals("Sala 101", resultado.getLocalEvento());
        verify(eventoRepository).save(any(Evento.class));
    }

    @Test
    void deveLancarExcecaoQuandoAtualizarEventoComIdInexistente() {
        Long idInexistente = 99L;
        Evento eventoAtualizado = new Evento( // Use o construtor com o id!
                null, "Novo título", "Nova descrição", LocalDate.now(), LocalTime.NOON, "Novo local"
        );

        when(eventoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                eventoService.atualizarEvento(idInexistente, eventoAtualizado)
        );

        assertEquals("Evento não encontrado com ID: 99", exception.getMessage());
    }
}
