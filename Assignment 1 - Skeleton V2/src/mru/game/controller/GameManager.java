package mru.game.controller;

import java.io.IOException;
import java.util.ArrayList; //lets us store multiple players in a list
import mru.game.model.Player; //lets this class use the player class 
import java.io.File;
import java.util.Scanner;


public class GameManager {

	private ArrayList<Player> players; //creates a field to hold player objects 

	public GameManager(){
		players = new ArrayList<Player>(); //intilaizes list so we can add players to it 
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



	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */

	

}
