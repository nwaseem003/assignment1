
package mru.game.controller;
import static org.junit.jupiter.api.Assertions.*;
import mru.game.model.Player;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;


class JUnitTest {

	 @Test
	    void testCard() {
	        Card card = new Card(13, "Hearts");

	        assertEquals(10, card.getCardValue());
	    }

	    @Test
	    void testSuit() {
	        Card card = new Card(5, "Spades");

	        assertEquals("Spades", card.getSuit());
	    }

	    @Test
	    void testPlayer() {
	        Player player = new Player("Alex", 100.0, 0);

	        assertEquals("Alex", player.getName());
	        assertEquals(100.0, player.getBalance(), 0.001);
	    }

	    @Test
	    void testScore() {
	        ArrayList<Card> hand = new ArrayList<Card>();

	        hand.add(new Card(13, "Hearts"));
	        hand.add(new Card(9, "Spades"));

	        assertEquals(19, BlackjackGame.getScore(hand));
	    }

	    @Test
	    void testBust() {
	        ArrayList<Card> hand = new ArrayList<Card>();

	        hand.add(new Card(13, "Hearts"));
	        hand.add(new Card(9, "Spades"));
	        hand.add(new Card(5, "Clubs"));

	        assertTrue(BlackjackGame.isBust(hand));
	    }

	    @Test
	    void testPayout() {
	        assertEquals(10.0,
	                BlackjackGame.calculatePayout(BlackjackGame.WIN, 10),
	                0.001);
	    }
	}





