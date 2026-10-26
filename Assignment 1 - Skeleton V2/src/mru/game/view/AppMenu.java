package mru.game.view;

import java.util.Scanner;
import java.util.ArrayList;

import mru.game.controller.Card;
import mru.game.model.Player;


public class AppMenu {
	private Scanner input;

	public AppMenu(){
		input = new Scanner(System.in);
	}

	public String showMainMenu() {
		System.out.println("\n--- MAIN MENU ---");
		System.out.println("(P) Play Game");
		System.out.println("(S) Search");
		System.out.println("(E) Exit");
		System.out.println("Enter your choice: ");

		String choice = input.nextLine().trim().toUpperCase();

		while (!choice.equals("P")
			&& !choice.equals("S")
		    && !choice.equals("E")) {
				System.out.println("Invalid choice.Please enter P,S or E.");
				System.out.println("Enter your choice: ");
				choice = input.nextLine().trim().toUpperCase();
			}

			return choice;
	}

	public String showSearchMenu(){
		System.out.println("\n--- SEARCH MENU ---");
		System.out.println("(T) Top player");
		System.out.println("(N) Search for a Name");
		System.out.println("(B) Back to main menu");
		System.out.println("Enter your choice: ");

		String choice = input.nextLine().trim().toUpperCase();

		while (!choice.equals("T")
			&& !choice.equals("N")
		    && !choice.equals("B")) {
				System.out.println("Invalid choice.Please eneter T,N or B.");
				System.out.println("Enter your choice: ");
				choice = input.nextLine().trim().toUpperCase();
			}
		return choice;
	}

	public void pause(){
		System.out.println("\nPress Enter to continue...");/**waits for user to press enter before continuing */
		input.nextLine();
	}

	public String askPlayerName(){
		System.out.println("Enter player name: ");
		return input.nextLine().trim();
	}
	
	public int promptBet(double balance, int minBet) {
		while (true) {
			System.out.print("\nHow much do you want to bet this round? ");

			if (!input.hasNextInt()) {
				input.nextLine(); /**throw away the bad input */
				System.out.println("Invalid amount! Please enter a whole number.");
				continue;
			}

			int bet = input.nextInt();
			input.nextLine(); /**clear the rest of the line */

			if (bet < minBet)
				System.out.println("The minimum bet is $" + minBet + ".");
			else if (bet > balance)
				System.out.println("You cannot bet more than your balance ($"
						+ Player.formatMoney(balance) + ").");
			else
				return bet;
		}}
	

	public void showMessage(String message) {
		System.out.println("\n" + message);}

	public boolean promptHit() {
		while (true) {
			System.out.println("\nSelect an option:\n");
			System.out.println("\t1.Hit");
			System.out.println("\t2.Stand\n");
			System.out.print("You choice:");

			String choice = input.nextLine().trim();
			if (choice.equals("1"))
				return true;
			if (choice.equals("2"))
				return false;

			System.out.println("Invalid choice! Please enter 1 or 2.");
		}}

public boolean promptContinue() {
	while (true) {
		System.out.print("\nDo you want to continue(y/n)? ");
		String choice = input.nextLine().trim();

		if (choice.equalsIgnoreCase("Y"))
			return true;
		if (choice.equalsIgnoreCase("N"))
			return false;

		System.out.println("Invalid choice! Please enter y or n.");
	}}

public void showTable(ArrayList<Card> playerHand, ArrayList<Card> dealerHand, boolean hideDealer) {
    System.out.println("\n       - BLACKJACK -");
    System.out.println("+-----------------------+----------------------+");
    System.out.println("| PLAYER                | DEALER               |");
    System.out.println("+-----------------------+----------------------+");

    int rows = playerHand.size();

    if (dealerHand.size() > rows) {
        rows = dealerHand.size();
    }

    for (int i = 0; i < rows; i++) {
        String p = "";
        String d = "";

        if (i < playerHand.size()) {
            p = playerHand.get(i).toString();
        }

        if (i < dealerHand.size()) {
            if (hideDealer && i == 1) {
                d = "Hidden";
            } else {
                d = dealerHand.get(i).toString();
            }
        }

        System.out.printf("| %-22s | %-21s |%n", p, d);
        System.out.println("+-----------------------+----------------------+");
    }
}


}
	