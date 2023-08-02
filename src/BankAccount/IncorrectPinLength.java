package BankAccount;

public class IncorrectPinLength extends Exception{
    public IncorrectPinLength(){
        super("Pin length has to be 4");
    }
}
