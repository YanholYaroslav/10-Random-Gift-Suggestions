package consolescreens;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import calendar.CalendarManager;
import calendar.Event;
import friendshipdb.FriendDatabase;
import friendshipdb.Friend;

import greetings.GreetingsGenerator;
import mailer.EmailSender;


public class Screens {

    protected Scanner userInput;

    protected DatabaseScreen dbScreen;
    protected FriendsScreen friendsScreen;
    protected CalendarScreen calendarScreen;
    
    //greetings screen

    protected FriendDatabase friendDb;
    protected String friendDbDataFileName;

    protected CalendarManager calendarDb;
    protected String calendarDbDataFileName;

    protected GreetingsGenerator greetingsGenerator;

    protected EmailSender emailSender;

    // Their data doesn't save
    private LocalDate today;
    private List<Event> eventsToday;
    private List<Friend> friendsToCongratulate;
    private HashMap <String, String> greetings;
    private String greetingsStatus;        // "null", "unchecked", "ready", "sent"


    public Screens(Scanner userInput, String friendDbDataFileName, String calendarDbDataFileName) {

        this.userInput = userInput;

        this.friendDb = new FriendDatabase();
        this.friendDbDataFileName = friendDbDataFileName;
        friendDb.readFromBinaryFile(friendDbDataFileName);

        this.calendarDb = new CalendarManager(friendDb);
        this.calendarDbDataFileName = calendarDbDataFileName;
        calendarDb.readFromBinaryFile(calendarDbDataFileName);

        this.greetingsGenerator = new GreetingsGenerator();

        this.emailSender = new EmailSender();

        this.dbScreen = new DatabaseScreen();
        this.friendsScreen = new FriendsScreen();
        this.calendarScreen = new CalendarScreen();

        this.today = LocalDate.now();
        this.eventsToday = calendarDb.findEventsByDate(today);
        this.friendsToCongratulate = new ArrayList<>();
        this.greetings = new HashMap<>();
        this.greetingsStatus = "null";        // "null", "unchecked", "ready", "sent"

    }


    private class DatabaseScreen {

        private DatabaseScreen() {}
    
        public void display() {
    
             do {
    
                ConsoleUtils.clearConsole();
                System.out.println("Database options");
                System.out.println("===========================================");
                System.out.println("1. Friends");
                System.out.println("2. Calendar");
                System.out.println("3. Export       (no implement yet)");
                System.out.println("4. Log          (no implement yet)");
                System.out.println("5. Settings     (no implement yet)");
                System.out.println("");
                System.out.println("0. Main menu");
                System.out.println("===========================================");
    
                int choice = userInput.nextInt();
                switch (choice) {
    
                    case 1:
                        friendsScreen.display();
                        continue;
    
                    case 2:
                        calendarScreen.display();
                        continue;
    
                    case 0:
                        return;
    
                    default:
                        System.out.println("No such option provided. Try again");
                        ConsoleUtils.delayConsole(1500);
    
                }
                
            } while (true);
    
        }
    
    }


    private class FriendsScreen {

        private boolean friendDbVisible = false;
        private int friendDbPage = 1;
        private int friendDbViewStartIndex = 0;
        private int friendDbViewEndIndex = 9 < friendDb
                .getFriendSize() ? 9 : friendDb.getFriendSize();

        public FriendsScreen() {}

        public void display() {
    
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
    
                int choice = userInput.nextInt();
                switch (choice) {
    
                    case 1:
                        option1();
                        continue;                        
    
                    case 2:
                        option2();
                        continue;
    
                    case 3:
                        option3();
                        continue;
    
                    case 4:
                        option4();
                        continue;
                    
    
                    case 5:
    
                    case 8:
                        option8();
                        continue;
                        
                    case 9:
                        option9();
                        continue;
                    
                    case 0:
                        return;
    
                    default:
                        System.out.println("No such option provided. Try again");
                        // Adding a delay of 1.5 seconds (1500 milliseconds)
                        ConsoleUtils.delayConsole(1500);
                        
                }
                
            } while (true);
    
        }
    
        private void option1(){
            if (friendDbVisible){
                friendDbVisible = false;
            } else {
                friendDbVisible = true;
            }
        }

        private void option2() {
            ConsoleUtils.clearConsole();
            System.out.println("Add a new friend");
            System.out.println("===========================================");
        
            System.out.print("Enter first name: ");
            String firstName2 = userInput.next();
            System.out.print("Enter last name: ");
            String lastName2 = userInput.next();
            System.out.print("Enter contact number: ");
            String contactNumber2 = userInput.next();
            System.out.print("Enter email: ");
            String email2 = userInput.next();
            System.out.print("Enter website: ");
            String website2 = userInput.next();
            System.out.print("Enter middle name: ");
            String middleName2 = userInput.next();
            System.out.print("Enter gender (Male/Female): ");
            String gender2 = userInput.next();
            System.out.print("Enter age: ");
            int age2 = userInput.nextInt();
            System.out.print("Enter zodiac sign: ");
            String zodiacSign2 = userInput.next();

            friendshipdb.Friend newFriend = new Friend(0, firstName2, lastName2, middleName2,
            contactNumber2, email2, website2, gender2, age2, zodiacSign2);

            friendDb.addFriend(newFriend);
            friendDb.writeToBinaryFile(friendDbDataFileName);
            System.out.println("Friend added successfully! Friend ID: " + newFriend.getId());
            ConsoleUtils.delayConsole(1500);
        }

        private void option3(){
            ConsoleUtils.clearConsole();
            if (friendDbVisible) {
                System.out.println("===========================================");
                System.out.println("Page " + friendDbPage);
                System.out.println(friendDb.toString(friendDbViewStartIndex, friendDbViewEndIndex));
                System.out.println("===========================================");
                System.out.println("");
            }
        
            System.out.println("Edit friend");
            System.out.println("===========================================");
        
            System.out.print("Enter ID: ");
            int id3 = userInput.nextInt();
        
            Friend friendToEdit = friendDb.findFriend(id3);
            if (friendToEdit != null) {
                System.out.print("Enter first name: ");
                friendToEdit.setFirstName(userInput.next());
                System.out.print("Enter last name: ");
                friendToEdit.setLastName(userInput.next());
                System.out.print("Enter contact number: ");
                friendToEdit.setContactNumber(userInput.next());
                System.out.print("Enter email: ");
                friendToEdit.setEmail(userInput.next());
                System.out.print("Enter website: ");
                friendToEdit.setWebsite(userInput.next());
                System.out.print("Enter middle name: ");
                friendToEdit.setMiddleName(userInput.next());
                System.out.print("Enter gender (Male/Female): ");
                friendToEdit.setGender(userInput.next());
                System.out.print("Enter age: ");
                friendToEdit.setAge(userInput.nextInt());
                System.out.print("Enter zodiac sign: ");
                friendToEdit.setZodiacSign(userInput.next());
            
                friendDb.updateFriend(id3, friendToEdit);
                friendDb.writeToBinaryFile(friendDbDataFileName);
            
                System.out.println("Friend updated successfully!");
            } else {
                System.out.println("Friend not found with ID: " + id3);
            }
            ConsoleUtils.delayConsole(1500);
        }

        private void option4(){
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
            int id4 = userInput.nextInt();
            
            boolean removed = friendDb.removeFriend(id4);
            if (removed) {
                System.out.println("Friend removed successfully!");
            } else {
                System.out.println("Friend not found with ID: " + id4);
            }
            // Adding a delay of 1.5 seconds (1500 milliseconds)
            ConsoleUtils.delayConsole(1500);
        }

        // more options must be placed here

        private void option8(){
            if (friendDbViewStartIndex - 10 >= 0) {
                friendDbViewStartIndex -= 10;
                if (friendDbViewEndIndex == friendDb.getFriendSize() - 1){
                    friendDbViewEndIndex = ((friendDbPage - 1)* 10) - 1;
                } else {
                    friendDbViewEndIndex -= 10;
                }
                friendDbPage--;
            }
        }

        private void option9(){
            if (friendDbViewEndIndex + 10 < friendDb.getFriendSize() - 1) {
                friendDbViewEndIndex += 10;
                friendDbViewStartIndex += 10;
                friendDbPage++;
            } else if (friendDbViewEndIndex == friendDb.getFriendSize() - 1){
                // Do nothing
            } else {
                friendDbViewEndIndex = friendDb.getFriendSize() - 1;
                friendDbViewStartIndex += 10;
                friendDbPage++;
            }
        }

    }


    private class CalendarScreen {

        private boolean calendarDbVisible = false;
        private int calendarDbPage = 1;
        private int calendarDbViewStartIndex = 0;
        private int calendarDbViewEndIndex = 9 < calendarDb
                .getEventsSize() ? 9 : calendarDb.getEventsSize();

        public CalendarScreen() {}

        public void display() {
    
            do {
    
                ConsoleUtils.clearConsole();
                System.out.println("Events options");
                System.out.println("===========================================");

                if (calendarDbVisible){
                    System.out.println("Page " + calendarDbPage);
                    System.out.println(calendarDb.toString(calendarDbViewStartIndex, calendarDbViewEndIndex));
                    System.out.println("===========================================");
                    System.out.println("1. Hide events");
                } else {
                    System.out.println("1. Show events");
                }

                System.out.println("2. Add event");
                System.out.println("3. Edit event");
                System.out.println("4. Remove event");
                System.out.println("5. Settings             (no implement yet)");
                System.out.println("");
                if (calendarDbVisible && calendarDbViewStartIndex != 0){
                    System.out.println("8. Prev. page");
                }
                if (calendarDbVisible && calendarDbViewEndIndex < calendarDb.getEventsSize() - 1){
                    System.out.println("9. Next page");
                }
                
                System.out.println("");
                System.out.println("0. Return");
                System.out.println("===========================================");
    
                int choice = userInput.nextInt();
                switch (choice) {
    
                    case 1:
                        option1();
                        continue;                        
    
                    case 2:
                        option2();
                        continue;
    
                    case 3:
                        option3();
                        continue;
    
                    case 4:
                        option4();
                        continue;
                    
    
                    case 5:
    
                    case 8:
                        option8();
                        continue;
                        
                    case 9:
                        option9();
                        continue;
                    
                    case 0:
                        return;
    
                    default:
                        System.out.println("No such option provided. Try again");
                        ConsoleUtils.delayConsole(1500);
                        
                }
                
            } while (true);
    
        }

        private void option1(){
            if (calendarDbVisible){
                calendarDbVisible = false;
            } else {
                calendarDbVisible = true;
            }
        }

        private void option2() {
            ConsoleUtils.clearConsole();
            if (calendarDbVisible) {
                System.out.println("===========================================");
                System.out.println("Page " + calendarDbPage);
                System.out.println(calendarDb.toString(calendarDbViewStartIndex, calendarDbViewEndIndex));
                System.out.println("===========================================");
                System.out.println("");
            }
            System.out.println("Add a new event");
            System.out.println("===========================================");

            System.out.println("Enter friend ID: ");
            int friendId = userInput.nextInt();

            Friend friend = friendDb.findFriend(friendId);
            if (friend != null) {

                System.out.println("Friend found: " + friend);
                System.out.println("===========================================");
                System.out.println("1. Confirm");
                System.out.println("0. Cancel");
                int confirmation = userInput.nextInt();

                switch (confirmation) {

                    case 1:
                    System.out.println("===========================================");
                    System.out.print("Enter event name: ");
                    String eventName = userInput.next();
                    System.out.print("Enter event date (YYYY-MM-DD): ");
                    String dateString = userInput.next();
                    LocalDate eventDate = LocalDate.parse(dateString);
                    System.out.print("Enter event occasion (\"birthday\", \"anniversary\", \"general\"): ");
                    String eventOccasion = userInput.next();

                    Event newEvent = new Event(friendId, eventName, eventDate, eventOccasion);

                    calendarDb.addEvent(friendId, newEvent);
                    calendarDb.writeToBinaryFile(calendarDbDataFileName);
                    System.out.println("===========================================");
                    System.out.println("Event added successfully! Event ID: " + newEvent.getFriendId());

                    default:
                        System.out.println("Event addition canceled.");

                }

            } else {
                System.out.println("Friend not found with ID: " + friendId);
            }
            System.out.println("Press Enter to continue");
            userInput.nextLine();
        }

        private void option3(){
            ConsoleUtils.clearConsole();
            if (calendarDbVisible) {
                System.out.println("===========================================");
                System.out.println("Page " + calendarDbPage);
                System.out.println(calendarDb.toString(calendarDbViewStartIndex, calendarDbViewEndIndex));
                System.out.println("===========================================");
                System.out.println("");
            }
        
            System.out.println("Edit event");
            System.out.println("===========================================");
        
            System.out.print("Enter friend ID: ");
            int friendId3 = userInput.nextInt();
            System.out.print("Enter event ID: ");
            int eventIndex = userInput.nextInt();
            System.out.println("===========================================");
            Event existingEvent = calendarDb.getEvent(friendId3, eventIndex);
            if (existingEvent != null) {
                // Display existing event details
                System.out.println("Existing Event Details:");
                System.out.println(existingEvent);
                System.out.println("===========================================");
                System.out.print("Enter event name (Don't use whitespaces): ");
                String eventName = userInput.next();
        
                System.out.print("Enter event date (YYYY-MM-DD): ");
                String dateString = userInput.next();
                LocalDate eventDate = LocalDate.parse(dateString);
                System.out.print("Enter event occasion (\"birthday\", \"anniversary\", \"general\"): ");
                String eventOccasion = userInput.next();
        
                // Create updated event
                Event updatedEvent = new Event(friendId3, eventName, eventDate, eventOccasion);
                // Update the event in the calendar
                if (calendarDb.updateEvent(friendId3, eventIndex, updatedEvent)) {
                    calendarDb.writeToBinaryFile(calendarDbDataFileName);
                    System.out.println("===========================================");
                    System.out.println("Event updated successfully!");
                    ConsoleUtils.delayConsole(2000);
                } else {
                    System.out.println("Failed to update event. Friend ID or index might be invalid.");
                }
            } else {
                System.out.println("Event not found with Friend ID: " + friendId3 + " and Index: " + eventIndex);
            }

        }

        private void option4(){
            ConsoleUtils.clearConsole();
            if (calendarDbVisible){
                System.out.println("===========================================");
                System.out.println("Page " + calendarDbPage);
                System.out.println(calendarDb.toString(calendarDbViewStartIndex, calendarDbViewEndIndex));
                System.out.println("===========================================");
                System.out.println("");
            }

            System.out.println("Remove event");
            System.out.println("===========================================");
            
            System.out.print("Enter friend ID: ");
            int friendId4 = userInput.nextInt();
            System.out.print("Enter event ID: ");
            int eventIndex = userInput.nextInt();
            
            boolean removed = calendarDb.removeEvent(friendId4, eventIndex);
            if (removed) {
                System.out.println("===========================================");
                System.out.println("Event removed successfully!");
            } else {
                System.out.println("Event not found with ID: " + friendId4);
            }
            ConsoleUtils.delayConsole(1500);
        }

        // more options must be placed here

        private void option8(){
            if (calendarDbViewStartIndex - 10 >= 0) {
                calendarDbViewStartIndex -= 10;
                if (calendarDbViewEndIndex == calendarDb.getEventsSize() - 1){
                    calendarDbViewEndIndex = ((calendarDbPage - 1)* 10) - 1;
                } else {
                    calendarDbViewEndIndex -= 10;
                }
                calendarDbPage--;
            }
        }

        private void option9(){
            if (calendarDbViewEndIndex + 10 < calendarDb.getEventsSize() - 1) {
                calendarDbViewEndIndex += 10;
                calendarDbViewStartIndex += 10;
                calendarDbPage++;
            } else if (calendarDbViewEndIndex == calendarDb.getEventsSize() - 1){
                // Do nothing
            } else {
                calendarDbViewEndIndex = calendarDb.getEventsSize() - 1;
                calendarDbViewStartIndex += 10;
                calendarDbPage++;
            }
        }


    }


    public class ConsoleUtils {

        protected static void clearConsole() {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println("Error clearing the console: " + e.getMessage());
            }
        }

        protected static void delayConsole(int time){
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();  // Handle the exception if needed
            }
        }

    }


    public void display() {
        
        do {

            ConsoleUtils.clearConsole();
            System.out.println("\"Version info: 0.0.2\"");
            System.out.println("===========================================");
            System.out.println("1. Database");
            System.out.println("2. Export       (no implement)");
            System.out.println("3. Log          (no implement)");
            System.out.println("4. Settings     (no implement)");
            System.out.println("");
            System.out.println("0. Terminate program");
            System.out.println("===========================================");

            int choice = userInput.nextInt();
            switch (choice) {

                case 1:
                    dbScreen.display();
                    continue;

                case 0:
                    System.out.println("Please, wait program to end");
                    userInput.close();
                    ConsoleUtils.delayConsole(1500);
                    // Можливо викликати метод для автоматичного відправлення писем тут
                    ConsoleUtils.clearConsole();
                    System.exit(0);
                    break;

                default:
                    System.out.println("No such option provided. Try again");
                    ConsoleUtils.delayConsole(1500);

            }
            
        } while (true);        
        
    }

    /**
     * Method called at the beginning of the program execution.
     * Checks for events today and prompts for greetings generation.
     */
    public void pushUpSuggestion() {

        if (!eventsToday.isEmpty()) {

            ConsoleUtils.clearConsole();
            System.out.println("Today's Events:");
            System.out.println("===========================================");
            for (Event event : eventsToday) {
                Friend friend = friendDb.findFriend(event.getFriendId());
                friendsToCongratulate.add(friend);
                System.out.println(friend);
                System.out.println("    Event" + event);
            }

            System.out.println("===========================================");
            System.out.println("Do you want to generate greetings right now? (Y/N):");
            String response = userInput.next();
            if (response.equals("Y")) {

                System.out.println("===========================================");
                System.out.println("Proceeding...");
                greetings = greetingsGenerator.generateGreetings(eventsToday, friendsToCongratulate);
                if (greetings != null) {
                    System.out.println("Greetings successfully generated!");
                    greetingsStatus = "unchecked";
                    ConsoleUtils.delayConsole(3000);
                    suggestionMenu();
                } else {
                    System.out.println("Something gone wrong...");
                    greetingsStatus = "null";
                }
                userInput.next();
                return;
            } else {
                System.out.println("Greetings will be automatically generated and sent at the end of the program");
                greetingsStatus = "null";
                return;
            }

        }

    }

    private void suggestionMenu() {

        do {
            ConsoleUtils.clearConsole();
            System.out.println("");
            System.out.println("===========================================");
            System.out.println("1. See greetings");
            System.out.println("2. Send greetings");
            System.out.println("");
            System.out.println("0. Main menu");
            System.out.println("===========================================");

            int choice = userInput.nextInt();
            switch (choice) {

                case 1:
                    for (String str : greetings.keySet()) {
                        ConsoleUtils.clearConsole();
                        System.out.println("Greetings");
                        System.out.println("===========================================");
                        System.out.println("Reciever " + str + ", message: " + greetings.get(str));
                        System.out.println("===========================================");
                        System.out.println("1. Edit");
                        System.out.println("0. Next");
                        int choice2 = userInput.nextInt();
                        if (choice2 == 1) {
                            System.out.println("Enter new greeting text:");
                            System.out.println("===========================================");
                            String newGreetingText = userInput.nextLine();
                            greetings.put(str, newGreetingText);
                            System.out.println("Updated. Enter something to continue");
                            userInput.next();
                        }
                    }
                    greetingsStatus = "ready";
                    continue;

                case 2:
                    for (String str : greetings.keySet()) {
                        emailSender.sendEmail(str, "", greetings.get(str));
                    }
                    greetingsStatus = "sent";
                    System.out.println("Sent successfully!");
                    ConsoleUtils.delayConsole(3000);
                    continue;

                case 0:
                    System.out.println("Greetings will be automatically sent at the end of the program");
                    greetingsStatus = "ready";
                    ConsoleUtils.delayConsole(3000);
                    return;

                default:
                    System.out.println("No such option provided. Try again");
                    ConsoleUtils.delayConsole(1500);

            }
        } while (true);

    }

    public void exitHandle() {
        if (greetingsStatus == "ready") {
            for (String str : greetings.keySet()) {
                emailSender.sendEmail(str, "", greetings.get(str));
            }
        } else if (greetingsStatus == "null") {
            greetings = greetingsGenerator.generateGreetings(eventsToday, friendsToCongratulate);
            for (String str : greetings.keySet()) {
                emailSender.sendEmail(str, "", greetings.get(str));
            }
        }
    }

}
