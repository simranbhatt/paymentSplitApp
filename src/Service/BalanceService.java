package Service;

import Model.Split;
import Model.User;

import java.util.Map;

public class BalanceService {

    public void addUserBalance(Split splits) {
        for (Map.Entry<User, Double> entry : splits.getSplits().entrySet()) {
            if(entry.getKey() != splits.getPayee()) {
                entry.getKey().addBalance(splits.getPayee(), entry.getValue());
            }
        }
    }

    public String getSingleUserBalances(User user) {
        if (user.getUserBalances().isEmpty()) {
            return "No balances";
        }
        String balance = "";
        for (Map.Entry<User, Double> entry : user.getUserBalances().entrySet()) {
            balance += user.getUserName() + " owes " + entry.getKey().getUserName() + ": " + entry.getValue().doubleValue() + "\n";
        }
        return balance;
    }

    public String getAllUserBalances() {
        UserService userService = new UserService();
        String allBalances = "";
        for (User eachUser : userService.getAllUsers().values()) {
            if(!eachUser.getUserBalances().isEmpty()) {
                allBalances += getSingleUserBalances(eachUser);
            }
        }
        return allBalances;
    }

}
