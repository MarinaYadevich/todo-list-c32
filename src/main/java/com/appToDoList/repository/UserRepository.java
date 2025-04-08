package com.appToDoList.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for storing users like a database
 */
public class UserRepository {
    private static final Map<String, String> USERS = new HashMap<>();
    private static final String FILE_NAME = "users.ser";

    static { // executed once when the class is loaded
        USERS.put("admin", "admin");
        USERS.put("user", "user");
        loadUsers();
    }

    private static void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            // Load the user collection if the file exists
            Object obj = ois.readObject();
            if (obj instanceof Map) {
                Map<String, String> loadedUsers = (Map<String, String>) obj;
                USERS.putAll(loadedUsers);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous user data found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(USERS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValid(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        if (USERS.containsKey(username)) {
            return USERS.get(username).equals(password);
        }
        return false;
    }

    public static boolean addUser(String username, String newPassword) {
        if (USERS.containsKey(username)) {
            return false;
        }

        USERS.put(username, newPassword);
        saveUsers();
        return true;
    }
}