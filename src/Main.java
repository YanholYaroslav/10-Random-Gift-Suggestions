import java.util.Scanner;

import consolescreens.Screens;


public class Main {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        Screens menuScreen = new Screens(userInput);
        menuScreen.pushUpSuggestion();
        menuScreen.display();

        userInput.close();

    }
}
