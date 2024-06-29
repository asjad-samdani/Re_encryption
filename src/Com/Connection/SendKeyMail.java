package Com.Connection;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendKeyMail {
	
	//public static void SecretKeySend(String filename,String email,String attributes1,String attributes2,String attributes3,String attributes4,String attributes5,String ownername)
	public static void SecretKeySend(String filename,String email,String key,String ownername)
		
	{
		
		
		 	Properties props = System.getProperties();
	        String host = "smtp.gmail.com";
	        String from = "blockchainbaseaccesscontrolsys@gmail.com";
	        String pass = "@Blockchainacs";
	        String to = email;
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", from);
	        props.put("mail.smtp.password", pass);
	        props.put("mail.smtp.port", "587");     
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.timeout", "25000");
	        Session mailsession = Session.getDefaultInstance(props);
	   
	   try
	   {
	        MimeMessage message = new MimeMessage(mailsession);       
	        message.setFrom(new InternetAddress(from));   
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	      
	        	message.setSubject("Your Attributes List");
		        message.setText("Owner Name=>"+ownername+"\nFileName=>"+filename+"\nKey=>"+key);
		   
	        Transport transport = mailsession.getTransport("smtp");
	        transport.connect(host, from, pass);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        
	      
	       
	    }catch (MessagingException mex) {
	      mex.printStackTrace();
	      
	    }
		 } 
	public static void SecretattributeSend(String filename,String email,String key,String ownername,String attributes1,String attributes2,String attributes3,String attributes4,String attributes5,String attributes6)
	
	{
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("blockchainbaseaccesscontrolsys@gmail.com","@Blockchainacs");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Your Attributes List");
	        message.setText("Owner Name=>"+ownername+"\nFileName=>"+filename+"\n Attributes File Name=>"+attributes1+"\n Attributes Owner Name=>"+attributes2+"\n Attributes File Description=>"+attributes3+"\n Attributes Date=>"+attributes4+"\n Attributes Mobile=>"+attributes5+"\n Attributes Owner ID=>"+attributes6+"\nFile Key=>"+key);
	   
			   
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		
		
//		 	Properties props = System.getProperties();
//	        String host = "smtp.gmail.com";
//	        String from = "blockchainbaseaccesscontrolsys@gmail.com";
//	        String pass = "@Blockchainacs";
//	        String to = email;
//	        props.put("mail.smtp.starttls.enable", "true");
//	        props.put("mail.smtp.host", host);
//	        props.put("mail.smtp.user", from);
//	        props.put("mail.smtp.password", pass);
//	        props.put("mail.smtp.port", "587");     
//	        props.put("mail.smtp.auth", "true");
//	        props.put("mail.smtp.timeout", "25000");
//	        Session mailsession = Session.getDefaultInstance(props);
//	   
//	   try
//	   {
//	        MimeMessage message = new MimeMessage(mailsession);       
//	        message.setFrom(new InternetAddress(from));   
//	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//	      
//	        	message.setSubject("Your Attributes List");
//		        message.setText("Owner Name=>"+ownername+"\nFileName=>"+filename+"\n Attributes File Name=>"+attributes1+"\n Attributes Owner Name=>"+attributes2+"\n Attributes File Description=>"+attributes3+"\n Attributes Date=>"+attributes4+"\n Attributes Mobile=>"+attributes5+"\nFile Key"+key);
//		   
//	        Transport transport = mailsession.getTransport("smtp");
//	        transport.connect(host, from, pass);
//	        transport.sendMessage(message, message.getAllRecipients());
//	        transport.close();
//	        
//	      
//	       
//	    }catch (MessagingException mex) {
//	      mex.printStackTrace();
//	      
//	    }
		 } 

}
