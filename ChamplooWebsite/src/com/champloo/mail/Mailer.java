package com.champloo.mail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;    
import javax.mail.internet.*;
import javax.mail.MessagingException;

/**
 * 
 * Questa classe gestisce tutto l'invio di e-mail del nostro sistema
 * 
 * @author Antonio Mancuso
 *
 */

public class Mailer{  
	private static String from = "noreply.champloo@gmail.com";
	private static String password = "Yeezus98";

	/**
	 * Questa funzione gestisce l'invio di una mail e pu� essere usata con il
	 * nome di un file presente nel sistema oppure semplicemente inserendo al suo posto
	 * una stringa vuota.
	 * 
	 * @param to
	 * @param sub
	 * @param msg
	 * @param filename
	 */
	public static void send(String to,String sub,String msg, String filename){  
		//Get properties object    
		File attach = null;
		Multipart multipart = new MimeMultipart();
		MimeBodyPart messageBodyPart = new MimeBodyPart();


		Properties props = new Properties();    
		props.put("mail.smtp.host", "smtp.gmail.com");    
		props.put("mail.smtp.socketFactory.port", "465");    
		props.put("mail.smtp.socketFactory.class",    
				"javax.net.ssl.SSLSocketFactory");    
		props.put("mail.smtp.auth", "true");    
		props.put("mail.smtp.port", "25");    
		//get Session   
		Session session = Session.getDefaultInstance(props,    
				new javax.mail.Authenticator() {    
			protected PasswordAuthentication getPasswordAuthentication() {    
				return new PasswordAuthentication(from,password);  
			}    
		});    
		//compose message    
		try {    
			MimeMessage message = new MimeMessage(session);    
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
			message.setSubject(sub);    
			message.setText(msg); 
			if(filename.equals(""))
			{

			}
			else
			{
				// creates message part

				messageBodyPart.setContent(msg, "text/html");
				message.setContent(msg, "text/html");

				// creates multi-part

				multipart.addBodyPart(messageBodyPart);
				attach = new File(filename);

				MimeBodyPart attachPart = new MimeBodyPart();
				try
				{
					attachPart.attachFile(attach);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				multipart.addBodyPart(attachPart);
				message.setContent(multipart);
			}
			//send message  
			Transport.send(message);    
		} catch (MessagingException e) {throw new RuntimeException(e);}    
	}

	public static void send(String to,String sub,String msg) {
		Properties props = new Properties();    
		props.put("mail.smtp.host", "smtp.gmail.com");    
		props.put("mail.smtp.socketFactory.port", "465");    
		props.put("mail.smtp.socketFactory.class",    
				"javax.net.ssl.SSLSocketFactory");    
		props.put("mail.smtp.auth", "true");    
		props.put("mail.smtp.port", "25");    
		//get Session   
		Session session = Session.getDefaultInstance(props,    
				new javax.mail.Authenticator() {    
			protected PasswordAuthentication getPasswordAuthentication() {    
				return new PasswordAuthentication(from,password);  
			}    
		});    
		//compose message    
		try {    
			MimeMessage message = new MimeMessage(session);    
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
			message.setSubject(sub);    
			message.setText(msg); 
			message.setContent(msg, "text/html");

			//send message  
			Transport.send(message);    
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}    
	}  
}  


