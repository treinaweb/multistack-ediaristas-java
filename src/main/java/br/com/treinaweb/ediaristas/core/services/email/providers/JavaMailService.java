package br.com.treinaweb.ediaristas.core.services.email.providers;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.treinaweb.ediaristas.core.services.email.adapters.EmailService;
import br.com.treinaweb.ediaristas.core.services.email.dtos.EmailParams;
import br.com.treinaweb.ediaristas.core.services.email.exceptions.EmailServiceException;

@Service
public class JavaMailService implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void enviarEmailComTemplateHtml(EmailParams params) {
        var mimeMessage = mailSender.createMimeMessage();
        var mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        var context = new Context();
        context.setVariables(params.getProps());

        var html = templateEngine.process(params.getTemplate(), context);

        try {
            mimeMessageHelper.setFrom("nao-responda@ediaristas.com", "E-Diaristas");
            mimeMessageHelper.setTo(params.getDestinatario());
            mimeMessageHelper.setSubject(params.getAssunto());
            mimeMessageHelper.setText(html, true);
        } catch (UnsupportedEncodingException exception) {
            throw new EmailServiceException(exception.getLocalizedMessage());
        } catch (MessagingException exception) {
            throw new EmailServiceException(exception.getLocalizedMessage());
        }

        mailSender.send(mimeMessage);
    }

}
