package daten;



import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;


/**
 * In dieser Klasse wird das Schicken von Emails impelentiert
 *
 */
public class mail extends Thread {
	private Properties p;
	/**
	 * Konstruktor der klasse Email
	 * @param an ist der empfaenger
	 * @param betreff ist der betreff der mail
	 * @param text ist die nachricht der mail
	 * @param anhangPfad1 ist der anhangspfad 1
	 * @param anhangName1 ist der name der ersten anhangsdatei
	 * @param anhangPfad2 ist der anhangspfad 2
	 * @param anhangName2 ist der name der zweiten anhangsdatei
	 */
	public mail(String an,String betreff,String text,
				String anhangPfad1,String anhangName1,
				String anhangPfad2,String anhangName2){
		
		if((an==null)||(an.length()==0)){
			return;
		}
		p=new Properties();
		p.put("mail.smtp.host","smtp.gmail.com");
		p.put("mail.smtp.user","0711nat@gmail.com");
		p.put("mail.smtp.password","natting4life");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.port","587");
		p.put("von","0711nat@gmail.com");
		p.put("an",an);
		p.put("betreff",betreff);
		p.put("text",text);
		
		if(anhangPfad1==null){
			p.put("anhangPfad1","");
		}else{
			p.put("anhangPfad1",anhangPfad1);
		}
		
		if(anhangName1==null){
			p.put("anhangName1","");
		}else{
			p.put("anhangName1",anhangName1);
		}
		
		if(anhangPfad2==null){
			p.put("anhangPfad2","");
		}else{
			p.put("anhangPfad2",anhangPfad1);
		}
		
		if(anhangName2==null){
			p.put("anhangName2","");
		}else{
			p.put("anhangName2",anhangName2);
		}
		
		this.start();
		
	}
	
	/**
	 * Klasse zur Autorisierung des Benutzers mit seinem Kennwort
	 * @author A2
	 *
	 */
	private class MailAuthenticator extends Authenticator{
		private String user, password;
		
		public MailAuthenticator(){
			this.user=""+p.get("mail.smtp.user");
			this.password=""+p.get("mail.smtp.password");
		}
		
		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(user,password);
		}
	}
	
	/**
	 * implementierte methode des interface runable 
	 * zum versenden einer Email
	 */
	@Override
	public void run(){
		try{
			
			System.out.println("Start Mailing an "+p.getProperty("an"));
			MailAuthenticator auth= new MailAuthenticator();
			Session session= Session.getDefaultInstance(p,auth);
			Message msg= new MimeMessage(session);
			msg.setFrom(new InternetAddress(p.getProperty("von")));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(p.getProperty("an"), false));
			msg.setSubject(p.getProperty("betreff"));
			//1.Teil der Nachricht
			MimeBodyPart bodyNachricht= new MimeBodyPart();
			bodyNachricht.setText(p.getProperty("text"));
			Multipart body = new MimeMultipart();
			body.addBodyPart(bodyNachricht);
			//2.Teil der Nachricht
			for(int i=1;i<=2;i++){
				if((!p.getProperty("anhangPfad"+i).equals(""))&&
					(!p.getProperty("anhangName"+i).equals(""))){
					MimeBodyPart bodyAnhang= new MimeBodyPart();
					DataSource source=new FileDataSource(p.getProperty("anhangPfad"+i));
					bodyAnhang.setDataHandler(new DataHandler(source));
					bodyAnhang.setFileName(p.getProperty("anhangName"+i));
					body.addBodyPart(bodyAnhang);
					
				}
			}
			msg.setContent(body);
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Mailing an "+p.getProperty("an")+" erfolgreich beendet.");
		}
		catch(Exception e){
			System.out.println("Mailing an "+p.getProperty("an")+" Fehlgeschlagen:");
			e.printStackTrace();
		}
	}
}

