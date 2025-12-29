# Homework 8 - Programming
---------------------------------

## Problem 1 - 47 points

**Video Poker:**

In this assignment, you will implement a simulation of a popular casino game usually called video poker. The card deck contains 52 cards, 13 of each suit. At the beginning of the game, the deck is shuffled. You need to devise a fair method for shuffling. (It does not have to be efficient.) The player pays a token for each game. Then the top five cards of the deck are presented to the player. The player can reject none, some, or all of the cards. The rejected cards are replaced from the top of the deck. Now the hand is scored. Your program should pronounce it to be one of the following:
* No pair—The lowest hand, containing five separate cards that do not match up to create any of the hands below.
* One pair—Two cards of the same value, for example two queens. Payout: 1
* Two pairs—Two pairs, for example two queens and two 5’s. Payout: 2
* Three of a kind—Three cards of the same value, for example three queens. Payout: 3
* Straight—Five cards with consecutive values, not necessarily of the same suit, such as 4, 5, 6, 7, and 8. The ace can either precede a 2 or follow a king. Payout: 4
* Flush—Five cards, not necessarily in order, of the same suit. Payout: 5
* Full House—Three of a kind and a pair, for example three queens and two 5’s.
Payout: 6
* Four of a Kind—Four cards of the same value, such as four queens. Payout: 25
* Straight Flush—A straight and a flush: Five cards with consecutive values of the same suit. Payout: 50
* Royal Flush—The best possible hand in poker. A 10, jack, queen, king, and ace, all of the same suit. Payout: 250


You must follow the instructions below to receive credit for this assignment. 
 
Instead of your player betting a single token each time, allow the player to bet between 1-5 tokens. If a player bets *n* tokens they win *n* times as much as indicated in the above prompt.

Your program must use the included templates. These templates are for the classes: Card, Deck, Game, Player, and a test class, TestPoker. You will need to fill-in the existing methods and may want to add more methods and/or instance variables to any or all of these classes except for the test class TestPoker. TestPoker must remain exactly as it is here. 

This project requires you to have two versions of your game each constructed using a different version of the Game constructor. One will require an explicit parameter that you will get as a command-line argument, this is to help you (and us) test your code. That is, it will allow the user to specify the hand that the player gets which will help in testing if your game correctly identifies the various hands. Carefully read the comments in the template files to help you understand how this will work.

## Problem 2 - 3 points

Templates for the five source files are located in the Homework 8 workspace on Codio. In addition to the source files  include a text file named readMe.txt with an explanation of how your program works. That is, write in plain English, instructions for using your software, explanations for how and why you chose to design your code the way you did. The readMe.txt file is also an opportunity for you to get partial credit when certain requirements of the assignment are not met. 
