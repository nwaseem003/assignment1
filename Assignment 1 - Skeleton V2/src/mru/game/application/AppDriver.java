package mru.game.application;
import mru.game.controller.GameManager;
import java.io.IOException;

public class AppDriver {

	public static void main(String[] args) throws IOException {
		new GameManager().start();
		
		// This is the starting point of the project!
		// hint - It usually calls method from GameManager class to start the app, so only one or two lines will be written here

	}

}
