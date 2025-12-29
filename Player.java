/* Alua Zhanuzakova
 * az2879
 * Player.java simulates a human player of video poker.
 */

import java.util.ArrayList;

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll; // the player's balance
    private double bet; // number of tokens player bets

    /**
	 * Instantiates a Player with an empty hand and a bankroll 
     * with 10 tokens.
	 */
	public Player(){		
	    
        hand = new ArrayList<>();
        bankroll = 10.0;
        bet = -1;

	}

    /**
	 * Adds a card to the player's hand
	 * 
     * @param c The card being added to the hand
	 */
	public void addCard(Card c){
	    // add the card c to the player's hand
        hand.add(c);

	}

    /**
	 * Removes a card to the player's hand
	 * 
	 * @param c The card to be removed from the hand
	 */
	public void removeCard(Card c){
	    // remove the card c from the player's hand
        hand.remove(c);

    }

    /**
	 * Subtracts the player's bet from their bankroll
	 * 
	 * @param amt The number of tokens the player bets
	 */
    public void bets(double amt){

        bet = amt;
        bankroll -= amt;

    }

    /**
	 * Adds the player's winnings to their bankroll
	 * 
	 * @param odds The number of tokens won by the player
	 */
    public void winnings(double odds){
        
        bankroll += odds;

    }

    /**
	 * Returns an array of integers where indices 1-4 
     * represent the 4 different possible suits. The 
     * value at each index is incremented by 1 for every 
     * card in the hand that has the corresponding suit.
	 * 
	 * @return An array representing how many cards of
               each suit are present in the hand.
	 */
    public int[] getSuitCounts() {

        // will leave index 0 empty
        int[] suitCounts = new int[5];

        // for each suit in the hand, increment the 
        // corresponding index in the counts array by 1
        for (Card c : hand) {

            suitCounts[c.getSuit()]++;

        }

        return suitCounts;

    }

    /**
	 * Returns an array of integers where indices 1-13 
     * represent the 13 different possible ranks. The 
     * value at each index is incremented by 1 for every 
     * card in the hand that has the corresponding rank.
	 * 
	 * @return An array representing how many cards of
               each rank are present in the hand.
	 */
    public int[] getRankCounts() {

        // will leave index 0 empty
        int[] rankCounts = new int[14];

        // for each rank in the hand, increment the 
        // corresponding index in the counts array by 1 
        for (Card c : hand) {

            rankCounts[c.getRank()]++;

        }

        return rankCounts;

    }

    /**
	 * Returns the player's current bankroll
	 * 
     * @return Number of tokens in the bankroll
	 */
    public double getBankroll(){

        return bankroll;

    }

    /**
	 * Returns the player's current hand
	 * 
     * @return The player's hand
	 */
    public ArrayList<Card> getHand() {

        return hand;
        
    }

    /**
	 * Returns a string representation of the hand
	 * 
     * @return A string showing the player's hand
	 */
    public String handToString() {

        return hand.toString();

    }

    /**
	 * Empties all cards in the player's hand
	 */
    public void clearHand() {

        hand.clear();

    }

}


