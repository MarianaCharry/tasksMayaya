package com.example.tasksEvaluacion.service;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.tasksEvaluacion.models.status;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class emailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String enviarNotificacionRegistro(
		    String destinatario, 
		    String title,
		    Date due_date, 
		    String assigned_to, 
		    status status) {

		    try {
		        // Optional: Sleep for 5 minutes (consider removing this in production)
		        TimeUnit.MINUTES.sleep(5);

		        String asunto = "Hola!";
		        String cuerpo = "<body style='margin: 0; padding: 0; background-color: #CCCCCC;'>" +
		                "<div style='background-color: #CCCCCC;'>" +
		                "<div style='background-color: white; max-width: 600px; margin: auto; padding: 20px; box-sizing: border-box;'>" +
		                "<h1 style='color: #2B56C5; text-align: center; font-size: 24px; margin-top: 20px;'>¡Bienvenid@ a Scheuler!</h1>" +
		                "<p style='color: #000; font-size: 16px; line-height: 1.5; margin-top: 20px;'>" +
		                "Estamos emocionados de tenerte en nuestro sistema. Aquí tienes los detalles de tu cuenta:</p>" +
		                "<ul style='color: #000; font-size: 16px; line-height: 1.5; margin-top: 20px;'>" +
		                "<li><strong>Título:</strong> " + title + "</li>" +
		                "<li><strong>Estado:</strong> " + status + "</li>" +
		                "<li><strong>Fecha de vencimiento:</strong> " + due_date + "</li>" +
		                "<li><strong>Asignado a:</strong> " + assigned_to + "</li>" +
		                "</ul>" +
		                "<p style='color: #000; font-size: 16px; line-height: 1.5; margin-top: 20px;'>" +
		                "¡Gracias por unirte a nosotros!</p>" +
		                "</div>" +
		                "</div>" +
		                "</body>";

		        boolean retorno = enviarCorreo(destinatario, asunto, cuerpo);
		        return retorno ? "Se envió correctamente" : "No se pudo enviar";

		    } catch (Exception e) {
		        return "Error al enviar: " + e.getMessage();
		    }
		}


	
	private boolean enviarCorreo(String destinatario,String asunto,String cuerpo) throws MessagingException {
		try {
			MimeMessage message=javaMailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message,true);
			
			helper.setTo(destinatario);
			helper.setSubject(asunto);
			helper.setText(cuerpo,true);
			
			javaMailSender.send(message);
			return true;
		}catch (Exception e) {

			return false;
		}
		
	}
}
