package dev.gardeningtool.namemc.web;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class Request {

    /**
     * The response code will be -1 if it hasn't been set by the connection when Request#connect
     * is called. If the response code is -1 when Request#getResponseCode is called, it will throw
     * an IllegalStateException, signifiying that the connection has not yet been made.
     */
    private int responseCode = -1;

    @Getter
    private final URL url;
    @Getter
    private final HttpsURLConnection connection;

    @SneakyThrows
    public Request(String urlString) {
        url = new URL(urlString);
        connection = (HttpsURLConnection) url.openConnection();
    }

    /**
     * Sets the user agent and request method
     */
    @SneakyThrows
    public void prepare() {
        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
        connection.setRequestMethod("GET");
    }

    /**
     * Initializes the connection
     */
    @SneakyThrows
    public void connect() {
        connection.connect();
        responseCode = connection.getResponseCode();
    }

    /**
     * @return The response code of the connection
     * @throws IllegalStateException if the connection has not yet been established
     */
    public int getResponseCode() throws IllegalStateException {
        if (responseCode == -1) {
            throw new IllegalStateException("A connection has not yet been made yet!");
        }
        return responseCode;
    }

    /**
     * @return A JsonArray  object of the response
     * @throws IllegalStateException If the connection has not yet been made
     * @throws IllegalStateException If the connection response code is 400
     * @throws IllegalStateException If too many requests are being sent
     * @throws IOException If the BufferedReader fails to read the response
     */
    public JsonArray toJsonArray() throws IllegalStateException, IOException {
        String content = getRawContent();
        Gson gson = new Gson();
        return gson.fromJson(content, JsonArray.class);
    }

    /**
     * @return A Map object of the response
     * @throws IllegalStateException If the connection has not yet been made
     * @throws IllegalStateException If the connection response code is 400
     * @throws IllegalStateException If too many requests are being sent
     * @throws IOException If the BufferedReader fails to read the response
     */
    public Map<?, ?> map() throws IllegalStateException, IOException {
        String content = getRawContent();
        Gson gson = new Gson();
        return gson.fromJson(content, Map.class);
    }

    /**
     * @return The response from NameMC, as a String
     * @throws IllegalStateException If the connection has not yet been made
     * @throws IllegalStateException If the connection response code is 400
     * @throws IllegalStateException If too many requests are being sent
     * @throws IOException If the BufferedReader fails to read the response
     */
    private String getRawContent() throws IllegalStateException, IOException {
        if (responseCode == -1) {
            throw new IllegalStateException("A connection has not been made yet!");
        }
        if (responseCode == 400) {
            throw new IllegalStateException("Connection failed! Cannot read as JSON!");
        }
        if (responseCode == 429) {
            throw new IllegalStateException("Rate limited!");
        }
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

}
