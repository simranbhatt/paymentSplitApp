package Model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private String userMobileNumber;

    private Map<User, Double> userBalances = new HashMap<>();

    public User(int userId, String userName, String userEmail, String userMobileNumber) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userMobileNumber = userMobileNumber;
    }

    public int getUserId() { return userId; }

    public String getUserName() {
        return userName;
    }

    public String getUserMobileNumber() {
        return userMobileNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void addBalance(User payTo, double amount) {
        userBalances.put(payTo, amount);
    }

    public Map<User, Double> getUserBalances() {
        return userBalances;
    }

}
