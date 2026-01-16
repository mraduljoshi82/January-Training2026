package pack5;

public class Account {
	   private static long accCounter = 1001; // auto-generated account number
	   private long accNum;
	   private double balance;
	   private Person accHolder;
	   private static final double MIN_BALANCE = 500.0;
	   // Constructor
	   public Account(Person accHolder, double initialBalance) {
	       this.accNum = accCounter++;
	       this.accHolder = accHolder;
	       if (initialBalance >= MIN_BALANCE) {
	           this.balance = initialBalance;
	       } else {
	           this.balance = MIN_BALANCE;
	           System.out.println("Initial balance too low. Minimum balance set to ₹500.");
	       }
	   }
	   // Deposit method
	   public void deposit(double amount) {
	       if (amount > 0) {
	           balance += amount;
	       }
	   }
	   // Withdraw method with minimum balance check
	   public void withdraw(double amount) {
	       if (balance - amount >= MIN_BALANCE) {
	           balance -= amount;
	       } else {
	           System.out.println("Withdrawal denied! Minimum balance of ₹500 must be maintained.");
	       }
	   }
	   // Get Balance
	   public double getBalance() {
	       return balance;
	   }
	   // Get Account Number
	   public long getAccNum() {
	       return accNum;
	   }
	   // Get Account Holder
	   public Person getAccHolder() {
	       return accHolder;
	   }
	   @Override
	   public String toString() {
	       return "Account [Account Number=" + accNum +
	              ", Holder=" + accHolder.getName() +
	              ", Balance=₹" + balance + "]";
	   }
	}
