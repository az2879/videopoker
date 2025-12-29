/* Alua Zhanuzakova
 * az2879
 * Card.java implements a Card object to
 * simulate any card found in a 52-card deck.
 */

public class Card implements Comparable<Card>{

	private int suit; // integers 1-4 encode the suit
	private int rank; // integers 1-13 encode the rank


	/**
	 * Creates a Card object with suit s and rank r
	 */
	public Card(int s, int r){
		
		suit = s;
		rank = r;

	}

	/**
	 * Compares two cards first on the basis of their 
	 * ranks, and then based on their suits if the ranks 
	 * are the same.
	 * 
	 * @param c The card object to be compared to the 
	 *          implicit parameter (IP)
	 * @return A positive integer if the IP is larger 
	 *         A negative integer if the IP is smaller
	 *         0 if the IP is equal to card c
	 */
	public int compareTo(Card c){
		
		if ((this.rank - c.rank) != 0) {

			return this.rank - c.rank;

		} else {

			return this.suit - c.suit;

		}

	}

	/**
	 * Overrides Java's equals() method by determining 
	 * whether two Card objects are equal based on the 
	 * output of the compareTo() method. 
	 * 
	 * @param obj Will be upcasted to a Card object and 
	 *            compared to the implicit parameter
	 * @return False if the cards are not equal 
	 *         True if the cards are equal
	 */
	public boolean equals(Object obj) {

		Card other = (Card) obj;

		return (compareTo(other) == 0);

	}

	/**
	 * Overrides Java's toString() method by encoding
	 * suit values 1-4 to the suits c, d, h, and s, 
	 * respectively to represent Card objects as strings.
	 * 
	 * @return A string that represents the Card object's 
	 *         suit and rank.
	 */
	public String toString() {
		// use this method to easily print a Card object
		String cardSuit;

		if (suit==1) {

			cardSuit = "c";

		} else if (suit==2) {

			cardSuit = "d";

		} else if (suit==3) {

			cardSuit = "h";

		} else {

			cardSuit = "s";

		}

		return cardSuit + rank;
	}

	/**
	 * Returns the int representation of a card's suit.
	 * 
	 * @return The card's suit
	 */
	public int getSuit() {

		return suit;

	}

	/**
	 * Returns the int representation of a card's rank.
	 * 
	 * @return The card's rank
	 */
	public int getRank() {

		return rank;

	}

	/**
	 * Creates a new Card object based on its string 
	 * representation. 	 
	 * 
	 * @param s The string representation of the card
	 * @return A Card object based on the suit and rank 
	 *         specified in string s. 
	 * @throws IllegalArgumentException if the suit or 
	 *         rank input is not valid.
	 */
	public static Card toCard(String s) {

		int intCardSuit;
		int intCardRank;
		String cardSuit;
		String cardRank;

		try {

			cardSuit = s.substring(0, 1);
			cardRank = s.substring(1);
		
			if (cardSuit.equals("c")) {

				intCardSuit = 1;

			} else if (cardSuit.equals("d")) {

				intCardSuit = 2;

			} else if (cardSuit.equals("h")) {

				intCardSuit = 3;

			} else if (cardSuit.equals("s")) {

				intCardSuit = 4;

			} else {

				throw new IllegalArgumentException("Suit must be c, d, h, or s");

			}

			intCardRank = Integer.parseInt(cardRank);

		} catch (NumberFormatException e) {

			throw new IllegalArgumentException("Rank must be an integer between 1 and 13");

		}

		if (intCardRank < 1 || intCardRank > 13) {

			throw new IllegalArgumentException("Rank must be an integer between 1 and 13");

		}

		return new Card(intCardSuit, intCardRank);

	}

}
