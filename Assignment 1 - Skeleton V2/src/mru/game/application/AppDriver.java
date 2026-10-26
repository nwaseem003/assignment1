package mru.game.application;
import mru.game.controller.GameManager;
import java.io.IOException;

public class AppDriver {

	public static void main(String[] args) throws IOException {
		new GameManager().start();

	}

}
