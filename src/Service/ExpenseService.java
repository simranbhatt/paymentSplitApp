package Service;

import Exceptions.IncorrectPercentagesException;
import Exceptions.InexactValuesException;
import Model.Expense;
import Model.Split;
import Model.User;

import java.util.List;

public class ExpenseService {

    SplitService splitService = new SplitService();

    public Split getSplitExpense(Expense expense, List<User> payers, List<Double> exactValues, List<Double> percentages) throws InexactValuesException, IncorrectPercentagesException {
        Split split = null;
        switch (expense.getExpenseType()) {
            case EQUAL -> {
                split = splitService.newEqualSplit(expense, payers);
                break;
            }
            case EXACT -> {
                split = splitService.newExactSplit(expense, payers, exactValues);
                break;
            }
            case PERCENT -> {
                split = splitService.newPercentSplit(expense, payers, percentages);
                break;
            }
        }
        return split;
    }
}
