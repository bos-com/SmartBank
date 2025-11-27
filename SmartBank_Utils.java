public class SmartBank_Utils {

    public static boolean isValidUsername(String username){
        return username.matches("[A-Za-z0-9]{3,20}");
    }

    public static boolean isValidPassword(String password){
        return password.length() >= 6;
    }
}
