package Model;

public class Expense {
    private User user;
    private double amount;
    private ExpenseType expenseType;

    public Expense(User user, double amount, ExpenseType expenseType) {
        this.user = user;
        this.amount = amount;
        this.expenseType = expenseType;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }
}
