package mru.game.model;

public class Player {
	
	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */
	private String name;
	private double balance;
	private int numberOfWins;
	public Player (String name, double balance, int numberOfWins) {
		this.name = name;
		this.balance = balance;
		this.numberOfWins = numberOfWins;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance=balance;
	}
	public int getNumberOfWins() {
		return numberOfWins;
	}
	public void setNumberOfWins(int numberOfWins) {
		this.numberOfWins = numberOfWins;
	}	
	@Override
	public String toString() {
		return name + ", " + balance +", " + numberOfWins;
	}
	public static String formatMoney(double amount) {
	    long wholeAmount = (long) amount;

	    if (amount == wholeAmount) {
	        return String.valueOf(wholeAmount);
	    } else {
	        return String.format("%.2f", amount);
	    }
	}
}
