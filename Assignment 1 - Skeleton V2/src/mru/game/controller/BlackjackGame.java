package mru.game.controller;

import java.io.IOException;
import java.util.ArrayList;
import mru.game.model.Player; // lets this class use the player class
import mru.game.view.AppMenu; // lets this class use the player class

public class BlackjackGame {

		
		private CardDeck deck; // deck used in games
		private AppMenu menu; // shows the game and prompts the user
		
		public static final int MIN_BET = 2; // minimum bet allowed
		public static final int DEALER_STAND = 17; // delaer draws until he reaches this amt

		
		public static final String PLAYER_NATURAL = "PLAYER_NATURAL";
		public static final String WIN = "WIN";
		public static final String LOSE = "LOSE";
		public static final String PUSH = "PUSH";
		
			

		public BlackjackGame(CardDeck deck, AppMenu menu){
			this.deck = deck;
			this.menu = menu;
		}

		public void play(Player player) { // this plays a round with the player until they're broke or want to stop
			while (true) {
				if (player.getBalance() < MIN_BET) {
					System.out.println("You do not have enough balance to play (minimum bet is $" + MIN_BET + ").");
					return;
				}

				int bet = menu.promptBet(player.getBalance(),MIN_BET);
				playRound(player, bet);

				if (player.getBalance()<MIN_BET){
					System.out.println("You do not have enough balance.");
					return;
				}

				if (!menu.promptContinue())
					return;
			}
		}

		public void playRound(Player player, int bet){ // plays a round and updates the players balance
			ArrayList<Card> playerHand = new ArrayList<Card>();
			ArrayList<Card> dealerHand = new ArrayList<Card>();

			playerHand.add(deck.dealCard());
			dealerHand.add(deck.dealCard());
			playerHand.add(deck.dealCard());
			dealerHand.add(deck.dealCard());

			String result; // one of the player_natural, win, lose, push

			
			
			if (isNatural(playerHand) || isNatural(dealerHand)) { // Naturals are settled immediately
				menu.showTable(playerHand, dealerHand, false);
				result = evaluate(playerHand, dealerHand);
				
			}
			else { // The player goes first
				while (true) {
					menu.showTable(playerHand, dealerHand, true);
					if (isBust(playerHand) || getScore(playerHand) == 21)
						break;
					if (menu.promptHit())
						playerHand.add(deck.dealCard());
					else
						break;
				}
				
				
				if (isBust(playerHand)){ // dealer doesn't need to play any cards
					result = LOSE;
			
				} else {
					playDealerHand(dealerHand);
					menu.showTable(playerHand, dealerHand, false);
					result = evaluate(playerHand, dealerHand);
				}
		}
			
			
			double payout = calculatePayout(result, bet);
			player.setBalance(player.getBalance() + payout);

			   if (result.equals(WIN) || result.equals(PLAYER_NATURAL))
			       player.setNumberOfWins(player.getNumberOfWins() + 1);

			   if (result.equals(PLAYER_NATURAL))
			       menu.showMessage("Blackjack! You won $" + Player.formatMoney(payout));
			   else if (result.equals(WIN))
			       menu.showMessage("You won $" + Player.formatMoney(payout));
			   else if (result.equals(LOSE))
			       menu.showMessage("You lost $" + Player.formatMoney(bet));
			   else
			       menu.showMessage("It's a tie! Your bet is returned.");
			}
		}
		
		public void playDealerHand(ArrayList<Card> dealerHand) {
			while (getScore(dealerHand) < DEALER_STAND)
				dealerHand.add(deck.dealCard());
		}
		
		public static int getScore(ArrayList<Card> hand) {
			int total = 0;
			int aces = 0;

			for (Card card : hand) {
				int rank = card.getRank();
				if (rank == 1) {
					aces++;
					total += 1;
				} else if (rank >= 10) {
					total += 10; // 10, Jack, Queen and King
				} else {
					total += rank;
				}
			}

			if (aces > 0 && total + 10 <= 21)
				total += 10;

			return total;
		}
		
		public static boolean isNatural(ArrayList<Card> hand) {
			return hand.size() == 2 && getScore(hand) == 21;
		}
		
		public static boolean isBust(ArrayList<Card> hand) {
			return getScore(hand) > 21;
		}
		
		
		public static String evaluate(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
			boolean playerNatural = isNatural(playerHand); // fix this line!!!!
			boolean dealerNatural = isNatural(dealerHand);

			if (playerNatural && dealerNatural)
				return PUSH;
			if (playerNatural)
				return PLAYER_NATURAL;
			if (dealerNatural)
				return LOSE;
			if (isBust(playerHand))
				return LOSE;
			if (isBust(dealerHand))
				return WIN;
			if (getScore(playerHand) > getScore(dealerHand))
				return WIN;
			if (getScore(playerHand) < getScore(dealerHand))
				return LOSE;
			return PUSH;
		}
		
		
		public static double calculatePayout(String result, int bet) {
			if (result.equals(PLAYER_NATURAL))
				return bet * 1.5;
			if (result.equals(WIN))
				return bet;
			if (result.equals(LOSE))
				return -bet;
			return 0;
			}
		}

		/**
		 * In this class you implement the game
		 * You should use CardDeck class here
		 * See the instructions for the game rules
		 */
	

