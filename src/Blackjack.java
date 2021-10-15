package src;

import src.*;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;


public class Blackjack implements Game {
    static Table table = new Table();

    public static void resetRound(Table table, Dealer dealer) {
        /*
        1. reset hands for each player
        2. reset dealers hand
        3. reset deck
         */
        for(Player player : table.players) {
            player.hands= new ArrayList<>();
            player.hands.add(new Hand());
        }
        //reset dealer hand
        dealer.hand = new Hand();

        //reset used cards
        table.used_cards = new ArrayList<>();

    }


    @Override
    public void play() {
        System.out.println("--------------Welcome to BlackJack-------------");
        table.get_players();
        table.get_decks();

        do {
            table.place_bets();
            Dealer dealer = new Dealer(table);
            Refree refree = new Refree(table, dealer);
            table.set_refree(refree);

            //reset hands and deck

            dealer.deal();                  //give 1 card to each player
            dealer.deal();                  //give 1 card to each player
            Printer.print_cards_with_hidden(dealer.hand.cards);  //print dealers cards with 1 hidden card
            //table.print_all_cards();        //print all the cards of players
            for(Player player : table.players) {
                Printer.print_player_cards(player);
            }
            dealer.ask_for_actions();       //ask for actions
            if (!refree.all_busted()) {
                refree.check_winners();
            }
            table.print_player_balances();
            System.out.println("This Round ends");
            //ask for cashing out
            table.players = table.cash_out();
            resetRound(table,dealer);
        }while (!table.players.isEmpty());

        System.out.println("Everybody cashed out!");
    }
}

