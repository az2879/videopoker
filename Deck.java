/* Alua Zhanuzakova
 * az2879
 * Deck.java simulates a 52-card deck used 
 * in a game of video poker.
 */

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private Card[] cards; // array of the 52 cards
	private int top; // the index of the top of the deck

	/**
	 * Initializes a Deck object that has 52 cards in 
	 * sorted order and whose top index is 0.
	 */
	public Deck(){
		
		cards = new Card[52];
		fillCards();
		top = 0;

	}
	
	/** 
	 * Uses the Fisher-Yates shuffling algorithm to 
	 * randomly shuffle the deck of cards. Code adapted 
	 * from GeeksforGeeks:
	 * https://www.geeksforgeeks.org/dsa/shuffle-a-deck-of-cards-3/
	*/
	public void shuffle(){
		
		Random rand = new Random();
        
        for (int i = 0; i < cards.length; i++) {

            // Pick random position from unshuffled part of array.
            int r = i + rand.nextInt(52 - i);
            
             // Swap the value at index r with current index i
             Card temp = cards[r];
             cards[r] = cards[i];
             cards[i] = temp;
             
        }

		// reset top card
		top = 0;

	}
	
	/**
	 * Returns the card at the top of the deck.
	 * 
     * @return The Card at index top
	 */
	public Card deal(){
		
		// reshuffle if at the end of the deck
		if (top == 52) {

			shuffle();
			top = 0;

		}

		Card dealtCard = cards[top];
		top++;
		return dealtCard;

	}
	
	/**
	 * Fills the deck of cards with 52 cards in the 
	 * order c1-13 => d1-13 => h1-13 => s1-13
	 */
	public void fillCards(){

		int index = 0;
		for (int i = 1; i <= 4; i++) {

			for (int j = 1; j <= 13; j++) {

				cards[index] = new Card(i, j);
				index++;

			}
		}

	}

}
