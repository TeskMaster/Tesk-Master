package com.microservice_esofii.notification_service.controller;

import com.microservice_esofii.notification_service.model.EmailRequest;
import com.microservice_esofii.notification_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    /**
     * API para enviar um email simples através do sistema de notificações.
     * Recebe destinatário, assunto e corpo do email e envia via SMTP configurado.
     * 
     * @param emailRequest Dados do email (destinatário, assunto e corpo da mensagem)
     * @return Confirmação de envio ou mensagem de erro
     */
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        boolean sent = emailService.sendSimpleEmail(
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getBody()
        );

        if (sent) {
            return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to send email. Check logs for details.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

