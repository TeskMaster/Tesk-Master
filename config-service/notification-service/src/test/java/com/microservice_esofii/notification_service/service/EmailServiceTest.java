package com.microservice_esofii.notification_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    private JavaMailSender mailSender;
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        mailSender = mock(JavaMailSender.class);
        emailService = new EmailService(mailSender);
    }

    @Test
    void deveEnviarEmailComSucesso() {
        // Arrange: simula o envio de e-mail com sucesso
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        // Act: tenta enviar o e-mail
        boolean resultado = emailService.sendSimpleEmail("teste@exemplo.com", "Assunto Teste", "Mensagem de teste");

        // Assert: o resultado deve ser true, indicando sucesso
        assertTrue(resultado);
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void deveFalharAoEnviarEmail() {
        // Arrange: simula uma falha ao tentar enviar o e-mail
        doThrow(new MailException("Falha simulada") {}).when(mailSender).send(any(SimpleMailMessage.class));

        // Act: tenta enviar o e-mail
        boolean resultado = emailService.sendSimpleEmail("falha@exemplo.com", "Assunto", "Mensagem");

        // Assert: o resultado deve ser false, indicando que houve falha
        assertFalse(resultado);
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
