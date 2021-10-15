package src;

import src.*;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;


public class Blackjack implements Game {
    static Table table = new Table();
    static int[] minmax = {17,21};

    @Override
    public void resetRound(Table table, Dealer dealer) {
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
    public void ask_for_actions(int[] minmax,Dealer dealer) {
        Action action = new Action();
        Scanner scanner = new Scanner(System.in);
        for (Player player: table.players) {
            for(int i = 0;i<player.hands.size(); i++) {
                Hand hand = new Hand();
                hand = player.hands.get(i);
                if(hand.isBusted()) continue;
                boolean flag = true;
                while(flag) {
                    System.out.println("What do you want to do with hand " + (player.hands.indexOf(hand) + 1) + " " + player.name + "? Hit, Split, Stand....");
                    System.out.println("Enter 1 for Hit , 2 for Stand, 3 for Split,4 for double,.....");
                    System.out.println("Current value of your hand is "+hand.get_value_of_hand(minmax[1]));
                    int player_choice = scanner.nextInt();
                    if (player_choice == 1) {
                        ////////do hit stuff
                        flag = action.hit(hand,dealer,player,table,minmax);

                    } else if (player_choice == 2) {
                        ////////do stand stuff
                        flag = action.stand(hand,player);

                    } else if (player_choice == 3) { //for split
                        flag = action.split(player,hand,dealer);
                    } else if (player_choice == 4) { //for double up
                        flag = action.double_up(player,hand,dealer);
                    }
                }
            }
        }
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
            Printer.print_cards_with_hidden(dealer.hand.cards,dealer);  //print dealers cards with 1 hidden card
            //table.print_all_cards();        //print all the cards of players
            for(Player player : table.players) {
                Printer.print_player_cards(player);
            }
            ask_for_actions(minmax,dealer);       //ask for actions
            if (!refree.all_busted()) {
                refree.check_winners(minmax);
            }
            Printer.print_balances(table.players);
            System.out.println("This Round ends");
            //ask for cashing out
            table.players = table.cash_out();
            resetRound(table,dealer);
        }while (!table.players.isEmpty());

        System.out.println("Everybody cashed out!");
    }
}

