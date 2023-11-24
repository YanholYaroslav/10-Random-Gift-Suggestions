package consolescreens;

import java.util.Scanner;

import friendshipdb.FriendDatabase;
import friendshipdb.Friend;


public class Screens {

    public class ConsoleUtils {

        public static void clearConsole() {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println("Error clearing the console: " + e.getMessage());
            }
        }

    }

    public void displayMenu() {

        Scanner user_inp = new Scanner(System.in);
        
        DatabaseScreen dbScreen = new DatabaseScreen();
        FriendsScreen friendsScreen = new FriendsScreen();
        
        do {

            ConsoleUtils.clearConsole();
            System.out.println("\"Version info . . .\"");
            System.out.println("===========================================");
            System.out.println("1. Database");
            //System.out.println("2. Friends Database");
            System.out.println("3. Calendar     (no implement)");
            System.out.println("4. Gifts        (no implement)");
            System.out.println("5. Export       (no implement)");
            System.out.println("6. Log          (no implement)");
            System.out.println("7. Settings     (no implement)");
            System.out.println("");
            System.out.println("0. Terminate program");
            System.out.println("===========================================");

            int choice = user_inp.nextInt();
            switch (choice) {

                case 1:
                    dbScreen.displayMenu(user_inp, friendsScreen);

                case 2:

                case 3:

                case 4:

                case 5:
                    // Виклик методу виведення меню
                    // Тут ви можете вивести меню бази даних, налаштувань і т. д.
                    break;

                case 0:
                    System.out.println("Please, wait program to end");
                    user_inp.close();
                    // Adding a delay of 1.5 seconds (1500 milliseconds)
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  // Handle the exception if needed
                    }
                    // Можливо, ви хочете викликати метод для автоматичного відправлення писем тут
                    ConsoleUtils.clearConsole();
                    System.exit(0);
                    break;

                default:
                    System.out.println("No such option provided. Try again");
                    // Adding a delay of 1.5 seconds (1500 milliseconds)
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  // Handle the exception if needed
                    }

            }
            
        } while (true);        
        
    }


    public class DatabaseScreen {
    
        public void displayMenu(Scanner user_inp, FriendsScreen friendsScreen) {
    
             do {
    
                ConsoleUtils.clearConsole();
                System.out.println("Database options");
                System.out.println("===========================================");
                System.out.println("1. Friends");
                System.out.println("2. Calendar     (no implement yet)");
                System.out.println("3. Export       (no implement yet)");
                System.out.println("4. Log          (no implement yet)");
                System.out.println("5. Settings     (no implement yet)");
                System.out.println("");
                System.out.println("0. Main menu");
                System.out.println("===========================================");
    
                int choice = user_inp.nextInt();
                switch (choice) {
    
                    case 1:
                        // Виклик методу перевірки писем
                        // Можливо, вам потрібно створити окремий клас для цього
                        friendsScreen.displayMenu(user_inp);
    
                    case 2:
    
                    case 3:
    
                    case 4:
    
                    case 5:
                        // Виклик методу виведення меню
                        // Тут ви можете вивести меню бази даних, налаштувань і т. д.
                        break;
    
                    case 0:
                        return;
    
                    default:
                        System.out.println("No such option provided. Try again");
                        // Adding a delay of 1.5 seconds (1500 milliseconds)
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();  // Handle the exception if needed
                        }
    
                }
                
            } while (true);
    
        }
    
    }

    public class FriendsScreen {

        public void displayMenu(Scanner user_inp) {

            FriendDatabase friendDb = new FriendDatabase();
            String friendDbDataFileName = "friendDb.db";
            friendDb.readFriendsFromBinaryFile(friendDbDataFileName);

            boolean friendDbVisible = false;
            int friendDbPage = 1;
            int friendDbViewStartIndex = 0;
            int friendDbViewEndIndex = 9 < friendDb
                    .getFriendSize() ? 9 : friendDb.getFriendSize();
    
            do {
    
                ConsoleUtils.clearConsole();
                System.out.println("Friends options");
                System.out.println("===========================================");

                if (friendDbVisible){
                    System.out.println("Page " + friendDbPage);
                    System.out.println(friendDb.toString(friendDbViewStartIndex, friendDbViewEndIndex));
                    System.out.println("===========================================");
                    System.out.println("1. Hide friends");
                } else {
                    System.out.println("1. Show friends");
                }

                System.out.println("2. Add friend");
                System.out.println("3. Edit friend");
                System.out.println("4. Remove friend");
                System.out.println("5. Settings             (no implement yet)");
                System.out.println("");
                if (friendDbVisible && friendDbViewStartIndex != 0){
                    System.out.println("8. Prev. page");
                }
                if (friendDbVisible && friendDbViewEndIndex < friendDb.getFriendSize() - 1){
                    System.out.println("9. Next page");
                }
                
                System.out.println("");
                System.out.println("0. Return");
                System.out.println("===========================================");
    
                int choice = user_inp.nextInt();
                switch (choice) {
    
                    case 1:
                        if (friendDbVisible){
                            friendDbVisible = false;
                        } else {
                            friendDbVisible = true;
                        }
                        continue;
                        
    
                    case 2:
                        ConsoleUtils.clearConsole();
                        System.out.println("Add a new friend");
                        System.out.println("===========================================");

                        System.out.print("Enter first name: ");
                        String firstName2 = user_inp.next();
                        System.out.print("Enter last name: ");
                        String lastName2 = user_inp.next();
                        System.out.print("Enter contact number: ");
                        String contactNumber2 = user_inp.next();
                        System.out.print("Enter email: ");
                        String email2 = user_inp.next();
                        System.out.print("Enter website: ");
                        String website2 = user_inp.next();

                        friendshipdb.Friend newFriend = new Friend(friendDb.getFreeId(), firstName2, lastName2, contactNumber2, email2, website2);
                        friendDb.addFriend(newFriend);
                        friendDb.writeFriendsToBinaryFile(friendDbDataFileName);
                        System.out.println("Friend added successfully! Friend ID: " + newFriend.getId());
                        // Adding a delay of 1.5 seconds (1500 milliseconds)
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();  // Handle the exception if needed
                        }
                        continue;
    
                    case 3:
                        ConsoleUtils.clearConsole();
                        if (friendDbVisible){
                            System.out.println("===========================================");
                            System.out.println("Page " + friendDbPage);
                            System.out.println(friendDb.toString(friendDbViewStartIndex, friendDbViewEndIndex));
                            System.out.println("===========================================");
                            System.out.println("");
                        }
                        
                        System.out.println("Edit friend");
                        System.out.println("===========================================");
                    
                        System.out.print("Enter ID: ");
                        int id3 = user_inp.nextInt();

                        Friend friendToEdit = friendDb.findFriendById(id3);
                        if (friendToEdit != null) {
                            System.out.print("Enter first name: ");
                            friendToEdit.setFirstName(user_inp.next());
                            System.out.print("Enter last name: ");
                            friendToEdit.setLastName(user_inp.next());
                            System.out.print("Enter contact number: ");
                            friendToEdit.setContactNumber(user_inp.next());
                            System.out.print("Enter email: ");
                            friendToEdit.setEmail(user_inp.next());
                            System.out.print("Enter website: ");
                            friendToEdit.setWebsite(user_inp.next());

                            friendDb.updateFriend(friendDb.findFriendById(id3), friendToEdit);
                            friendDb.writeFriendsToBinaryFile(friendDbDataFileName);
                    
                            System.out.println("Friend updated successfully!");

                        } else {
                            System.out.println("Friend not found with ID: " + id3);
                        }
                        // Adding a delay of 1.5 seconds (1500 milliseconds)
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();  // Handle the exception if needed
                        }
                        continue;
    
                    case 4:
                        ConsoleUtils.clearConsole();
                        if (friendDbVisible){
                            System.out.println("===========================================");
                            System.out.println("Page " + friendDbPage);
                            System.out.println(friendDb.toString(friendDbViewStartIndex, friendDbViewEndIndex));
                            System.out.println("===========================================");
                            System.out.println("");
                        }

                        System.out.println("Remove friend");
                        System.out.println("===========================================");
                    
                        System.out.print("Enter ID: ");
                        int id4 = user_inp.nextInt();
                    
                        Friend friendToRemove = friendDb.findFriendById(id4);
                        if (friendToRemove != null) {
                            friendDb.removeFriend(friendToRemove);
                            friendDb.writeFriendsToBinaryFile(friendDbDataFileName);
                    
                            System.out.println("Friend removed successfully!");
                        } else {
                            System.out.println("Friend not found with ID: " + id4);
                        }
                        // Adding a delay of 1.5 seconds (1500 milliseconds)
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();  // Handle the exception if needed
                        }
                        continue;
                    
    
                    case 5:
    
                    case 8:
                        if (friendDbViewStartIndex - 10 >= 0) {
                            friendDbViewStartIndex -= 10;
                            if (friendDbViewEndIndex == friendDb.getFriendSize() - 1){
                                friendDbViewEndIndex = ((friendDbPage - 1)* 10) - 1;
                            } else {
                                friendDbViewEndIndex -= 10;
                            }
                            friendDbPage--;
                        }
                        continue;
                        
                    case 9:
                        if (friendDbViewEndIndex + 10 < friendDb.getFriendSize() - 1) {
                            friendDbViewEndIndex += 10;
                            friendDbViewStartIndex += 10;
                            friendDbPage++;
                        } else if (friendDbViewEndIndex == friendDb.getFriendSize() - 1){
                            continue;
                        } else {
                            friendDbViewEndIndex = friendDb.getFriendSize() - 1;
                            friendDbViewStartIndex += 10;
                            friendDbPage++;
                        }
                        continue;
                    
                    case 0:
                        return;
    
                    default:
                        System.out.println("No such option provided. Try again");
                        // Adding a delay of 1.5 seconds (1500 milliseconds)
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();  // Handle the exception if needed
                        }
                        
                }
                
            } while (true);
    
        }
    
    }
}
