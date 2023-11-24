package tests;

import greetings.GreetingsGenerator;

public class TestGreetingsGenerator {

    public static void main(String[] args) {
        // Create a GreetingsGenerator instance
        GreetingsGenerator greetingsGenerator = new GreetingsGenerator();

        // Test generating a personalized greeting for a birthday
        String birthdayGreeting = greetingsGenerator.generateGreeting("birthday", "John");
        System.out.println("Birthday Greeting: " + birthdayGreeting);

        // Test generating a personalized greeting for an anniversary
        String anniversaryGreeting = greetingsGenerator.generateGreeting("anniversary", "Jane");
        System.out.println("Anniversary Greeting: " + anniversaryGreeting);

        // Test generating a generic greeting
        String generalGreeting = greetingsGenerator.generateGreeting("random", "Friend");
        System.out.println("General Greeting: " + generalGreeting);

        // Test selecting a gift suggestion for an occasion
        String giftSuggestion = greetingsGenerator.selectGiftSuggestion("birthday");
        System.out.println("Gift Suggestion: " + giftSuggestion);
    }
}

