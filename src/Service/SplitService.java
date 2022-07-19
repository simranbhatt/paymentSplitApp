package Service;

import Exceptions.IncorrectPercentagesException;
import Exceptions.InexactValuesException;
import Model.Expense;
import Model.Split;
import Model.User;

import java.util.*;

public class SplitService {

    BalanceService balanceService = new BalanceService();

    public Split newEqualSplit(Expense expense, List<User> splitters) {
        int numberOfSplitters = splitters.size();
        double equalSplit = expense.getAmount()/numberOfSplitters;
        HashMap<User, Double> splits = new HashMap<>();
        splitters.forEach(splitter -> {
            splits.put(splitter, equalSplit);
        });
        Split totalSplit = new Split(expense);
        totalSplit.setSplits(splits);
        balanceService.addUserBalance(totalSplit);
        return totalSplit;
        }

    public Split newExactSplit(Expense expense, List<User> splitters, List<Double> exactValues) throws InexactValuesException {
        double sumExactValues = exactValues.stream().mapToDouble(Double::doubleValue).sum();
        if(sumExactValues != expense.getAmount()) {
            throw new InexactValuesException("Values do not add up to the expense");
        }
        HashMap<User, Double> splits = new HashMap<>();
        Iterator<User> iterateSplitters = splitters.iterator();
        Iterator<Double> iterateExactValues = exactValues.iterator();
        while(iterateSplitters.hasNext() && iterateExactValues.hasNext()) {
            splits.put(iterateSplitters.next(), iterateExactValues.next());
        }
        Split totalSplit = new Split(expense);
        totalSplit.setSplits(splits);
        balanceService.addUserBalance(totalSplit);
        return totalSplit;
    }

    public Split newPercentSplit(Expense expense, List<User> splitters, List<Double> percentages) throws IncorrectPercentagesException {
        double sumPercentages = percentages.stream().mapToDouble(Double::doubleValue).sum();
        if(sumPercentages != 100) {
            throw new IncorrectPercentagesException("Values do not represent 100% of the expense");
        }
        List<Double> percentValues = new ArrayList<>();
        HashMap<User, Double> splits = new HashMap<>();
        Iterator<User> iterateSplitters = splitters.iterator();
        Iterator<Double> iteratePercentages = percentages.iterator();
        double totalAmount = expense.getAmount();
        while(iteratePercentages.hasNext()){
            double value = (totalAmount*iteratePercentages.next().doubleValue())/100;
            percentValues.add(value);
        }
        iteratePercentages = percentValues.iterator();
        while(iterateSplitters.hasNext() && iteratePercentages.hasNext()) {
            splits.put(iterateSplitters.next(), iteratePercentages.next());
        }
        Split totalSplit = new Split(expense);
        totalSplit.setSplits(splits);
        balanceService.addUserBalance(totalSplit);
        return totalSplit;
    }


}



