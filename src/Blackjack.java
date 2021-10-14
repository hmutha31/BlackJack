import src.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
    static Table table = new Table();
    public static void main(String[] args) {
        System.out.println("--------------Welcome to BlackJack-------------");

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of Players:");


        table.number_of_players = scan.nextInt();
        for (int i=0;i<table.number_of_players;i++){
            Player temp_player = new Player();
            System.out.println("Enter the name of Player"+" "+(i+1));
            String name = scan.next();
            temp_player.name=name;
            table.players.add(temp_player);
        }

        Dealer dealer = new Dealer(table);

        System.out.println("Every player's wallet has been loaded with 100 USD!");

        System.out.println("Enter the number of Decks you want to play with:");
        table.no_of_decks = scan.nextInt();
        for (int i =0;i<table.no_of_decks;i++){
            Deck temp = new Deck(52);
            table.decks.add(temp);
        }

        dealer.deal();      ///////give 2 cards to each player
        dealer.deal();
        table.print_all_cards(); /////////print all the cards

        ////////////place bet

        






    }





}
