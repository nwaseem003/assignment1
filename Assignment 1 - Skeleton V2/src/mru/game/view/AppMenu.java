package mru.game.view;

import java.util.Scanner;


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
		System.out.println("\nPress Enter to continue...");//waits for user to press enter before continuing
		input.nextLine();
	}

	public String askPlayerName(){
		System.out.println("Enter player name: ");
		return input.nextLine().trim();
	}

	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */
}
