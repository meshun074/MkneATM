package ATM_Machine;

import BankAccount.Account;
import BankAccount.AccountManager;

public class OptionMenu {

    public static void Menu(Account account){
        int option;
        String message;
        do {
//            displays menu
            System.out.printf("""
                    ----Option Menu
                    Hello %s
                    Acc no:%s
                    Enter options to perform transaction
                    1.Check Balance
                    2.Cash Withdrawal
                    3.Cash Deposit
                    4.Cancel and EXit.
                    """, account.getCus_name().substring(0, account.getCus_name().indexOf(" ")), account.getAcc_number());
            option = getOption(4);
            if(option == 1)
            {
                //check balance
                if(CheckBalance.DisplayBalance(account)==2) {
                    option = 4;
                    AccountManager.updateAccount(account);
                }
            } else if (option ==2) {
                //withdraw money
                message=CashWithdrawal.Cashout(account);
                System.out.println(message);
                AccountManager.sc.nextLine();
            }
            else if (option ==3){
                //deposit
                System.out.println(CashDeposit.deposit(account));
            }
            else
            {
                //update file before existing
                AccountManager.updateAccount(account);
            }
        } while (option != 4);
    }

    //gets user input option
    public static int getOption(int limit) {
        int option = 0;
        while (option <= 0 || option > limit) {
            try {
                option = Integer.parseInt(AccountManager.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input, try again!");
            }
        }
        return option;
    }
}
