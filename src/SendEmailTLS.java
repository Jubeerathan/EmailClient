import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.*;

public class SendEmailTLS { // Class for sending Emails

    public static void sendEmail(Email email) {
        Host host=Host.getInstance();
        final String username = host.getUsername(); //Use your own password
        final String password = host.getPassword(); //Use your own username

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS


        Session session = Session.getInstance(prop,new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email.getEmail()));
            message.setSubject(email.getSubject());
            message.setText(email.getContent() + "\n" + "\nWith Love,\n" + host.getName());

            Transport.send(message); // sending message

        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}