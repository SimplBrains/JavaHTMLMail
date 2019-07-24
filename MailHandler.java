package com.test;

import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailHandler {
	public static void main(String[] args) throws Exception {
		
		System.out.println("Preparing to send email.");
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		final String myAccount="simplbrains@gmail.com";
		final String password="password for myAccount";
		final String recipient="simplbrains@gmail.com";
		Session session = Session.getInstance(properties,new Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
				return new javax.mail.PasswordAuthentication(myAccount, password);
			}
		});
		
		Message message = prepareMessage(session, myAccount, recipient);
		
		Transport.send(message);
		System.out.println("Mail sent successfully.");
	}
	private static Message prepareMessage(Session session, String email, String recipient){
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("My First Email From Java Program");
			//message.setText("<html><head></head><bod><b>Hey there.</b>\n How are you today ?</b></body></html>");
			StringBuilder sb = new StringBuilder();
			try {
				FileInputStream fin = new FileInputStream("C:\\abhi\\software\\Jasper Report\\report1.html");
				int i=0;    
	            while((i=fin.read())!=-1){
	            	sb.append((char)i);
	            }    
				fin.close();
			} catch (Exception e) {
				System.out.println(e);
			}           
			System.out.println(sb.toString());
			message.setContent(sb.toString(),"text/html");
			return message;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
