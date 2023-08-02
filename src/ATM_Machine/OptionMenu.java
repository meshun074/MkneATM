package ATM_Machine;

import BankAccount.Account;

import java.util.Scanner;

public class OptionMenu {
    static Scanner sc =new Scanner(System.in);
    public static void Menu(Account account){
        int option=0;
        do {
            System.out.printf("""
                    ----Option Menu
                    Hello %s
                    Acc no:%s
                    Enter options to perform transaction
                    1.Check Balance
                    2.Cash Withdrawal
                    3.Cancel and EXit.""", account.getCus_name().substring(0, account.getCus_name().indexOf(" ")), account.getAcc_number());
            option = getOption(3);
            if(option == 1)
            {
                CheckBalance.DisplayBalance(account);
            } else if (option ==2) {

            }

        } while (option != 3);
    }
    public static int getOption(int limit) {
        int option = 0;
        while (option <= 0 || option > limit) {
            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input, try again!");
            }
        }
        return option;
    }
}
