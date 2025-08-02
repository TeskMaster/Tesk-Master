package com.microservice_esofii.notification_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender javaMailSender;  // Corrigido para usar a variável javaMailSender

    // Construtor para injeção de dependência do JavaMailSender
    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // Método para enviar e-mail simples
    public boolean sendSimpleEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            javaMailSender.send(message);  // Alterado para usar javaMailSender
            logger.info("Email enviado com sucesso para: {}", to);
            return true;
        } catch (MailException e) {
            logger.error("Erro ao enviar email para {}: {}", to, e.getMessage());
            return false;
        }
    }
}
