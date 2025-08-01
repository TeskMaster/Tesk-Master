package com.microservice_esofii.notification_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice_esofii.notification_service.model.EmailRequest;
import com.microservice_esofii.notification_service.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void deveEnviarEmailComSucesso() throws Exception {
        EmailRequest request = new EmailRequest();
        request.setTo("teste@exemplo.com");
        request.setSubject("Assunto Teste");
        request.setBody("Mensagem de teste");

        when(emailService.sendSimpleEmail(anyString(), anyString(), anyString())).thenReturn(true);

        mockMvc.perform(post("/api/notificacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Email enviado com sucesso para: teste@exemplo.com"));
    }

    @Test
    public void deveRetornarErroAoFalharEnvio() throws Exception {
        EmailRequest request = new EmailRequest();
        request.setTo("teste@exemplo.com");
        request.setSubject("Assunto");
        request.setBody("Mensagem");

        when(emailService.sendSimpleEmail(anyString(), anyString(), anyString())).thenReturn(false);

        mockMvc.perform(post("/api/notificacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Falha ao enviar email para: teste@exemplo.com"));
    }
}
