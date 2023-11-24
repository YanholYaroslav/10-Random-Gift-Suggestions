package tests;

import java.io.*;
import java.util.Properties;

public class MailPropertiesFileWriter {

    public static void main(String args[]) throws IOException {

        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtps");
        properties.setProperty("mail.smtps.auth", "true");
        properties.setProperty("mail.smtps.host", "smtp.gmail.com");
        properties.setProperty("mail.smtps.user", "kirilosoprano@gmail.com");
        FileOutputStream out = new FileOutputStream("mail.properties");
        properties.store(out, null);
        out.close();
    }
    
}
