package daten;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class mail{
	/**
	 * Methode zum versenden der mail
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 * @param toAddress
	 * @param subject
	 * @param message
	 * @param attachFiles
	 * @throws AddressException
	 * @throws MessagingException
	 */
	
	public static void sendEmailWithAttachments(String host, String port,
	        final String userName, final String password, String toAddress,
	        String subject, String message, String[] attachFiles)
	        throws AddressException, MessagingException {
	  
	    Properties properties = new Properties();
	    properties.put("mail.smtp.host", host);
	    properties.put("mail.smtp.port", port);
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.user", userName);
	    properties.put("mail.password", password);


	        // neue Session mit einem Athenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	        Session session = Session.getInstance(properties, auth);
	 
	        // Email-Nachricht
	        Message msg = new MimeMessage(session);
	 
	        msg.setFrom(new InternetAddress(userName));
	        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	 
	     
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(message, "text/html");
	 
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	 
	        // Anhang hinzufügen
	        if (attachFiles != null && attachFiles.length > 0) {
	            for (String filePath : attachFiles) {
	                MimeBodyPart attachPart = new MimeBodyPart();
	 
	                try {
	                    attachPart.attachFile(filePath);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	 
	                multipart.addBodyPart(attachPart);
	            }
	        }
	 
	        msg.setContent(multipart);
	 
	        // E-Mail senden
	        Transport.send(msg);
	 
	    }
	 
	    /**
	     * Test-Email senden
	     */
	    public static void main(String[] args) {
	        // SMTP info
	        String host = "smtp.gmail.com";
	        String port = "587";
	        String mailFrom = "srossa18@gmail.com";
	        String password = "28041992sr";
	 
	        // message info
	        String mailTo = "b.jakovic@hotmail.de";
	        String subject = "Neue Email";
	        String message = "Hallo User. Hier ist die geforderte Email mit dem gewünschten Dokument. Dein JavaDAME-Entwickler-Team";
	 
	        // Anhang
	        String[] attachFiles = new String[1];
	        attachFiles[0] = "C:/Users/Katherina/Desktop/Damekokokkok.pdf"; // PFAD verwenden wo die PDF gespeichert wurde
	    
	        //Fehlerbehandlung: Email gesendet oder Fehlgeschlagen 
	        
	        try {
	            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
	                subject, message, attachFiles);
	            System.out.println("Email wurde erfolgreich versendet.");
	        } catch (Exception ex) {
	            System.out.println("Die Email konnte leider nicht versendet werden.");
	            ex.printStackTrace();
	        }
	    }
	}



