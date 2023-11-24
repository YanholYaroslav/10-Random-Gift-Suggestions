package tests;

import calendar.CalendarManager;
import calendar.Event;

import java.time.LocalDate;
import java.util.List;

public class TestCalendarManager {

    public static void main(String[] args) {
        // Create some sample events
        Event event1 = new Event("Birthday Party", LocalDate.of(2023, 11, 15));
        Event event2 = new Event("Meeting", LocalDate.of(2023, 11, 20));

        // Create a CalendarManager instance
        CalendarManager calendarManager = new CalendarManager();

        // Test adding events
        calendarManager.addEvent(event1);
        calendarManager.addEvent(event2);

        // Test retrieving all events
        List<Event> allEvents = calendarManager.getAllEvents();
        System.out.println("All Events:");
        for (Event event : allEvents) {
            System.out.println(event);
        }

        // Test finding events by date
        LocalDate dateToFind = LocalDate.of(2023, 11, 15);
        List<Event> eventsOnDate = calendarManager.findEventsByDate(dateToFind);
        System.out.println("Events on " + dateToFind + ":");
        for (Event event : eventsOnDate) {
            System.out.println(event);
        }

        // Test checking for events on a specific date
        LocalDate dateToCheck = LocalDate.of(2023, 11, 20);
        boolean hasEventsOnDate = calendarManager.hasEventsOnDate(dateToCheck);
        if (hasEventsOnDate) {
            System.out.println("There are events on " + dateToCheck);
        } else {
            System.out.println("There are no events on " + dateToCheck);
        }

        // Test removing an event
        boolean removalSuccess = calendarManager.removeEvent(event2);
        if (removalSuccess) {
            System.out.println("Event removed successfully.");
        } else {
            System.out.println("Failed to remove event.");
        }

        // Display the final list of events
        System.out.println("Final List of Events:");
        allEvents = calendarManager.getAllEvents();
        for (Event event : allEvents) {
            System.out.println(event);
        }
    }
}

