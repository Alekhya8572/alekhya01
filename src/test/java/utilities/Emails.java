//package utilities;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//
//public class Emails {
//
//    private static final String HOST = "smtp.gmail.com";
//    private static final String PORT = "587";
//    private static final String USERNAME = "add763764@gmail.com";
//    private static final String PASSWORD = "hzps vtjv prdz fway";
//
//    public static void sendTestReport(String to, String subject, String body) {
//        // Configure mail properties
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", HOST);
//        properties.put("mail.smtp.port", PORT);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//
//        // Create session with authentication
//        Session session = Session.getInstance(properties, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(USERNAME, PASSWORD);
//            }
//        });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(USERNAME));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//            message.setSubject(subject);
//            message.setText(body);
//
//
//            Transport.send(message);
//            System.out.println("Email sent successfully to " + to);
//        } catch (MessagingException e) {
//            System.err.println("Failed to send email. Check your SMTP settings and credentials.");
//            e.printStackTrace();
//        }
//    }
//}


//package utilities;//package utils;
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import java.util.Properties;
//
//public class Emails {
//
//    public static void sendReportByEmail(String to, String subject, String body, String filePath) {
//        String from = "alekhya8572@gmail.com";
//        String password = "vwqa sadt khne tcru";
//
//        sendEmail(from, password, to, subject, body, filePath);
//    }
//
//    public static void sendEmail(String from, String password, String to, String subject, String body, String filePath) {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//        Session session = Session.getInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(from, password);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//            message.setSubject(subject);
//
//            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText(body);
//
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart);
//
//            messageBodyPart = new MimeBodyPart();
//            DataSource source = new FileDataSource(filePath);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName("extent-report.html");
//            multipart.addBodyPart(messageBodyPart);
//
//            message.setContent(multipart);
//
//            Transport.send(message);
//
//            System.out.println("Email sent successfully with attachment");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}


package utilities;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

public class Emails {

    public static void sendTestReportByEmail(String to, String subject, String body, String filePath) {
        // Email credentials
        String from = "alekhya8572@gmail.com";
        String username = "add763764@gmail.com";
        String password = "vwqa sadt khne tcru";

        try {
            // Create an instance of HtmlEmail
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setSSLOnConnect(true);
            email.setFrom(from);
            email.setSubject(subject);
            email.setMsg(body);
            email.addTo(to);

            // Attach the report file
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(filePath);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Extent Report");
            attachment.setName("extent-report.html");
            email.attach(attachment);

            // Send the email
            email.send();
            System.out.println("Email sent successfully with attachment");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while sending email: " + e.getMessage());
        }
    }
}





