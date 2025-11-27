import java.util.HashMap;

public class Bank {
    public static HashMap<String, User> users = new HashMap<>();
    public static double collectedInterest = 0.0;

    public static boolean register(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, new User(username, password));
        return true;
    }

    public static User getUser(String username) {
        return users.get(username);
    }

    public static double totalBankAsset() {
        double total = collectedInterest;
        for (User u : users.values()) total += u.balance;
        return total;
    }
}
