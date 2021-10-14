import src.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
    static Table table = new Table();
    public static void main(String[] args) {
        System.out.println("--------------Welcome to BlackJack-------------");

        Scanner scan = new Scanner(System.in);

        Refree refree = new Refree(table);

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
        for (Player player: table.players) {
            System.out.println("Please enter your bet "+player.name);
            int temp_bet = scan.nextInt();
            player.wallet= player.wallet - temp_bet;
            player.hands.get(0).bet = temp_bet;
        }

        for (Player player: table.players) {
            for (Hand hand:player.hands) {
                System.out.println("What do you want to do with hand "+ player.hands.indexOf(hand) +" "+ player.name + "? Hit, Split, Stand....");
                System.out.println("Enter 1 for Hit , 2 for Stand, 3 for Split,4 for double .....");
                int player_choice = scan.nextInt();
                if (player_choice == 1) {
                    ////////do hit stuff
                    hand.cards.add(dealer.pick_a_card());
                    hand.print_cards();

                } else if (player_choice == 2) {
                    ////////do stand stuff
                }
                else if (player_choice==3){
                    player.split();
                    dealer.deal_player(player);
                }
                else if (player_choice==4){
                    hand.bet=hand.bet*2;
                    hand.cards.add(dealer.pick_a_card());
                    //////////now the player stands
                }
            }
        }

    }





}
