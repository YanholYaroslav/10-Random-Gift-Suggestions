package friendshipdb;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class representing a simple database for managing friends.
 */
public class FriendDatabase {
    private List<Friend> friends;

    /**
     * Constructor for the FriendDatabase class.
     */
    public FriendDatabase() {
        this.friends = new ArrayList<>();
    }

    /**
     * Gets an available ID based on the current list of friends.
     *
     * @return An available ID.
     */
    private int getFreeId() {
        if (friends.isEmpty()) {
            return 0; // Start with ID 0 if the list is empty
        }
        int maxId = -1;
        for (Friend friend : friends) {
            int currentId = friend.getId();
            maxId = Math.max(maxId, currentId);
        }
        return maxId + 1;
    }

    /**
     * Adds a friend to the database with a new ID.
     *
     * @param friend The friend to be added.
     */
    public void addFriend(Friend friend) {
        int newId = getFreeId();
        friend.setId(newId);
        friends.add(friend);
    }

    /**
     * Updates the information of an existing friend in the database.
     *
     * @param id       The ID of the friend to be updated.
     * @param newFriend The friend with the updated information.
     * @return True if the update was successful, false otherwise.
     */
    public boolean updateFriend(int id, Friend newFriend) {
        for (int i = 0; i < friends.size(); i++) {
            Friend existingFriend = friends.get(i);
            if (existingFriend.getId() == id) {
                newFriend.setId(id);
                friends.set(i, newFriend);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a friend from the database by ID.
     *
     * @param id The ID of the friend to be removed.
     * @return True if the friend was successfully removed, false otherwise.
     */
    public boolean removeFriend(int id) {
        Iterator<Friend> iterator = friends.iterator();
        while (iterator.hasNext()) {
            Friend friend = iterator.next();
            if (friend.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Finds and returns a friend with the specified ID.
     *
     * @param id The ID of the friend to find.
     * @return The found friend or null if not found.
     */
    public Friend findFriend(int id) {
        for (Friend friend : friends) {
            if (friend.getId() == id) {
                return friend;
            }
        }
        return null;
    }

    /**
     * Gets a list of all friends in the database.
     *
     * @return List of friends.
     */
    public List<Friend> getAllFriends() {
        return new ArrayList<>(friends);
    }

    /**
     * Gets the size of the friends list in the database.
     *
     * @return The size of the friends list.
     */
    public int getFriendSize() {
        return friends.size();
    }


    // toString method for the list of friends within a specified range
    public String toString(int startIndex, int endIndex) {
        StringBuilder result = new StringBuilder();
        for (int i = startIndex; i <= endIndex && i < getFriendSize(); i++) {
            result.append(friends.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Reads the list of friends from a binary file in the resources/data directory.
     *
     * @param fileName Name of the file from which to read the data.
     * @return List of friends read from the file.
     */
    public List<Friend> readFromBinaryFile(String fileName) {
        friends.clear();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("resources/data/" + fileName);
             ObjectInputStream ois = new ObjectInputStream(is)) {
            if (is == null) {
                System.err.println("File not found: " + fileName);
                return null;
            }

            int friendCount = ois.readInt();  // Read the number of friends
            for (int i = 0; i < friendCount; i++) {
                Friend friend = Friend.readBinaryData(ois);
                friends.add(friend);
            }
            return friends;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Writes the list of friends to a binary file in the resources/data directory.
     *
     * @param fileName Name of the file to which to write the data.
     * @return True if the write was successful, false otherwise.
     */
    public boolean writeToBinaryFile(String fileName) {
        try (OutputStream os = new FileOutputStream("resources/data/" + fileName);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeInt(friends.size());  // Write the number of friends
            for (Friend friend : friends) {
                friend.writeBinaryData(oos);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

}
