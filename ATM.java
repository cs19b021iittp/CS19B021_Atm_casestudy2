
import java.util.*;

 class Atm {

    Map< Integer, Account> accountsMap = new HashMap<Integer, Account>();

    private Atm(){}; //default constructor shouldn't be used.

    Atm(int id){ //Should provide id to construct this class
        this.id = id;
        initAccounts();
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void startAtm(){
        while(true){
            System.out.println("SBI Bank welcomes you \n");
            System.out.println("Please enter valid account number : ");

            Scanner in = new Scanner(System.in);
            int accountNumber = in.nextInt();
            if(accountNumber==0) return;
            if(accountsMap.get(accountNumber)==null) {
                System.out.println("The account number  you've entered is either incorrect or invalid.");
                continue;
            }

            System.out.println("Please enter PIN associated with this account : ");
            int pin = in.nextInt();

            

            Account account = accountsMap.get(accountNumber);

            if(!account.verifyPin(pin)) {
                System.out.println("The  PIN you've entered is either incorrect or invalid.");
                continue;
            }

            transaction(account,accountsMap);
        }
    }

    private void accountTransfer(Map<Integer, Account> accountsMap, Account account) {
        System.out.println("Enter the recipient account number :");
        Scanner in = new Scanner(System.in);
        int recipient = in.nextInt();
        System.out.println("Enter the recipient IFSC code :");
        in = new Scanner(System.in);
        int ifscCode = in.nextInt();
        Account recipientAccount ;
        if(accountsMap.get(recipient)==null) {

            System.out.println("The account number you've entered is either incorrect or invalid.");
            return;
        } else {
            recipientAccount = accountsMap.get(recipient);
            if(recipientAccount.getIfscCode()!=ifscCode){
                System.out.println("The account number/IFSC code combination you've entered is either incorrect or invalid.");
                return;
            }
        }
        System.out.println("Enter the amount you want to transfer :");
        int amount = in.nextInt();
        if(!account.reEnterPin()) return;
        if(amount>account.getAmount()&&amount>0){
            System.out.println("Insufficient Funds or invalid input");
            return;
        }
        account.changeAmount(-amount);
        recipientAccount.changeAmount(amount);
        System.out.println("Funds transferred successfully");
        account.viewBalance();
    }

    private void transaction(Account account, Map<Integer, Account> accountsMap) {
        System.out.println("Welcome back, "+ account.getName()+"!");
        System.out.println("Kindly enter a number corresponding to your transaction:");
        System.out.println("1. Deposit amount\n" +
                "2. Withdraw amount\n" +
                "3. View Balance\n" +
                "4. Account to account transfer\n" +
                "5. Change details");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        if(option<1||option>5){
            System.out.println("Invalid option selected.");
            return;
        }
        if(option==1) account.deposit();
        else if(option==2) account.withdraw();
        else if(option==3) account.viewBalance();
        else if(option==4) accountTransfer(accountsMap,account);
        else if (option==5) account.changeDetails();

        System.out.println("\nYour transaction is complete. Have a great day, "
                + account.getName()+".\nThank you and visit us again." +
                "\n\n============================================\n\n");

        return;
    }

    private void initAccounts(){
        addAccount(accountsMap,12345,"Bahubali",12344,9000,1234);
        addAccount(accountsMap,12346,"Ballaladeva",23456,2000,1234);
        addAccount(accountsMap,11223,"Shivagami",12345,1000,1234);
        addAccount(accountsMap,12333,"kattappa",12332,3000,1235);
        addAccount(accountsMap,12334,"davasena",12311,5000,1235);
    }

    private void addAccount(Map<Integer, Account> accountsMap, int accountNumber, String name, int pin, int amount, int ifscCode) {
        Account a = new Account(accountNumber,name,pin,amount,ifscCode);
        accountsMap.put(a.getAccountNumber(),a);
        return ;
    }

}
