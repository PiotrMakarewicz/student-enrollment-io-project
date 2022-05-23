package pl.edu.agh.niebieskiekotki.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.edu.agh.niebieskiekotki.entitites.Student;

import java.util.Map;

@Component
@EnableAutoConfiguration
public class EmailService {

    @Autowired
    public JavaMailSender mailSender;

    public void send(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendToAll(Map<Student,String> studentLinkMap) {
        for(Map.Entry<Student,String> entry : studentLinkMap.entrySet()){
            send(entry.getKey().getEmailAddress(),"Link","localhost:3000/"+entry.getValue());
        }
    }
}