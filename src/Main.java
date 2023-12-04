import java.util.Scanner;

import consolescreens.Screens;


public class Main {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        Screens menuScreen = new Screens(userInput, "friendDb.db", "calendarDb.db");
        menuScreen.pushUpSuggestion();
        menuScreen.display();
        menuScreen.exitHandle();

        userInput.close();

    }
}
