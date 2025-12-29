Alua Zhanuzakova
az2879 
Description of My Video Poker Simulator 

    In the play() method in Game.java, as long as the boolean playingGame (which is initialized as 
true in the Game() constructor) is true, the game will keep starting a new round after the preceding 
round ends. The only way to break out of the while loop is for the user to indicate that they want to 
stop playing, which is offered to them at the end of each round. I wrote a method that allows the user 
to print out the paytable with all of the combinations and payouts before deciding how many tokens to 
bet. I use a while loop to keep asking the player how many tokens they would like to bet until their 
input is between 1 and 5 (to make sure their input is valid).

    I defined two different hand accessor methods in the Player class. getHand() returns the ArrayList 
itself, which is useful for checking the number of cards in the hand at any moment and evaluating the 
payout. For instance, I use (getHand().size() < 5) as a condition inside my while loop so that the program 
stops adding cards to the player's hand once it is full. handToString(), on the other *hand*, returns a 
String representation of the player's hand, which I utilize to display the contents of the hand to the 
player. For instance, I use handToString() to show the player their hand after randomly filling it with 
5 cards.

    One of the static methods I added to the Card class was Card.toCard(String s), which takes in the 
string representation of a card (s12, h1, d5, etc) and creates the corresponding Card object. For this 
method, I use a try-catch statement so that the catch statement can catch any NumberFormatExceptions 
that may be thrown if the user inputs a non-integer rank after the suit. I also make sure to throw an 
IllegalArgumentException if the value at the first index of the input is not a c, d, h, or s. Lastly, I 
also throw an IllegalArgumentException if the integer the user inputs is not between 1 and 13.

    Catching those exceptions is important because the next part of the play() methods asks the player 
to discard any unwanted cards, so I have to make sure that their input is a legal argument to pass into 
the parameter of Card.toCard(). Looking back to the play() method in Game.java, my discard loop also 
contains a try-catch statement, where the catch block will catch any IllegalArgumentExceptions thrown
in the Card.toCard() method and print the corresponding error message to the user.

    My implementation of the checkHand() method relies on two methods I wrote in the Player class: 
getSuitCounts() and getRankCounts(). getSuitCounts() creates an initially empty array of size 5. Then, 
an enhanced for loop iterates through the Card objects in the player's hand and increments the values 
of the array by 1 according to the suits (1-4) of the cards. For instance, if a player's hand looked 
like this: [s1, d3, h4, h7, s11], the output of getSuitCounts() would be [0 0 1 2 2], because there are 
0 clubs (c = index 1), 1 diamond (d = index 2), 2 hearts (h = index 3), and 2 spades(s = index 4).
getRankCounts() follows the same logic except for ranks instead of suits. For that same sample deck, 
the corresponding output of getRankCounts() would be [0 1 0 1 1 0 0 1 0 0 0 1 0 0].

    In my checkHand() method, I first save the outputs of both of these methods into the variables 
suitCounts and rankCounts. Next, I go down the line of possible evaluations starting from royal flush 
down to no pair to determine if the hand matches any of those categories. I created separate methods to 
test for each possible evaluation (isRoyalFlush(), isStraight(), e.t.c.).

    All evaluator methods rely on suitCounts and rankCounts to determine the classification of the 
hand. For instance, isOnePair() checks to see if any of the values in rankCounts is 2, since that would 
indicate that two cards have the same rank in that hand. Although the isOnePair() method is not 
implemented in such a way that it can discriminate between being one pair, two pairs, a full house, e.t.c., 
since checkHand() starts the if/else block with isRoyalFlush() and works its way down to isOnePair(), 
the other options would cause the method to return before it reaches isOnePair(). Since full houses and 
straight flushes are combinations of other classifications, I can use other evaluator methods within 
another evaluator method for simplicity (return isOnePair() && isThreeOfAKind() to check full house, e.t.c.)

    I overrode Java's standard x.comapreTo(y) implementation so that it returns a positive value if 
card x's rank is greater than card y's rank and a negative value if card y's rank is greater than 
card x's rank. In the case that the two cards have the same rank, they are compared by subtracting 
card y's (integer) suit from card x's suit. I utilized compareTo() when overriding the .equals()
method by declaring two cards as equal to each other when compareTo() evaluates to 0 (since that 
would mean both their ranks and suits are the same). However, my implementation of the checkHand()
method didn't require me to sort the cards in the player's hand, so compareTo() was only important 
for determining if a card was equal to another. 

