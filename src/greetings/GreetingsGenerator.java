package greetings;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import calendar.Event;
import friendshipdb.Friend;

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
        // more templates for different occasions
    }


    private String ChatGPTAPI(String prompt) {
        String urlToOpenai = "https://api.openai.com/v1/chat/completions";
        String apiKey = "OPEN_AI_API_KEY";
        String model = "gpt-3.5-turbo";

        try {

            URI uri = new URI(urlToOpenai);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // URL encode the prompt
            String encodedPrompt = URLEncoder.encode(prompt, "UTF-8");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + encodedPrompt + "\"}]}";
            connection.setDoOutput(true);

            try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                writer.write(body);
            }

            // Response from ChatGPT
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();

                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }

                    // Calls the method to extract the message.
                    return extractMessageFromJSONResponse(response.toString());
                }
            } else {
                throw new RuntimeException("HTTP Request Failed with response code: " + responseCode);
            }

        } catch (Exception e) {
            throw new RuntimeException("An error occurred during the API request", e);
        }
    }

    private String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }

    /**
     * Generates a greeting for a specific event.
     *
     * @param friendInfo Information about the friend associated with the event.
     * @param eventInfo  Information about the event.
     * @return Generated greeting.
     */
    public String generateGreeting(String friendInfo, String eventInfo) {
        String promptString = "Don't use any formating symbols. Don't assign author name. Assume the occasion. Generate a greeting for the friend based on: Friend Info: " + friendInfo + "Event Info: " + eventInfo;
        
        return ChatGPTAPI(promptString);
    }

    /**
     * Generates greetings for a list of events.
     *
     * @param events List of events.
     * @return List of generated greetings.
     */
    public HashMap<String, String> generateGreetings(List<Event> events, List<Friend> friends) {

        HashMap<String, String> generatedGreetings = new HashMap<>();

        for (Event event : events) {

            int friendId = event.getFriendId();
            String friendInfo = friends.get(friendId).toString();
            String eventInfo = event.toString();

            String greeting = generateGreeting(friendInfo, eventInfo);

            generatedGreetings.put(friends.get(friendId).getEmail(), greeting);
        }

        return generatedGreetings;

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
