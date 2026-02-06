package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.JsonObject;
import org.example.dto.requests.UserViewRequest;
import org.example.security.SessionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    public static List<UserViewRequest> fetchUsers(String apiPath, RequestMethod requestMethod, JsonObject jsonObject) {
        try {
            URL url = new URL(SPRINGBOOT_URL + apiPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(requestMethod.toString());

            conn.setRequestProperty("Authorization", "Bearer " + SessionManager.getToken());
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");

            System.out.println(conn.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            return mapper.readValue(
                    reader,
                    new TypeReference<List<UserViewRequest>>() {
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}