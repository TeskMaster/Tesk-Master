package com.microservice_esofii.notification_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        // Arrange
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        // Act
        boolean resultado = emailService.sendSimpleEmail("teste@exemplo.com", "Assunto Teste", "Mensagem de teste");

        // Assert
        assertTrue(resultado);
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void deveFalharAoEnviarEmail() {
        // Arrange
        doThrow(new RuntimeException("Falha simulada")).when(mailSender).send(any(SimpleMailMessage.class));

        // Act
        boolean resultado = emailService.sendSimpleEmail("falha@exemplo.com", "Assunto", "Mensagem");

        // Assert
        assertFalse(resultado);
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
