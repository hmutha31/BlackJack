import src.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;


public class Blackjack {
    static Table table = new Table();
    public static void main(String[] args) {
        System.out.println("--------------Welcome to BlackJack-------------");
        table.get_players();
        table.get_decks();
        table.place_bets();
        Dealer dealer = new Dealer(table);
        Refree refree = new Refree(table,dealer);
        table.set_refree(refree);

        dealer.deal();                  //give 1 card to each player
        dealer.deal();                  //give 1 card to each player
        dealer.print_cards_with_hidden();  //print dealers cards with 1 hidden card
        table.print_all_cards();        //print all the cards of players
        dealer.ask_for_actions();       //ask for actions

        refree.check_winners();

        table.print_player_balances();



    }





}

