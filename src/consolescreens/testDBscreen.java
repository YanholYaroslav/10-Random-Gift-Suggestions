package consolescreens;

import java.util.Scanner;

import consolescreens.Screens.ConsoleUtils;

public class testDBscreen {
    
    public void displayMenu(Scanner user_inp) {

         do {

            ConsoleUtils.clearConsole();
            System.out.println("Database options");
            System.out.println("===========================================");
            System.out.println("1. Friends");
            System.out.println("2. Calendar");
            System.out.println("3. Export");
            System.out.println("4. Log");
            System.out.println("5. Settings");
            System.out.println("");
            System.out.println("0. Main menu");
            System.out.println("===========================================");

            int choice = user_inp.nextInt();
            switch (choice) {

                case 1:
                    // Виклик методу перевірки писем
                    // Можливо, вам потрібно створити окремий клас для цього
                    

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

            }
            
        } while (true);

    }

}
