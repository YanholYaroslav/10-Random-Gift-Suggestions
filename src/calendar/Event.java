package calendar;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A class representing an event and containing basic information 
 * about it.
 */
public class Event implements Serializable {
    private int friendId;       // ID of the friend associated with the event
    private String eventName;   // Name of the event
    private LocalDate date;     // Date of the event
    private String occasion;    // Occasion for the event (e.g., birthday, general celebration)

    /**
     * Constructor for the Event class.
     *
     * @param friendId  ID of the friend associated with the event.
     * @param eventName Name of the event.
     * @param date      Date of the event.
     * @param occasion  Occasion for the event.
     */
    public Event(int friendId, String eventName, LocalDate date, String occasion) {
        this.friendId = friendId;
        this.eventName = eventName;
        this.date = date;
        this.occasion = occasion;
    }

    /**
     * Get the ID of the friend associated with the event.
     *
     * @return ID of the friend associated with the event.
     */
    public int getFriendId() {
        return friendId;
    }

    /**
     * Set the ID of the friend associated with the event.
     *
     * @param friendId ID of the friend associated with the event.
     */
    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    /**
     * Get the name of the event.
     *
     * @return Name of the event.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Set the name of the event.
     *
     * @param eventName Name of the event.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Get the date of the event.
     *
     * @return Date of the event.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set the date of the event.
     *
     * @param date Date of the event.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Get the occasion for the event.
     *
     * @return Occasion for the event.
     */
    public String getOccasion() {
        return occasion;
    }

    /**
     * Set the occasion for the event.
     *
     * @param occasion Occasion for the event.
     */
    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    /**
     * Overridden toString() method for easy printing of information 
     * about the object.
     *
     * @return String with information about the object.
     */
    @Override
    public String toString() {
        return "{" +
                "Name='" + eventName + '\'' +
                ", date=" + date +
                ", occasion='" + occasion + '\'' +
                '}';
    }

    /**
     * Reads event data from an ObjectInputStream.
     *
     * @param ois ObjectInputStream to read from.
     * @return An Event object read from the stream.
     * @throws IOException            If an I/O error occurs.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
    public static Event readBinaryData(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        return (Event) ois.readObject();
    }

    /**
     * Writes event data to an ObjectOutputStream.
     *
     * @param oos ObjectOutputStream to write to.
     * @throws IOException If an I/O error occurs.
     */
    public void writeBinaryData(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this);
    }

}
