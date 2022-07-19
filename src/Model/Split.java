package Model;

import java.util.Map;
import java.util.HashMap;

public class Split {
    private Expense expense;
    Map <User, Double> splits;

    public Split(Expense expense) {
        this.expense = expense;
    }

    public void setSplits(HashMap <User, Double> splits) {
        this.splits = splits;
    }

    public Map<User, Double> getSplits() {
        return splits;
    }

    public User getPayee() {
        return expense.getUser();
    }



}
