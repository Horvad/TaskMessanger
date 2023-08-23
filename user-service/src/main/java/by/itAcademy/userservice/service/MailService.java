package by.itAcademy.userservice.service;

import by.itAcademy.userservice.core.exception.MailException;
import by.itAcademy.userservice.service.api.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void send(String mail, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(username);
            message.setTo(mail);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        } catch (Exception e){
            throw new MailException("Ошибка отправки сообщения" );
        }
    }

    @Override
    public void sendActivationCode(String mail, int codeVerification) {
        send(mail, "Activation code","Activation code "+String.valueOf(codeVerification));
    }


}
