package br.com.inspirar.ccm.service.email;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.inspirar.ccm.service.UsuarioService;


@Service
public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void sendEmailTeste(String nome, String email) throws MessagingException{

    	
    	String password = usuarioService.recuperarSenha(email);
    	
    	Map<String, Object> params = new HashMap<>();
    	params.put("nome", nome);
    	params.put("password", password);


        String[] targets = new String[] {email, "inspirarvic19@gmail.com"};
        
        String subject = "Titulo";
        
        String template = "template";
    	
        this.enviarEmail(params, subject, targets, template);
    	
    }
    


    
    
    private void enviarEmail(Map<String, Object> params, String subject, String[] targets, String template) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(targets);
        mmh.setFrom(sender);
        mmh.setSubject(subject);
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        
        Context context = new Context();

        params.forEach((key, value) -> {
        	context.setVariable(key, value);
        });

          
        mmh.setText(templateEngine.process(template, context), true);
        
    	sendHtmlEmail(mimeMessage);
    }

}
