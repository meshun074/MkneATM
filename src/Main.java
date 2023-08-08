import ATM_Machine.Authentication;
import ATM_Machine.OptionMenu;
import BankAccount.Account;

public class Main {
    public static void main(String[] args) {
        do {
            System.out.println("Hello and welcome MKNE ATM!");
            Account account=Authentication.login();
            if (account == null) {
                System.out.println("Incorrect credentials, try again later!");
            } else {
                OptionMenu.Menu(account);
            }
            System.out.println("Enter 1 to continue or 2 to exit");
        }while (OptionMenu.getOption(2)==1);
    }
}