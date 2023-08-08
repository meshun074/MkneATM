package BankAccount;

public class IncorrectPinLength extends Exception{
    // throws an error for incorrect pin
    public IncorrectPinLength(){
        super("Pin length has to be 4");
    }
}
