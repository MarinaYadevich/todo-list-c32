package servlet;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static final Map<String, String> USERS = new HashMap<>();

    static { // выполняется один раз при загрузке класса
        USERS.put("admin", "admin");
        USERS.put("user", "user");
    }

    public static boolean isValid(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        if (USERS.containsKey(username)) { // ищет в коллекции такой ключ
            return USERS.get(username).equals(password); // достаем по ключу значение и сравниваем
        }
        return false;
    }
}
