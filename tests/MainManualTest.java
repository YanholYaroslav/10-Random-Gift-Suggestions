package tests;

import java.util.Scanner;

import consolescreens.Screens;

public class MainManualTest {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        Screens menuScreen = new Screens(userInput, "testfriendDb.db", "testcalendar.db");
        menuScreen.pushUpSuggestion();
        menuScreen.display();
        menuScreen.exitHandle();

        userInput.close();

    }
}
