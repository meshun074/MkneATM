package BankAccount;

import ATM_Machine.OptionMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountManager {
    private static final File file = new File("src/BankAccount/account.txt");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Hello and Welcome my account creating system.\nPress 1 to create an account\nPress any key to exit.");
            try {
                if (Integer.parseInt(sc.nextLine()) == 1) {
                    createAccount();
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                exit = true;
            }
        }
    }

    private static void createAccount() {
        boolean invalid = true;
        String bankCode="";
        String cus_name = "";
        double balance;
        String acc_type;
        List<Banks> banksList = new ArrayList<>(Objects.requireNonNull(BankManger.getBanks()));
        String pin;
        while (invalid) {
            AtomicInteger counter = new AtomicInteger();
            System.out.println("Enter your name");
            cus_name = sc.nextLine();
            banksList.forEach(x -> System.out.println(counter.incrementAndGet() + " " + x.getName()));
            System.out.println("Select bank name");
            bankCode = banksList.get(OptionMenu.getOption(banksList.size())- 1).getId();
            System.out.println("Account type \n1. Saving 2. Current \nSelect account type name");
            acc_type = OptionMenu.getOption(2) == 1 ? "Saving" : "Current";
            System.out.println("Enter initial balance");
            try {
                balance = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                balance = 0;
            }
            System.out.println("Enter 4 digit pin for VISA card verification");
            pin = getPin();
            if (addAccount(cus_name, acc_type, balance, bankCode,pin)) {
                invalid = false;
            }
            System.out.println("Done");
        }
    }

    private static String createAccNumber(){
        int start=1000000000;
        long end= 9999999999L;
        Random random =new Random();
        //get the range, casting to long to avoid overflow problems
        long range = end - (long)start + 1;
        // compute a rvalue of the range, 0 <= frac < range
        long rvalue = (long)(range * random.nextDouble());
        long accNumber =  rvalue + (long)start;
        return String.valueOf(accNumber);
    }

    private static String cardNumber(){
        int start=10000000;
        long end= 99999999L;
        Random random =new Random();
        //get the range, casting to long to avoid overflow problems
        long range = end - (long)start + 1;
        // compute a rvalue of the range, 0 <= frac < range
        long rvalue = (long)(range * random.nextDouble());
        long cardNumber =  rvalue + (long)start;
        return String.valueOf(cardNumber);
    }

    private static boolean addAccount(String cusName, String accType, double balance, String bankCode, String pin) {
        String accNumber=bankCode+createAccNumber();
        Account acc = new Account(cusName, accNumber,balance,accType,pin, cardNumber());
        if (recordAccount(acc)){
            System.out.println("Account created successfully");
            return true;
        }
        return false;
    }


    private static String getPin() {
        String pin = "";
        try {
            pin = sc.nextLine();
            if (pin.length() != 4) {
                throw new IncorrectPinLength();
            }
            Integer.parseInt(pin);
        } catch (NumberFormatException | IncorrectPinLength e) {
            System.out.println("Incorrect pin, try again");
            return getPin();
        }
        return pin;
    }

    private static boolean recordAccount(Account account) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write("Name:" +account.getCus_name() + " Account:" + account.getAcc_number() + " Acc_Type:" + account.getAcc_type() +  " Balance:" + account.getBalance() + " CardNumber:" + account.getCardId()+" Pin:" + account.getPin() + "\n");
            System.out.println(account.getAcc_number());
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Unable to write in file");
            return false;
        }
    }
    public static List<Account> getAccounts(){
        try {
            List<Account> accounts=new ArrayList<>();
            String text;
            Scanner fr =new Scanner(file);
            while (fr.hasNext()){
                text=fr.nextLine();
                accounts.add(readAccount(text));
            }
            return accounts;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }

    public static Account readAccount(String text)
    {
        String accName=text.substring(5,text.indexOf(" Account"));
        String accNumber=text.substring(text.indexOf("Account:")+8,text.indexOf(" Acc_Type"));
        String accType=text.contains("Saving")?"Saving":"Current";
        double balance=Double.parseDouble(text.substring(text.indexOf("Balance:")+8,text.indexOf(" CardNumber")));
        String idcard =text.substring(text.indexOf("CardNumber")+10,text.indexOf(" Pin:"));
        String pin =text.substring(text.indexOf("Pin:")+4);
        return new Account(accName,accNumber,balance,accType,pin,idcard);
    }
}
