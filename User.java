import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public String username;
    public String password;
    public double balance = 0.0;
    public ArrayList<String> transactions = new ArrayList<>();
    public ArrayList<Loan> loans = new ArrayList<>();
    
    public String avatarPath = null;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
