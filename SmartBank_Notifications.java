public class SmartBank_Notifications {

    public static void notifyUser(User user, String message){
        user.transactions.add("Notification: " + message);
    }

    public static void notifyAllUsers(String message){
        for(User u : Bank.users.values()){
            notifyUser(u, message);
        }
    }
}
