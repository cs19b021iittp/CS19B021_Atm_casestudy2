
import java.util.Scanner;

public class Account {

    Account(int accountNumber, String name, int pin, int amount, int ifscCode){
        this.pin = pin;
        this.accountNumber =accountNumber;
        this.name = name;
        this.amount = amount;
        this.ifscCode = ifscCode;
    }

    private String name;
    private String phoneNumber;
    private int accountNumber;
    private int ifscCode;
    private int pin;
    private int amount;

    public String getName() {
        return name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean reEnterPin(){
        System.out.println("Please re-enter your current PIN: ");
        Scanner in = new Scanner(System.in);
        int pin = in.nextInt();
        int otp = generateOtp();
        System.out.println("****  The OTP received by the customer is :"+otp+"  *****");
        System.out.println("Please enter the 6-digit OTP you've received: ");
        int enteredOtp = in.nextInt();
        if(!verifyPin(pin)||otp!=enteredOtp) {
            System.out.println("The pin/otp you've entered is incorrect.");
            return false;
        }
        return true;
    }

    private int generateOtp() {
        double a = Math.random();
        a = a * 900000;
        a = a + 100000;
        return (int) a;
    }

    public boolean verifyPin(int pin) {
        if(pin!=this.pin) {
            return false;
        }
        return true;
    }

    public void changePin(){
        if(!reEnterPin()) return;
        System.out.println("Please enter your new 5-digit PIN: ");
        Scanner in = new Scanner(System.in);
        int pin = in.nextInt();
        if(pin<10000||pin>99999){
            System.out.println("Invalid PIN. Your pin change has FAILED.");
            return;
        }
        this.pin = pin;
        System.out.println("Your PIN is successfully changed.");
        return;
    }

    public int getAmount() {
        return amount;
    }

    public int viewBalance(){
        System.out.println("Your current balance is : "+ amount);
        return amount;
    }

    public void changeAmount(int amount) {
        this.amount += amount;
    }

    public void deposit(){
        if(!reEnterPin()) return;
        System.out.println("Please place your amount in the deposit tray and enter the correct amount below: ");
        Scanner in = new Scanner(System.in);
        int amount = in.nextInt();
        if(amount%100==0&&amount<4000000) {
        changeAmount(amount);
        viewBalance();
        return;}
        else {
        	System.out.println("amount you entered is either exceeding the limit or not present in 100 notes.");
        	return;
        }
    }

    public void withdraw(){
        System.out.println("Please enter the amount you want to withdraw ");
        Scanner in = new Scanner(System.in);
        int amount = in.nextInt();
        if(!reEnterPin()) return;
        if(amount>this.amount||amount%100!=0) {
            System.out.println("Insufficient funds.or amount you want to withdraw is not in 100 notes");
            return;
        }
        changeAmount(-amount);
        viewBalance();
        return;
    }

    public int getIfscCode() {
        return ifscCode;
    }

    public void setName() {
        System.out.println("Enter your new name : ");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        this.name = name;
    }

    public void setPhoneNumber() {
        System.out.println("Enter your new phone number : ");
        Scanner in = new Scanner(System.in);
        String phoneNumber = in.next();
        this.phoneNumber = phoneNumber;
    }

    public void changeDetails() {
        System.out.println("Kindly enter a number corresponding to your desired change:");
        System.out.println("1. Change name\n" +
                "2. Change phone number\n" +
                "3. Change pin");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        if(option<1||option>3){
            System.out.println("Invalid option selected.");
            return;
        }
        if(!reEnterPin()) return;
        if(option==1) this.setName();
        else if(option==2) this.setPhoneNumber();
        else if(option==3) this.changePin();
    }
}
