package bankatm;

public class Account {
    private double[] balance = {0,0}; // Savings is index 0, checking is index 1
    private int withdrawals = 0;
    private final static String[] ACCOUNT_NAMES = {"savings", "checking"}; 
    private final static double FEE = 1.5; 
    
    // Constructor. Sets checking and savings balances.
    public Account(double savings, double checking) {
        balance[0] = savings;
        balance[1] = checking;
    }
    
    // Setters
    public void setSavingsBalance(double amount) {
        balance[0] = amount;
    }
    
    public void setCheckingBalance(double amount) {
        balance[1] = amount;
    }
    
    public void setWithdrawls(int num) {
        withdrawals = num;
    }
    
    // Getters
    public double getSavingsBalance() {
        return balance[0];
    }

    public double getCheckingBalance() {
        return balance[1];
    }
    
    public int getWithdrawals() {
        return withdrawals;
    }
    
    // Returns a formatted string of the balance of one sub-account
    public String displayBalance(int type) {
        String balanceString = String.format("$%.2f in %s account.", 
                balance[type], ACCOUNT_NAMES[type]);
        return balanceString;
    }
    
    // Transfers funds from one sub-account to the other. Takes as arguments the
    // originating account and the amount to be transferred.
    public String transfer (int type, double amount) throws InsufficientFundsException {
        String msg = "";
        if (balance[Math.abs(type - 1)] >= amount) {
            if (type == 0) {
                balance[1] -= amount;
                balance[0] += amount;
            }
            else if (type == 1) {
                balance[1] += amount;
                balance[0] -= amount;   
            }
            msg = String.format("Transfer successful. $%.2f transferred to "
                    + "%s account.", amount, ACCOUNT_NAMES[type]);
        }
        else {
            throw new InsufficientFundsException();
        }
        
        return msg;

    }
    
    // Withdraws from the specified account (remember, 0 = savings, 1 = checking)
    // so long as the amount is increments of $20 and there are sufficient funds
    // in the account. If more than four withdrawals have been made, the fee is also deducted.
    public String withdraw (int type, double amount) throws InsufficientFundsException {
        String msg = "";
        if (amount % 20 == 0) {
            if (balance[type] >= amount) {
                if (withdrawals > 3) {
                    amount += FEE;
                    balance[type] -= amount;
                    msg = String.format("Success. $%.2f withdrawn from %s "
                            + "account. Fee of $%.2f charged.", amount, 
                            ACCOUNT_NAMES[type], FEE);
                }
                else {
                    balance[type] -= amount;
                    withdrawals += 1;
                    msg = String.format("Success. $%.2f withdrawn from %s "
                            + "account.", amount, ACCOUNT_NAMES[type]);
                }                
                
            }
            else {
                msg = "Please try again.";
                throw new InsufficientFundsException();
            }
        }
        else {
            msg = "Please enter an increment of $20.";
        }
        
        return msg;
        
    }
    
    public String deposit (int type, double amount) {
        balance[type] += amount;
        return String.format("$%.2f deposited in %s account.", amount, 
                ACCOUNT_NAMES[type]);
    }

}