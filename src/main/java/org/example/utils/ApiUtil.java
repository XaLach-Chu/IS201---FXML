package org.example.utils;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiUtil {
    private static final String SPRINGBOOT_URL = "http://localhost:8094";
    public enum RequestMethod{POST, GET, PUSH, DELETE};

    public static HttpURLConnection fetchApi(String apiPath, RequestMethod requestMethod, JsonObject jsonData) {
        try {
            URL url = new URL(SPRINGBOOT_URL + apiPath); // create url
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // open connection

            // set request type
            conn.setRequestMethod(requestMethod.toString()); // set method (POST, GET, PUSH, DELETE,...)

            // send data to the api
            if (jsonData != null && requestMethod != RequestMethod.GET) {
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                conn.setRequestProperty("Accept", "application/json");

                conn.setDoOutput(true);

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonData.toString().getBytes(StandardCharsets.UTF_8);

                    os.write(input, 0, input.length);
                }
            }
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}