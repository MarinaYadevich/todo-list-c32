package servlet;

import java.util.HashMap;
import java.util.Map;

//  Class for storing users like a database
public class UserRepository {
    private static final Map<String, String> USERS = new HashMap<>();

    static { // executed once when the class is loaded
        USERS.put("admin", "admin");
        USERS.put("user", "user");
    }

    public static boolean isValid(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        if (USERS.containsKey(username)) { // looking for such a key in the collection
            return USERS.get(username).equals(password); // we get the value by key and compare
        }
        return false;
    }

    public static boolean addUser(String username, String password) {
        if (USERS.containsKey(username)) {
            return false; // The user already exists
        }
        USERS.put(username, password);
        return true;
    }
}
