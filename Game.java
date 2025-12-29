/* Alua Zhanuzakova
 * az2879
 * Game.java utilizes the Deck, Player, and Card 
 * classes to simulate a game of video poker.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Game {

	private Player p; // Human player
	private Deck cards; // Deck of 52 cards
	private boolean playingGame; // to keep playing
	private double payout; // base # of tokens won  
	private int tokens; // # of tokens player bets
	private int[] suitCounts; // tracks suits in hand
	private int[] rankCounts; // tracks ranks in hand

	/**
	 * Initializes a Game with a new player and deck. 
	 * The user specifies which cards to add to the hand.
	 */
	public Game(String[] testHand){

		p = new Player();
		cards = new Deck();
		playingGame = true;
		payout = 0.0;
		tokens = 0;

		for (int i = 0; i < testHand.length; i++) {

			if (p.getHand().size() == 5) {

				System.out.println("First five cards have been added to hand");
				break;

			}

			p.addCard(Card.toCard(testHand[i]));

		}
		
	}

	/**
	 * Initializes a Game with a new player and deck. 
	 */
	public Game(){
		
		p = new Player();
		cards = new Deck();
		playingGame = true;
		payout = 0.0;
		tokens = 0;
		
	}
	
	/**
	 * Starts a game of video poker. Game will continue until 
	 * player says they wish to stop.
	 */
	public void play(){

		// Instantiate scanner to read in player's inputs
		Scanner playerInput = new Scanner(System.in);

		//The deck is shuffled prior to the start of the game
		cards.shuffle();
		
		while (playingGame) {

			System.out.println("Welcome to Video Poker!");

			// The player is made aware how many tokens they start with
			System.out.println("Your current balance is " + 
			                    p.getBankroll() + " tokens");

			// Player can print out paytable before betting
			System.out.print("Would you like to see the paytable " +
			                 "before you bet (y/n)? ");

			if (playerInput.next().charAt(0) == 'y') {

				displayPaytable();

			}

			// The player makes a bet (1-5 tokens)
			System.out.print("How many tokens (1-5) would you like to bet? ");
			tokens = playerInput.nextInt();

			while (tokens < 1 | tokens > 5) {

				System.out.print("Must pick at least 1 and at most 5 tokens. " + 
				                 "Try again: ");

				tokens = playerInput.nextInt();

			}

			p.bets(tokens);

			// The player is dealt up to 5 starting cards 
			while (p.getHand().size() < 5) {

				p.addCard(cards.deal());

			}

			System.out.println("Your hand is: " + p.handToString());

			// The player selects which cards they want to discard
			System.out.print("Do you wish to discard any of these cards (y/n)? ");

			if (playerInput.next().charAt(0) == 'y') {

				System.out.println("Input the cards you wish to discard one-by-one");
				System.out.println("Input cards using the same format shown to you");
				System.out.println("When you wish to stop discarding, input 'stop'");

				// Allow the player to discard up to 5 cards
				while (p.getHand().size() > 0) {

					String nextCard = playerInput.next();

					if (nextCard.equals("stop")) {

						break;

					}

					try {

						p.removeCard(Card.toCard(nextCard));

					// If .toCard() is unable to convert the player's 
					// input properly, let the user know and allow 
					// them to try again with the correct format
					} catch (IllegalArgumentException e) {

						System.out.println("Invalid card format: " + 
						                    e.getMessage() + ". Try again.");
						continue;

					}

				}

			}

			// Rejected cards replaced with cards from top of deck 
			while (p.getHand().size() < 5) {

				p.addCard(cards.deal());

			}

			System.out.println("Here is the new hand: " + 
			                    p.handToString());

			// The player's hand is evaluated
			System.out.println(checkHand(p.getHand()));

			// Bankroll is adjusted accordingly
			p.winnings(tokens * payout);
			System.out.println("Your current balance is " + 
			                    p.getBankroll() + " tokens");

			// Empty the hand before next round starts
			p.clearHand();
		
			// The player is asked if they want to bet again 
			System.out.print("Would you like to keep betting (y/n)? ");
			if (playerInput.next().charAt(0) == 'n') {

				playingGame = false;

			}
		}

		System.out.println("Game over. Final balance: " + 
		                    p.getBankroll() + " tokens");

	}
	
	/**
	 * Evaluates the player's hand to determine payout
	 * 
	 * @param hand The player's current hand
     * @return A string announcing how hand was scored
	 */
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String

		suitCounts = p.getSuitCounts();
		rankCounts = p.getRankCounts();
		
		if (isRoyalFlush()) {

			payout = 250;
			return "Royal flush";

		} else if (isStraightFlush()) {

			payout = 50;
			return "Straight flush";

		} else if (isFourOfAKind()) {

			payout = 25;
			return "Four of a kind";

		} else if (isFullHouse()) {

			payout = 6;
			return "Full House";

		} else if (isFlush()) {
			
			payout = 5;
			return "Flush";

		} else if (isStraight()) {

			payout = 4;
			return "Straight";

		} else if (isThreeOfAKind()) {
			
			payout = 3;
			return "Three of a kind";

		} else if (isTwoPairs()) {

			payout = 2;
			return "Two pairs";

		} else if (isOnePair()) {

			payout = 1;
			return "One pair";

		} else {

			payout = 0;
			return "No pair";

		}
		
	}

	/**
	 * Determines if player's hand has a pair
	 * 
     * @return True if the hand has a pair
	 *         False otherwise
	 */
	public boolean isOnePair() {

		for (int i : rankCounts) {

			// A value of 2 means there are 
			// 2 cards with the same rank
			if (i == 2) {

				return true;

			}

		}

		return false;

	}

	/**
	 * Determines if player's hand has two pairs
	 * 
     * @return True if the hand has two pairs
	 *         False otherwise
	 */
	public boolean isTwoPairs() {

		int pairs = 0;
		
		for (int i : rankCounts) {

			if (i==2) {

				pairs++;

			}

		}

		return (pairs==2);

	}

	/**
	 * Determines if player's hand has 3 cards 
	 * of the same rank
	 * 
     * @return True if the hand is 3 of a kind
	 *         False otherwise
	 */
	public boolean isThreeOfAKind() {

		for (int i : rankCounts) {

			// A value of 3 means there are 
			// 3 cards of the same rank
			if (i == 3) {

				return true;

			}

		}

		return false;

	}

	/**
	 * Determines if player's hand is a straight
	 * 
     * @return True if the hand is a straight
	 *         False otherwise
	 */
	public boolean isStraight() {

		// check if the order is 10-J-Q-K-A 
		if (rankCounts[10] == 1 &&
			rankCounts[11] == 1 &&
			rankCounts[12] == 1 &&
			rankCounts[13] == 1 &&
			rankCounts[1] == 1) {

				return true;

			}

		// check for all other sequential orders
		for (int i = 0; i < rankCounts.length - 4; i++) {

			if (rankCounts[i] == 1 &&
				rankCounts[i+1] == 1 &&
				rankCounts[i+2] == 1 &&
				rankCounts[i+3] == 1 &&
				rankCounts[i+4] == 1) {

					return true;

				}

		}

		return false;

	}

	/**
	 * Determines if player's hand is a flush
	 * 
     * @return True if the hand is a flush
	 *         False otherwise
	 */
	public boolean isFlush() {

		for (int i : suitCounts) {

			// A value of 5 means there are 
			// 5 cards of the same suit
			if (i == 5) {

				return true;

			}

		}

		return false;

	}

	/**
	 * Determines if player's hand is a full house
	 * 
     * @return True if the hand is a full house
	 *         False otherwise
	 */
	public boolean isFullHouse() {

		// A full house has 3 of a kind and 1 pair
		return isThreeOfAKind() && isOnePair();

	}

	/**
	 * Determines if player's hand has 4 cards of 
	 * the same rank
	 * 
     * @return True if the hand is 4 of a kind
	 *         False otherwise
	 */
	public boolean isFourOfAKind() {

		for (int i : rankCounts) {

			// A value of 4 means there are 
			// 4 cards of the same rank
			if (i == 4) {

				return true;

			}

		}

		return false;

	}

	/**
	 * Determines if player's hand is a straight flush
	 * 
     * @return True if the hand is a straight flush
	 *         False otherwise
	 */
	public boolean isStraightFlush() {

		// Straight flush is both a straight and flush
		return isStraight() && isFlush();

	}

	/**
	 * Determines if player's hand is a royal flush
	 * 
     * @return True if the hand is royal flush
	 *         False otherwise
	 */
	public boolean isRoyalFlush() {

		// a royal flush must also be a regular flush
		if (!isFlush()) {

			return false;

		}

		// check that an ace is present
    	if (rankCounts[1] != 1) {

        	return false;

    	}

		// checks that the other 4 card ranks are 10, J, Q, K
		for (int i = 10; i < rankCounts.length; i++) {

			if (rankCounts[i] != 1) {

				return false;

			} 
		}

		return true;

	}
	
	/**
	 * Displays the video poker paytable
	 */
	public void displayPaytable() {

		System.out.println("                | 1 token  |  2 tokens  |  3 tokens  |  4 tokens  |  5 tokens");
		System.out.println("----------------+----------+------------+------------+------------+----------");
		System.out.println("One pair        |    1     |     2      |     3      |     4      |    5     ");
		System.out.println("Two pairs       |    2     |     4      |     6      |     8      |    10    ");
		System.out.println("Three of a kind |    3     |     6      |     9      |     12     |    15    ");
		System.out.println("Straight        |    4     |     8      |     12     |     16     |    20    ");
		System.out.println("Flush           |    5     |     10     |     15     |     20     |    25    ");
		System.out.println("Full house      |    6     |     12     |     18     |     24     |    30    ");
		System.out.println("Four of a kind  |    25    |     50     |     75     |     100    |    125   ");
		System.out.println("Straight flush  |    50    |     100    |     150    |     200    |    250   ");
		System.out.println("Royal flush     |    250   |     500    |     750    |     1000   |    1250  ");

	}
	
}
