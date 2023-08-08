package BankAccount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankManger {
    private static final File file = new File("src/BankAccount/banks.txt");
    public static void main(String[] args){
        boolean exit=false;
//        get user option to create a sample bank
        while (!exit){
            System.out.println("Hello and Welcome my bank creating system.\nPress 1 to create a Bank\nPress any key to exit.");
            try {
                if (Integer.parseInt(AccountManager.sc.nextLine()) == 1) {
                    createBanks();
                } else {
                    exit = true;
                }
            }catch (NumberFormatException e)
            {
                exit = true;
            }
        }
    }

//    Create bank using user input
    private static void createBanks()
    {
        String id = "";
        boolean invalid =true;
        String bankName="";
        String branchName="";
        while (invalid) {
            System.out.println("Enter bank name");
            bankName = AccountManager.sc.nextLine();
            System.out.println("Enter branch name");
            branchName = AccountManager.sc.nextLine();
            id = bankName.charAt(0) + String.valueOf(branchName.charAt(0))+"0";
            if(!checkValidity(bankName,id,branchName).equals(""))
            {
                invalid=false;
            }
        }

        Banks bank = new Banks(bankName, id, branchName);
        recordBank(bank);
    }

//    Save bank details in a file
    private static void recordBank(Banks bank) {
        try {
            FileWriter fw=new FileWriter(file,true);
            fw.write(bank.getName()+" "+bank.getId()+" "+bank.getBranch()+"\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    check if bank does not exist
    private static String checkValidity(String bankName,String id,String branchName) {
        try {
            Scanner fr= new Scanner(file);
            if(!fr.hasNext()){
                System.out.println("File was empty");
                return id;
            }
            while (fr.hasNext()){
                String data =fr.nextLine();
                if(data.substring(0,data.indexOf(" ")).equals(bankName) && data.substring(data.lastIndexOf(" ")+1).equals(branchName))
                {
                    return "";
                }
            }
            fr.close();
            return id;
        } catch (FileNotFoundException e) {
            System.out.println("File not found try again later");
            return "";
        }
    }

//    get all stored banks in file
    public static List<Banks> getBanks(){
        try {
            List<Banks> banks=new ArrayList<>();
            String text;
            Banks bk;
            Scanner fr1 =new Scanner(file);
            while (fr1.hasNext()){
                text=fr1.nextLine();
                bk = new Banks(text.substring(0,text.indexOf(" ")),text.substring(text.indexOf(" ")+1,text.lastIndexOf(" ")),text.substring(text.lastIndexOf(" ")+1));
                banks.add(bk);
            }
            return banks;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }
}