# CS 611 - Blackjack and Trinata
Our version of the popular card games Blackjack and Trinata for CS 611 Assignment. 

## Table of Contents
* [General Info](#general-info)
* [Technologies](#technology)
* [Class Descriptions](#class-descriptions)
* [Setup](#setup)
* [Team Members](#team-members)

## General Info
Implementation of Blackjack and its popular custom variation Trinata using an efficient object oriented design. 
This project was developed towards the CS 611 Assignment 3.

## Technologies
Project was developed in:
* Java 8
* Github

## Class Descriptions
The following gives a brief description of all the classes used in our code base. 

### Game
  * An interface common to Blackjack and Trianta classes. Provides definitions for play() and reset_round()

### Blackjack
  * Main class to play the game of Blackjack. Implements methods play() and reset_round() as required by the game
  * static variable minmax. minmax[0] returns the minimum value(17) the dealer has to reach. minmax[1] returns the max value(21) before getting busted!

### Trianta
  * Main class to play the game of Trianta. Implements methods play() and reset_round() as required by the game
  * static variable minmax. minmax[0] returns the minimum value(27) the dealer has to reach. minmax[1] returns the max value(31) before getting busted!

### Player
  * Player class denoting a player of the game.
  * Each player has a list of hands, and a wallet. 
  * wallet keeps track of the money a player has.

### Dealer
  * Dealer class denoting the dealer 
  * Each dealer has a hand, and the functions to deal cards to himself and each player as required.

### Table
  * Table class denoting a table
  * A table has a list of players currently playing on the table, and a stack of decks.

### Printer
  * A helper class to implement different print functions required by our games. 

### Refree
  * A helper class to run functions such as checking if a hand is busted or not.
  * Main function is to check for winner after each round ends.

### Wallet
  * Class to denote the wallet of each player and dealer(if the game is Trianta).

### Card
  * Class used to denote a card object. Each card has a rank and shape.

### Hand
  * Hand class is used to denote a certain set of cards belonging to each playing player and dealer. 
  * It holds a property of busted which denotes if the hand's value exceeds the maximum value allowed by the game.

### Deck
  * Deck class holds a set of 52 standard playing cards.

## Setup
To run this project:
* Download the necessary files from the /src folder onto your local machine
* Move down into the directory where the files are located
* To compile the code- javac Blackjack.java
* Once the code compiles, to run the game - java Blackjack
* Follow onscreen instructions to play the game

## Team Members
The developers who worked on this project are:
* Shekhar Sharma (U43306392 , shekhars@bu.edu)
* Shweta Baindur (U73181758 , shwetab@bu.edu)
* Harsh Mutha (U84186562 , hmutha31@bu.edu)

