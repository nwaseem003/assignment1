package mru.game.controller;

import java.io.IOException;
import java.util.ArrayList; //lets us store multiple players in a list
import mru.game.model.Player; //lets this class use the player class 
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import mru.game.view.AppMenu;


public class GameManager {

	private ArrayList<Player> players; //creates a field to hold player objects 
	private AppMenu menu;
	private CardDeck deck;
	

	public GameManager(){
		players = new ArrayList<Player>(); //intilaizes list so we can add players to it 
		menu = new AppMenu();
		deck = new CardDeck();
	}
	
	public void loadPlayers() throws IOException{
		File file = new File("res/CasinoInfo.txt");//reprsensts the file we want to acces and where to look

        if(!file.exists()){
			file.createNewFile();
			return;
		}

		Scanner input = new Scanner(file);//lets us read the information on that file

		while(input.hasNextLine()){
			String line = input.nextLine(); //reads that line and stores it in line
			//loop repeats until there are no more lines
		

		String[] data = line.split(","); //if line contains a comma splitting each comma gives us a value 

		String name = data[0];
		double balance = Double.parseDouble(data[1]);//we use this because it can comtain decimals
		int numberOfWins = Integer.parseInt(data[2]);//for the umber of wims we use this because it contains whole numbers 

		Player player = new Player(name,balance,numberOfWins);
		players.add(player);   //create aplayer object and that is saved in our arraylist 
		
	}

	input.close();
}
	public void savePlayers() throws IOException{
		File file = new File("res/CasinoInfo.txt");

		PrintWriter output = new PrintWriter(file); //lets us write to the file

		for (Player player : players){ //goes through every player in the list
			output.println(player.getName() + "," //retrieve player information and writes players infor on a line 
			+ player.formatMoney(player.getBalance()) + ","
			+ player.getNumberOfWins());

		}

		output.close(); //closes file when finished
	}

	public Player findPlayer(String name){ //recieves the name that we are looking for 
		for (Player player : players){ //for loop checks each player in the players ArrayList 
			if (player.getName().equalsIgnoreCase(name)){ //compares name with no need to check is lower or uppercase 
				return player;//gives back the matching player if found
			}
		}

		return null; //returns this is no matches were found 
	}


	public Player getOrCreatePlayer(String name){
		Player player = findPlayer(name); //checks whether the player already exists 

		if (player == null){ //checks if no matching player was found 
			player = new Player(name,100.0,0);//if they are a new plyer then they create a new player with $100 and zero wins
			players.add(player);//adds said no player to the list 
		}

		return player; //return the player whether they are new or not.
	}
	
    public ArrayList<Player> getTopPlayers() {
    ArrayList<Player> topPlayers = new ArrayList<Player>();

    if (players.isEmpty()) {
        return topPlayers;
    }

    int mostWins = players.get(0).getNumberOfWins();

    for (Player player : players) {
        if (player.getNumberOfWins() > mostWins) {
            mostWins = player.getNumberOfWins();
        }
    }

    for (Player player : players) {
        if (player.getNumberOfWins() == mostWins) {
            topPlayers.add(player);
        }
    }

    return topPlayers;
}

	
public void start() throws IOException {
    loadPlayers();

    String choice = "";

    while (!choice.equals("E")) {
        choice = menu.showMainMenu();

        if (choice.equals("P")) {
        	Player player = preparePlayer();
            if (player != null) { // preparePlayer returns null for a $0 player
                new BlackjackGame(deck, menu).play(player);
            }
        } else if (choice.equals("S")) {
            searchMenu();
        }
	} savePlayers():
    System.out.println("Player information saved. Goodbye!");

}

public void searchMenu() {
    String choice = "";

    while (!choice.equals("B")) {
        choice = menu.showSearchMenu();

        if (choice.equals("T")) {
            ArrayList<Player> topPlayers = getTopPlayers();

            if (topPlayers.isEmpty()) {
                System.out.println("No players found.");
            } else {
                System.out.println("Top player(s):");

                for (Player player : topPlayers) {
                    System.out.println("Name: " + player.getName());
                    System.out.println("Number of wins: "
                            + player.getNumberOfWins());
                }
            }

            menu.pause();
            choice = "B";
        }
        else if (choice.equals("N")) {
            String name = menu.askPlayerName();
            Player player = findPlayer(name);

            if (player == null) {
                System.out.println("Player not found.");
            } else {
                System.out.println("Name: " + player.getName());
                System.out.println("Balance: $" + player.getBalance());
                System.out.println("Wins: " + player.getNumberOfWins());
            }

            menu.pause();
            choice = "B";
        }
    }
}


public Player preparePlayer() {
    String name = menu.askPlayerName();

    while (name.isEmpty()) {
        System.out.println("Name cannot be empty.");
        name = menu.askPlayerName();
    }

    Player player = getOrCreatePlayer(name);

    if (player.getBalance() <= 0) {
        System.out.println("This player has no money left.");
        return null;
    }

    System.out.println("Welcome, " + player.getName() + "!");
    System.out.println("Your balance is: $" + player.getBalance());

    return player;
}


}



	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */

	

