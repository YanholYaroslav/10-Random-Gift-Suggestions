package greetings;

import java.util.HashMap;
import java.util.Map;

/**
 * A class responsible for generating personalized greetings and 
 * selecting appropriate gifts.
 */
public class GreetingsGenerator {
    private Map<String, String> greetingsTemplates;

    /**
     * Constructor for the GreetingsGenerator class.
     */
    public GreetingsGenerator() {
        this.greetingsTemplates = new HashMap<>();
        initializeGreetingsTemplates();
    }

    /**
     * Initializes the greetings templates.
     */
    private void initializeGreetingsTemplates() {
        greetingsTemplates.put("birthday", "Happy Birthday, %s! Wishing you a fantastic day filled with joy and laughter.");
        greetingsTemplates.put("anniversary", "Congratulations on your %s anniversary! May your love continue to grow.");
        greetingsTemplates.put("general", "Hello, %s! Sending warm wishes your way.");
        // Add more templates for different occasions as needed
    }

    /**
     * Generates a personalized greeting based on the occasion and 
     * recipient's name.
     *
     * @param occasion The occasion for the greeting (e.g., 
     * "birthday", "anniversary", "general").
     * @param name     The recipient's name.
     * @return Personalized greeting.
     */
    public String generateGreeting(String occasion, String name) {
        String template = greetingsTemplates.getOrDefault(occasion.toLowerCase(), greetingsTemplates.get("general"));
        return String.format(template, name);
    }

    /**
     * Selects an appropriate gift based on the occasion.
     *
     * @param occasion The occasion for selecting a gift.
     * @return An appropriate gift suggestion.
     */
    public String selectGiftSuggestion(String occasion) {
        // Add logic to select a gift suggestion based on the occasion
        // For now, return a generic suggestion
        return "Consider giving a thoughtful gift that reflects the recipient's interests.";
    }
}
