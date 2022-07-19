import Exceptions.IncorrectPercentagesException;
import Exceptions.InexactValuesException;
import Service.BalanceService;
import Service.ExpenseService;
import Service.UserService;
import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws InexactValuesException, IncorrectPercentagesException {
        UserService userService = new UserService();
        ExpenseService expenseService = new ExpenseService();
        BalanceService balanceService = new BalanceService();
        Scanner input = new Scanner(System.in);

        System.out.println("To add user: USER <numerical user ID> <username> <email> <phone number> \n Add expense: \n EXPENSE <user-id-of-person-who-paid> <amount> <no-of-users> <space-separated-list-of-users> <EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal> \n Show balances for all: \n SHOW \n Show balances for a single user: \n SHOW <user-id> \n");
        System.out.println("Enter command: ");
        while(true) {
            String command = input.nextLine();
            String[] inputs = command.split(" ");
            String commandType = inputs[0];
            switch(commandType) {
                case "USER" -> {
                    int userId = Integer.parseInt(inputs[1]);
                    String userName = inputs[2];
                    String userEmail = inputs[3];
                    String userMobileNumber = inputs[4];
                    userService.addUser(userId, userName, userEmail, userMobileNumber);
                    break;
                }
                case "SHOW" -> {
                        System.out.println(inputs.length == 1 ? balanceService.getAllUserBalances() :
                                balanceService.getSingleUserBalances(userService.getUserById(Integer.parseInt(inputs[1]))));
                        break;
                    }
                case "EXPENSE" -> {
                    int paidById = Integer.parseInt(inputs[1]);
                    Double amount = Double.parseDouble(inputs[2]);
                    int noOfUsers = Integer.parseInt(inputs[3]);
                    List<User> payees = new ArrayList<>();
                    for(int i = 4; i < noOfUsers + 4; i++) {
                        payees.add(userService.getUserById(Integer.parseInt(inputs[i])));
                    }
                    ExpenseType expenseType = ExpenseType.valueOf(inputs[4 + noOfUsers]);
                    Expense expense = new Expense(userService.getUserById(paidById), amount, expenseType);
                    switch (expenseType) {
                        case EQUAL -> {
                            expenseService.getSplitExpense(expense, payees, null, null);
                            break;
                        }
                        case EXACT -> {
                            List<Double> exactValues = new ArrayList<>();
                            for(int i = 1; i <= noOfUsers; i++) {
                                exactValues.add(Double.parseDouble(inputs[4 + noOfUsers + i]));
                            }
                                expenseService.getSplitExpense(expense, payees, exactValues, null);
                            break;
                            }
                        case PERCENT -> {
                            List<Double> percentages = new ArrayList<>();
                            for(int i = 1; i <= noOfUsers; i++) {
                                percentages.add(Double.parseDouble(inputs[4 + noOfUsers + i]));
                            }
                            expenseService.getSplitExpense(expense, payees, null, percentages);
                            break;
                        }
                    }
                    System.out.println("Split added.");
                    break;
                }
            }
        }
    }
}
