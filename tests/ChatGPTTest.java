package tests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

public class ChatGPTTest {

    public static String chatGPT(String prompt) {
        String urlToOpenai = "https://api.openai.com/v1/chat/completions";
        // The clear account is used, no worries this time
        String apiKey = "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";
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

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }

    public static void main(String[] args) {
        String promtString = "";
        System.out.println(chatGPT(promtString));
    }
}
