package tests;

import friendshipdb.Friend;
import friendshipdb.FriendDatabase;

import java.util.List;

public class TestFriendDatabase {

    public static void main(String[] args) {
        // Create some sample friends
        Friend friend1 = new Friend(1, "John", "Doe", "123456789", "john@example.com", "www.johndoe.com");
        Friend friend2 = new Friend(2, "Jane", "Smith", "987654321", "jane@example.com", "www.janesmith.com");

        // Create a FriendDatabase instance
        FriendDatabase friendDatabase = new FriendDatabase();

        // Test adding friends
        friendDatabase.addFriend(friend1);
        friendDatabase.addFriend(friend2);

        // Test retrieving all friends
        List<Friend> allFriends = friendDatabase.getAllFriends();
        System.out.println("All Friends:");
        for (Friend friend : allFriends) {
            System.out.println(friend);
        }

        // Test finding a friend by name
        Friend foundFriend = friendDatabase.findFriendByName("John");
        if (foundFriend != null) {
            System.out.println("Found Friend: " + foundFriend);
        } else {
            System.out.println("Friend not found.");
        }

        // Test updating a friend's information
        Friend updatedFriend = new Friend(1, "John", "Doe", "987654321", "john.new@example.com", "www.johndoe-updated.com");
        boolean updateSuccess = friendDatabase.updateFriend(friend1, updatedFriend);
        if (updateSuccess) {
            System.out.println("Friend information updated successfully.");
        } else {
            System.out.println("Failed to update friend information.");
        }

        // Test removing a friend
        boolean removalSuccess = friendDatabase.removeFriend(friend2);
        if (removalSuccess) {
            System.out.println("Friend removed successfully.");
        } else {
            System.out.println("Failed to remove friend.");
        }

        // Display the final list of friends
        System.out.println("Final List of Friends:");
        allFriends = friendDatabase.getAllFriends();
        for (Friend friend : allFriends) {
            System.out.println(friend);
        }
    }
}
