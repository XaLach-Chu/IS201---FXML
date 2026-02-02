package org.example.utils;

import com.google.gson.JsonObject;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.HttpURLConnection;

public class SqlUtil {
    // GET
    public static boolean getUserByEmail(String email) {
        HttpURLConnection conn = null;
        try {
            conn = ApiUtil.fetchApi(
                    "/api/v1/user?email=" + email,
                    ApiUtil.RequestMethod.GET,
                    null
            );

            System.out.println(conn.getResponseCode());

            if (conn.getResponseCode() != 200) {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return true;

    }

    // POST
    public static boolean postCreateUser(JsonObject userData) {
        HttpURLConnection conn = null;
        try {
            conn = ApiUtil.fetchApi(
                    "/api/v1/user/register",
                    ApiUtil.RequestMethod.POST,
                    userData
            );

            if (conn.getResponseCode() != 200) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return true;
    }
}
