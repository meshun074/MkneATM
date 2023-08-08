package ATM_Machine;

import BankAccount.Account;
import BankAccount.AccountManager;

public class CashDeposit {
    //get user deposit
    public static String deposit(Account account)
    {
        System.out.println("Enter the amount you want to deposit");
        account.setBalance(account.getBalance()+getAmount());
        return "Current balance is: "+account.getBalance();
    }
    //ensure user enter appropriate amount
    private static double getAmount(){
        try {
            double amount;
            amount = Double.parseDouble(AccountManager.sc.nextLine());
            return amount;
        }
        catch (NumberFormatException e)
        {
            System.out.println("Incorrect amount, try again");
            return getAmount();
        }
    }
}
