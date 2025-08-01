package com.microservices_esofii.criar_usuarios.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices_esofii.criar_usuarios.dto.UsuarioRequestDTO;
import com.microservices_esofii.criar_usuarios.dto.UsuarioResponseDTO;
import com.microservices_esofii.criar_usuarios.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void deveCadastrarUsuarioComSucesso() throws Exception {
        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNome("Fulano");
        request.setEmail("fulano@email.com");

        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(1L);
        response.setNome(request.getNome());
        response.setEmail(request.getEmail());
        response.setCriadoEm(LocalDateTime.now());

        when(service.cadastrarUsuario(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Fulano"))
                .andExpect(jsonPath("$.email").value("fulano@email.com"));
    }

    @Test
    void deveListarUsuariosComSucesso() throws Exception {
        UsuarioResponseDTO usuario = new UsuarioResponseDTO();
        usuario.setId(1L);
        usuario.setNome("Fulano");
        usuario.setEmail("fulano@email.com");
        usuario.setCriadoEm(LocalDateTime.now());

        when(service.listarTodos()).thenReturn(Collections.singletonList(usuario));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Fulano"))
                .andExpect(jsonPath("$[0].email").value("fulano@email.com"));
    }
}
