package com.microservices_esofii.criar_usuarios.service;

import com.microservices_esofii.criar_usuarios.dto.UsuarioResponseDTO;
import com.microservices_esofii.criar_usuarios.dto.UsuarioRequestDTO;
import com.microservices_esofii.criar_usuarios.model.Usuario;
import com.microservices_esofii.criar_usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarNovoUsuarioComSucesso() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNome("Fulano");
        dto.setEmail("fulano@email.com");

        when(repository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());

        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setNome(dto.getNome());
        usuarioSalvo.setEmail(dto.getEmail());
        usuarioSalvo.setDataCriacao(LocalDateTime.now());

        when(repository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        UsuarioResponseDTO response = service.cadastrarUsuario(dto);

        assertNotNull(response);
        assertEquals(dto.getNome(), response.getNome());
        assertEquals(dto.getEmail(), response.getEmail());
        assertNotNull(response.getCriadoEm());
    }

    @Test
    void deveLancarExcecaoParaEmailDuplicado() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNome("Fulano");
        dto.setEmail("email@duplicado.com");

        when(repository.findByEmail(dto.getEmail())).thenReturn(Optional.of(new Usuario()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.cadastrarUsuario(dto));

        assertEquals("Usuário já cadastrado com este email.", exception.getMessage());
    }

    @Test
    void deveListarUsuariosComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Nome Teste");
        usuario.setEmail("email@teste.com");
        usuario.setDataCriacao(LocalDateTime.now());

        when(repository.findAll()).thenReturn(Collections.singletonList(usuario));

        var resultado = service.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Nome Teste", resultado.get(0).getNome());
    }
}
