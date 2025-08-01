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

//    @Test
//    void deveBuscarEventoPorIdExistente() {
//        Evento evento = new Evento(1L, "Workshop", "Evento técnico",
//                LocalDate.now(), LocalTime.of(14, 0), "Sala 101");
//
//        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
//
//        Optional<Evento> resultado = eventoService.buscarPorId(1L);
//
//        assertTrue(resultado.isPresent());
//        assertEquals("Workshop", resultado.get().getTitulo());
//    }

//    @Test
//    void deveRetornarVazioQuandoEventoNaoExistir() {
//        when(eventoRepository.findById(999L)).thenReturn(Optional.empty());
//
//        Optional<Evento> resultado = eventoService.buscarPorId(999L);
//
//        assertFalse(resultado.isPresent());
//    }
//
//    @Test
//    void deveExcluirEvento() {
//        Evento evento = new Evento(1L, "Conferência", "Evento acadêmico",
//                LocalDate.now(), LocalTime.of(8, 30), "Sala Magna");
//
//        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
//        doNothing().when(eventoRepository).delete(evento);
//
//        eventoService.deletar(1L);
//
//        verify(eventoRepository, times(1)).delete(evento);
//    }

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
        EventoRequestDTO dto = new EventoRequestDTO();
        dto.setTitulo("Novo título");
        dto.setDescricao("Nova descrição");
        dto.setDataInicio(LocalDate.now());
        dto.setHorarioInicio(LocalTime.NOON);
        dto.setLocalEvento("Novo local");

        when(repository.findById(idInexistente)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                service.atualizarEvento(idInexistente, dto)
        );

        assertEquals("Evento não encontrado com ID: 99", exception.getMessage());
    }

}
