README FILE
 
  This folder consists of 3 java class files.
          Account class   consists of name,account number,pin and amount in the  account, ifsc code and methods such as re-enter pin,generate otp, verify pin,change pin,view    balance,withdraw,deposit,change details.

In the Atm class I used hashmap to store the data of 5 accounts as index, account and then I executed all the process.

1.    firstly user is asked to enter the account number. If its not matching with any of the 5 account numbers then it prints invalid account number.
2.    If the account number entered is correct then user will be asked to enter the pin.If the entered pin doesn’t match with the pin of respective account,it will be shown as   i       invalid or incorrect pin.
3.    If the entered pin is correct, user will be asked to choose one among 5 options i.e,deposit,withdraw,view balance,transfer to other account ,change details.
4.    After selection, 6 digit otp is displayed.user has to enter the otp.If the entered otp is correct then the respective operations are performed.

       In the main class,I just created a constructor and called the method
       Start atm. 
