package tests;

import mailer.EmailSender;

public class TestEmailSender {

    public static void main(String[] args) {
        // Create an EmailSender instance
        EmailSender emailSender = new EmailSender();

        // Test sending an email
        String recipientEmail = "kirilosoprano@gmail.com";
        String subject = "Test Email";
        String content = "This is a test email content.";

        boolean sendSuccess = emailSender.sendEmail(recipientEmail, subject, content);

        if (sendSuccess) {
            System.out.println("Email sent successfully.");
        } else {
            System.out.println("Failed to send email.");
        }
    }
}

