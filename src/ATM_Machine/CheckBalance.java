package ATM_Machine;

import BankAccount.Account;

public class CheckBalance {
    public static int DisplayBalance(Account account)
    {
        System.out.println("Account Name: "+account.getCus_name());
        System.out.println("Account Number: "+account.getAcc_number());
        System.out.println("Account type: "+account.getAcc_type());
        System.out.println("Balance: "+account.getBalance());
        System.out.println("Enter 1 to continue or 2 to exit!!!");
        return OptionMenu.getOption(2);
    }
}
