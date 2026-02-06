package org.example.security;

public class SessionManager {

    private static String token;
    private static String role;

    public static boolean hasRole(String r) {
        return role.equals(r);
    }
    public static void clearSession() {
        token = null;
        role = null;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SessionManager.token = token;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        SessionManager.role = role;
    }
}
