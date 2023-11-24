package mailer;

import java.io.*;
import java.util.Properties;

public class MailPropertiesManager {
    private static final String PROPERTIES_FILE_NAME = "resources/data/mail.properties";
    private Properties properties;

    public MailPropertiesManager() {
        this.properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + PROPERTIES_FILE_NAME);
                return;
            }
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Properties getPropertiesObject() {
        return properties;
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }

    private void saveProperties() {
        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE_NAME)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage:
        MailPropertiesManager propertiesManager = new MailPropertiesManager();
        System.out.println("Current SMTP Host: " + propertiesManager.getProperty("mail.smtps.host"));

        // Modify a property (e.g., SMTP Host)
        //propertiesManager.setProperty("mail.smtps.host", "new.smtp.host.com");

        // Verify the change
        //System.out.println("Updated SMTP Host: " + propertiesManager.getProperty("mail.smtps.host"));
    }
}

