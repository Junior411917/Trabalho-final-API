package org.serratec.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${mail_username}")
    private String mailUsername;

    public void save(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailUsername);
        message.setTo(para);
        message.setSubject(assunto);
        message.setText("Confirmação de Cadastro \n" + texto + "\n\n\n" + "Serratec - 2025");
        mailSender.send(message);
    }

    public void update(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailUsername);
        message.setTo(para);
        message.setSubject(assunto);
        message.setText("Atualização de cadastro \n" + texto + "\n\n\n" + "Serratec - 2025");
        mailSender.send(message);
    }
    
    public void delete(String para, String assunto, String texto) {
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setFrom(mailUsername);
    	message.setTo(para);
    	message.setSubject(assunto);
    	message.setText("Sua conta foi desativada \n"+ texto + "\n\n\n" + "Serratec - 2025");
    	mailSender.send(message);
    }
}
