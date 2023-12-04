package calendar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import friendshipdb.FriendDatabase;


/**
 * A class representing a calendar manager to handle events.
 */
public class CalendarManager {

    private Map<Integer, List<Event>> eventsMap;

    /**
     * Constructor for the CalendarManager class.
     */
    public CalendarManager(FriendDatabase friendDatabase) {
        this.eventsMap = new HashMap<>();
    }

    /**
     * Adds an event to the calendar.
     *
     * @param event The event to be added.
     */
    public void addEvent(int friendId, Event event) {
        if (!eventsMap.containsKey(friendId)) {
            eventsMap.put(friendId, new ArrayList<>());
        }
        eventsMap.get(friendId).add(event);
    }

    /**
     * Updates the details of an existing event.
     *
     * @param friendId The ID of the friend.
     * @param index    The index of the event to update (in the friend's list).
     * @param updatedEvent The updated event to set at the specified index.
     * @return True if the update is successful, false otherwise.
     */
    public boolean updateEvent(int friendId, int index, Event updatedEvent) {
        if (eventsMap.containsKey(friendId)) {
            List<Event> events = eventsMap.get(friendId);
            if (index >= 0 && index < events.size()) {
                events.set(index, updatedEvent);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes an event from the calendar.
     *
     * @param friendId The ID of the friend whose event is to be removed.
     * @param eventId  The ID of the event to be removed.
     * @return True if the event was successfully removed, false otherwise.
     */
    public boolean removeEvent(int friendId, int eventId) {
        if (eventsMap.containsKey(friendId)) {
            List<Event> events = eventsMap.get(friendId);
            Event eventToDel = events.remove(eventId);
            if (events.isEmpty()) {
                eventsMap.remove(friendId);
            }
            return eventToDel != null ? true : false;
        }
        return false;
    }

    /**
     * Returns the Event for a given friend ID and index.
     *
     * @param friendId The ID of the friend.
     * @param index    The index of the event to retrieve (in the friend's list).
     * @return The Event at the specified index for the given friend ID,
     *         or null if not found.
     */
    public Event getEvent(int friendId, int index) {
        if (eventsMap.containsKey(friendId)) {
            List<Event> events = eventsMap.get(friendId);
            if (index >= 0 && index < events.size()) {
                return events.get(index);
            }
        }
        return null;
    }

    /**
     * Gets a list of all events in the calendar.
     *
     * @return List of events.
     */
    public List<Event> getAllEvents(int friendId) {
        return eventsMap.getOrDefault(friendId, new ArrayList<>());
    }

    /**
     * Finds and returns events on a specific date.
     *
     * @param date The date to find events for.
     * @return List of events on the specified date.
     */
    public List<Event> findEventsByDate(LocalDate date) {
        List<Event> eventsOnDate = new ArrayList<>();

        for (List<Event> events : eventsMap.values()) {
            for (Event event : events) {
                if (event.getDate().equals(date)) {
                    eventsOnDate.add(event);
                }
            }
        }

        return eventsOnDate;
    }


    /**
     * Gets the size of the events map.
     *
     * @return The size of the events map.
     */
    public int getEventsSize() {
        int size = 0;
        for (List<Event> events : eventsMap.values()) {
            size += events.size();
        }
        return size;
    }

    /**
     * Customized toString() method for printing a range of events for each friend.
     *
     * @param startIndex The start index for the range.
     * @param endIndex   The end index for the range.
     * @return String with information about events within the specified range.
     */
    public String toString(int startIndex, int endIndex) {
        StringBuilder result = new StringBuilder();
        int friendIndex = 0;
        for (Map.Entry<Integer, List<Event>> entry : eventsMap.entrySet()) {
            if (friendIndex >= startIndex && friendIndex <= endIndex) {
                result.append("Friend ID: ").append(entry.getKey()).append("\n");
                for (Event event : entry.getValue()) {
                    result.append("    Event ID: ").append(entry.getKey()).append(" ").append(event).append("\n");
                }
            }
            friendIndex++;
        }
        return result.toString();
    }

    /**
     * Reads event data from a binary file and sets it in eventsMap.
     *
     * @param fileName The name of the file to read data from.
     * @return True if reading and setting data is successful, false otherwise.
     */
    public boolean readFromBinaryFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resources/data/" + fileName))) {
            int size = ois.readInt();
            eventsMap = new HashMap<>();

            for (int i = 0; i < size; i++) {
                int friendId = ois.readInt();
                Event event = (Event) ois.readObject();

                // Check if the friendId is already present in the map
                if (!eventsMap.containsKey(friendId)) {
                    eventsMap.put(friendId, new ArrayList<>());
                }

                eventsMap.get(friendId).add(event);
            }

            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading events map from file: " + e.getMessage());
            return false;
        }
    }


    /**
     * Writes event data to a binary file.
     *
     * @param fileName The name of the file to write data to.
     * @return True if writing is successful, false otherwise.
     */
    public boolean writeToBinaryFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resources/data/" + fileName))) {
            int size = 0;
            for (List<Event> events : eventsMap.values()) {
                size += events.size();
            }

            oos.writeInt(size);

            for (Map.Entry<Integer, List<Event>> entry : eventsMap.entrySet()) {
                for (Event event : entry.getValue()) {
                    oos.writeInt(entry.getKey());
                    event.writeBinaryData(oos);
                }
            }

            return true;
        } catch (IOException e) {
            System.err.println("Error writing events map to file: " + e.getMessage());
            return false;
        }
    }

}
