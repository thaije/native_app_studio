public class SavingsAccount {

	private int balance;

	public SavingsAccount() {
		balance = 0;
	}

	public SavingsAccount(int initialBalance) {
		balance = initialBalance;
	}



	public void greet() {
		System.out.println("Hello");
	}

	public void showBalance() {
		System.out.println("Balance is:" + balance);
	}

	public void withdraw(int howMuch) { 
		if(howMuch > 0)
			balance -= howMuch;
	}

	public void deposit(int howMuch) {
		if(howMuch > 0)
			balance += howMuch;
	}

}

