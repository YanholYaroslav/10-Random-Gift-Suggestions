package calendar;

import java.time.LocalDate;

/**
 * A class representing an event and containing basic information 
 * about it.
 */
public class Event {
    private String eventName;    // Name of the event
    private LocalDate date;      // Date of the event

    /**
     * Constructor for the Event class.
     *
     * @param eventName Name of the event.
     * @param date      Date of the event.
     */
    public Event(String eventName, LocalDate date) {
        this.eventName = eventName;
        this.date = date;
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
     * Overridden toString() method for easy printing of information 
     * about the object.
     *
     * @return String with information about the object.
     */
    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", date=" + date +
                '}';
    }
}
