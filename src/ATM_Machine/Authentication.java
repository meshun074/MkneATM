package ATM_Machine;

import BankAccount.Account;
import BankAccount.AccountManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Authentication {

    public static Account login() {
        File file = new File("src/BankAccount/account.txt");
        Scanner fr;
        Scanner input;
        String accDetails;

        try {
            input = new Scanner(System.in);
            for (int i = 1; i <= 3; i++) {
                fr = new Scanner(file);
                System.out.println("Enter Card Number");
                String idcard = input.nextLine();
                System.out.println("Enter pin");
                String pin = input.nextLine();
                while (fr.hasNext()) {
                    accDetails = fr.nextLine();
                    //Compare ID card number and pin with ID card number and pin in the database
                    if (accDetails.substring(accDetails.indexOf("CardNumber")+10,accDetails.indexOf(" Pin:")).contains(idcard) && accDetails.substring(accDetails.indexOf("Pin:")+4).contains(pin)) {
                        return AccountManager.readAccount(accDetails);
                    }
                }
                System.out.println("Incorrect pin or Card Number!!!");
            }
            return null;
        } catch (FileNotFoundException e) {
            System.out.println("File not found, try again another");
            return null;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
