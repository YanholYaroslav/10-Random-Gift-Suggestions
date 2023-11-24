package calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a calendar manager to handle events.
 */
public class CalendarManager {
    private List<Event> events;

    /**
     * Constructor for the CalendarManager class.
     */
    public CalendarManager() {
        this.events = new ArrayList<>();
    }

    /**
     * Adds an event to the calendar.
     *
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Removes an event from the calendar.
     *
     * @param event The event to be removed.
     * @return True if the event was successfully removed, false 
     * otherwise.
     */
    public boolean removeEvent(Event event) {
        return events.remove(event);
    }

    /**
     * Gets a list of all events in the calendar.
     *
     * @return List of events.
     */
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    /**
     * Finds and returns events on a specific date.
     *
     * @param date The date to find events for.
     * @return List of events on the specified date.
     */
    public List<Event> findEventsByDate(LocalDate date) {
        List<Event> eventsOnDate = new ArrayList<>();
        for (Event event : events) {
            if (event.getDate().equals(date)) {
                eventsOnDate.add(event);
            }
        }
        return eventsOnDate;
    }

    /**
     * Checks if there are any events on a specific date.
     *
     * @param date The date to check for events.
     * @return True if there are events on the specified date, false 
     * otherwise.
     */
    public boolean hasEventsOnDate(LocalDate date) {
        for (Event event : events) {
            if (event.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }
}
