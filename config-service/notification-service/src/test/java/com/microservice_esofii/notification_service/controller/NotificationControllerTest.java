package com.microservice_esofii.notification_service.controller;

import com.microservice_esofii.notification_service.model.EmailRequest;
import com.microservice_esofii.notification_service.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(NotificationController.class)  // Usar WebMvcTest para testar apenas o controller
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

        // Simulando o comportamento do serviço de email
        when(emailService.sendSimpleEmail(anyString(), anyString(), anyString())).thenReturn(true);

        // Realizando o teste de envio de email
        mockMvc.perform(post("/notifications/send-email")  // Corrigido para /notifications/send-email
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())  // Espera que a resposta seja 200 OK
                .andExpect(content().string("Email sent successfully!"));  // Espera que o conteúdo seja a mensagem de sucesso
    }

    @Test
    public void deveRetornarErroAoFalharEnvio() throws Exception {
        EmailRequest request = new EmailRequest();
        request.setTo("teste@exemplo.com");
        request.setSubject("Assunto");
        request.setBody("Mensagem");

        // Simulando a falha no envio do email
        when(emailService.sendSimpleEmail(anyString(), anyString(), anyString())).thenReturn(false);

        // Realizando o teste de falha ao enviar email
        mockMvc.perform(post("/notifications/send-email")  // Corrigido para /notifications/send-email
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())  // Espera que a resposta seja 500 (erro do servidor)
                .andExpect(content().string("Failed to send email. Check logs for details."));  // Espera que o conteúdo seja a mensagem de erro
    }
}
