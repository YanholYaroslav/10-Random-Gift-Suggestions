package friendshipdb;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Class representing friends and containing basic contact information 
 * about them.
 */
public class Friend implements Serializable {

    // Basic attributes of a friend
    private int id;               // Unique ID
    private String firstName;      // First name
    private String lastName;       // Last name
    private String patronymic;     // Middle name
    private String email;          // Email
    private String gender;         // Gender (male or female)
    private int age;               // Age in years
    private String contactNumber;  // Phone number
    private String website;        // Website
    private String zodiacSign;     // Zodiac sign

    /**
     * Constructor for the Friend class.
     *
     * @param id             Unique ID of the friend.
     * @param firstName      First name of the friend.
     * @param lastName       Last name of the friend.
     * @param patronymic     Patronymic of the friend.
     * @param contactNumber  Phone number of the friend.
     * @param email          Email of the friend.
     * @param website        Website of the friend.
     * @param gender         Gender of the friend (male or female).
     * @param age            Age of the friend in years.
     * @param zodiacSign     Zodiac sign of the friend.
     */
    public Friend(int id, String firstName, String lastName, String patronymic,
                  String contactNumber, String email, String website,
                  String gender, int age, String zodiacSign) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.contactNumber = contactNumber;
        this.email = email;
        this.website = website;
        this.gender = gender;
        this.age = age;
        this.zodiacSign = zodiacSign;
    }

    // Getters and setters

    /**
     * Get the unique ID of the friend.
     *
     * @return Unique ID of the friend.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the unique ID of the friend.
     *
     * @param id Unique ID of the friend.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the first name of the friend.
     *
     * @return First name of the friend.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of the friend.
     *
     * @param firstName First name of the friend.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of the friend.
     *
     * @return Last name of the friend.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of the friend.
     *
     * @param lastName Last name of the friend.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the phone number of the friend.
     *
     * @return Phone number of the friend.
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Set the phone number of the friend.
     *
     * @param contactNumber Phone number of the friend.
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Get the email of the friend.
     *
     * @return Email of the friend.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the friend.
     *
     * @param email Email of the friend.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the website of the friend.
     *
     * @return Website of the friend.
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Set the website of the friend.
     *
     * @param website Website of the friend.
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Get the middle name of the friend.
     *
     * @return Middle name of the friend.
     */
    public String getMiddleName() {
        return patronymic;
    }

    /**
     * Set the middle name of the friend.
     *
     * @param middleName Middle name of the friend.
     */
    public void setMiddleName(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * Get the gender of the friend.
     *
     * @return Gender of the friend.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the gender of the friend.
     *
     * @param gender Gender of the friend.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the age of the friend.
     *
     * @return Age of the friend.
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the age of the friend.
     *
     * @param age Age of the friend.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the zodiac sign of the friend.
     *
     * @return Zodiac sign of the friend.
     */
    public String getZodiacSign() {
        return zodiacSign;
    }

    /**
     * Set the zodiac sign of the friend.
     *
     * @param zodiacSign Zodiac sign of the friend.
     */
    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    /**
     * Overridden toString() method for easy printing of information 
     * about the object.
     *
     * @return String with information about the object.
     */
    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", zodiac='" + zodiacSign + '\'' +
                '}';
    }

    /**
     * Reads friend data from an ObjectInputStream.
     *
     * @param ois ObjectInputStream to read from.
     * @return A Friend object read from the stream.
     * @throws IOException            If an I/O error occurs.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    public static Friend readBinaryData(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        return (Friend) ois.readObject();
    }

    /**
     * Writes friend data to an ObjectOutputStream.
     *
     * @param oos ObjectOutputStream to write to.
     * @throws IOException If an I/O error occurs.
     */
    public void writeBinaryData(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this);
    }

}
