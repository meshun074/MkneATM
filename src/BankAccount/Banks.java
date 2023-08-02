package BankAccount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Banks {
    private String name;
    private final String id;
    private String Branch;
    private List<Account> accounts;

    public Banks(String name, String id, String branch, List<Account> accounts) {
        this.name = name;
        this.id = id;
        Branch = branch;
        this.accounts = accounts;
    }
    public Banks(String name, String id, String branch) {
        this.name = name;
        this.id = id;
        Branch = branch;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getBranch() {
        return Branch;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public boolean isBankAccount(Account acc_number)
    {
        return getAccounts().stream().anyMatch(x-> x.getAcc_number().equals(acc_number.getAcc_number()));
    }
    public List<Account> getAccounts(){
        accounts =new ArrayList<>();
        for(Account x: Objects.requireNonNull(AccountManager.getAccounts()))
        {
            if(x.getAcc_number().startsWith(getId())){
                accounts.add(x);
            }
        }
        return accounts;
    }
}
