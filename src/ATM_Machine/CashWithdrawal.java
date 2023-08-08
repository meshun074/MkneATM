package ATM_Machine;

import BankAccount.Account;
import BankAccount.AccountManager;

public class CashWithdrawal {
    protected static String Cashout(Account account)
    {
        System.out.println("Enter the amount you want to withdraw");
        double amount;
        // Ensure user withdrawal does not exceed current deposit
        try {
            amount = Double.parseDouble(AccountManager.sc.nextLine());
            if (account.getBalance()<amount || account.getBalance()-amount<=20)
            {
                return "Insufficient balance";
            }
            else
            {
                account.setBalance(account.getBalance()-amount);
                System.out.println("Remove your card and press enter");
                AccountManager.sc.nextLine();
                System.out.println("Press enter to take your cash");
                AccountManager.sc.nextLine();
                return "Your new balance is "+account.getBalance();
            }
            //throw an error for incorrect input
        } catch (NumberFormatException e) {
            return "Invalid Amount: ";
        }
    }
}
