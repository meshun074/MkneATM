package BankAccount;

public class Account {
    private String cus_name;
    private final String acc_number;
    private double balance;
    private final String acc_type;

    private String pin;
    private String cardId;

    public Account(String cus_name, String acc_number, double balance, String acc_type, String pin,String cardId) {
        this.cus_name = cus_name;
        this.acc_number = acc_number;
        this.balance = balance;
        this.acc_type = acc_type;
        this.pin=pin;
        this.cardId=cardId;
    }
    public Account(String cus_name, String acc_number, String acc_type, String pin,String cardId) {
        this.cus_name = cus_name;
        this.acc_number = acc_number;
        this.balance = 0.0;
        this.acc_type = acc_type;
        this.pin=pin;
        this.cardId=cardId;
    }

    public String getCus_name() {
        return cus_name;
    }

    public String getAcc_number() {
        return acc_number;
    }

    public double getBalance() {
        return balance;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public String getPin() {
        return pin;
    }

    public String getCardId() {
        return cardId;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
