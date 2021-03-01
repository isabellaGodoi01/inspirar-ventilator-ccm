package br.com.inspirar.ccm.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
public interface EmailService {

	@Async
    void sendEmailTeste(String nome, String email) throws MessagingException;

    void sendHtmlEmail(MimeMessage msg);


}
