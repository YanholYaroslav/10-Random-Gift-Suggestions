package mailer;

import javax.mail.*;
import javax.mail.internet.*;


/**
 * A class responsible for sending emails.
 */
public class EmailSender {
    /**
     * Sends an email with the specified subject and content to the given recipient.
     *
     * @param recipientEmail The email address of the recipient.
     * @param subject        The subject of the email.
     * @param content        The content of the email.
     * @return True if the email was sent successfully, false otherwise.
     */
    public boolean sendEmail(String recipientEmail, String subject, String content) {
        
        MailPropertiesManager propertiesManager = new MailPropertiesManager();

        Session mailSession = Session.getInstance(
            propertiesManager.getPropertiesObject(),
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(propertiesManager
                    .getProperty("mail.smtp.user"), propertiesManager
                    .getProperty("mail.smtp.password"));
                }
            }
        );
        
        try {
            
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(propertiesManager
                    .getProperty("mail.smtp.user"), true));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(content);

            javax.mail.Transport.send(message);

        } catch (NoSuchProviderException e) {
            System.err.println("Error while connecting to email provider: " 
                    + e.getMessage());
            return false;  // Return false in case of any exception
        } catch (MessagingException e) {
            System.err.println("Error while sending email: " + e.getMessage());
            return false;  // Return false in case of any exception
        }

        // For now, simulate the email sending process
        System.out.println("Email sent to: " + recipientEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);
        return true;  // Simulate a successful email sending
    }
}
