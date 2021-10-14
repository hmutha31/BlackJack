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

        System.out.println("Every player's wallet has been loaded with 1000 USD!");

        System.out.println("Enter the number of Decks you want to play with:");
        table.no_of_decks = scan.nextInt();
        for (int i =0;i<table.no_of_decks;i++){
            Deck temp = new Deck(52);
            table.decks.add(temp);
        }

        ////////////place bet
        for (Player player: table.players) {
            boolean flag = true;
            int temp_bet = 0;
            while (flag) {
                System.out.println("Please enter your bet " + player.name);
                temp_bet = scan.nextInt();
                player.wallet = player.wallet - temp_bet;
                if (player.wallet < 0) {
                    System.out.println("You dont have enough balance");
                    player.wallet = player.wallet + temp_bet;
                    continue;
                }
                flag=false;
            }
            player.hands.get(0).bet = temp_bet;
        }

        dealer.deal();      ///////give 2 cards to each player
        dealer.deal();
        dealer.print_cards();
        table.print_all_cards(); /////////print all the cards


        boolean value = true;




            for (Player player: table.players) {
                for(int i = 0;i<player.hands.size(); i++) {
                    Hand hand = new Hand();
                    hand = player.hands.get(i);
                    if(hand.isBusted()) continue;
                    boolean flag = true;
                    while(flag) {
                        System.out.println("What do you want to do with hand " + (player.hands.indexOf(hand) + 1) + " " + player.name + "? Hit, Split, Stand....");
                        System.out.println("Enter 1 for Hit , 2 for Stand, 3 for Split,4 for double,.....");
                        System.out.println("Current value of your hand is "+hand.get_value_of_hand());
                        int player_choice = scan.nextInt();
                        if (player_choice == 1) {
                            ////////do hit stuff
                            hand.cards.add(dealer.pick_a_card());
                            hand.print_cards();
                            hand.display_Bet();
                            int handState = refree.checkHand(hand);
                            switch(handState) {
                                case 1 :
                                    //player wins
                                    System.out.println("You hit Blackjack");
                                    //player.wallet+=(hand.bet*2);
                                    break;
                                case -1 :
                                    hand.setBusted(true);
                                    flag = false;
                                    System.out.println("Busted!!");
                                    break;
                                case 0 :
                                    //player can still play
                                    break;
                            }

                        } else if (player_choice == 2) {
                            ////////do stand stuff

                            hand.print_cards();
                            hand.display_Bet();
                            flag=false;

                        } else if (player_choice == 3) { //for split
                            if (player.wallet< hand.bet){
                                System.out.println("You dont have enough balance to split!");
                                continue;
                            }
                            if(hand.cards.size()!=2 || (!hand.cards.get(0).face_value.equals(hand.cards.get(1).face_value))) {
                                System.out.println("You cant split this hand. You need minimum 2 cards");
                                continue;
                            }


                            player.split();
                            dealer.deal_player(player);
                            player.print_cards();
                            hand.display_Bet();
                        } else if (player_choice == 4) { //for double up
                            if (player.wallet< hand.bet){
                                System.out.println("You dont have enough balance to double!");
                                continue;
                            }
                            player.wallet=player.wallet-hand.bet;
                            hand.bet = hand.bet * 2;

                            hand.cards.add(dealer.pick_a_card());
                            flag=false;
                            hand.print_cards();
                            hand.display_Bet();

                        }
                    }
                }
            }

            int dealerState = refree.checkDealerHand(dealer.hand,dealer);
            if(dealerState==1) {
                //dealer is above 17 & less than 21
                int dealerSum = dealer.hand.get_value_of_hand();
                for(Player player : table.players) {
                    for(Hand hand : player.hands) {
                        if(!hand.isBusted()) {
                            if(hand.get_value_of_hand() > dealerSum) {
                                System.out.println(player.name+" Wins!!");
                                player.wallet+=(hand.bet*2);
                            }else if(hand.get_value_of_hand()==dealerSum) {
                                player.wallet+=hand.bet;
                                System.out.println(player.name+" ties with dealer");
                            }else {
                                System.out.println(player.name+ " loses");
                            }
                        }
                    }
                }
            }
            else {
                //dealer is busted
                System.out.println("Dealer Busted!!");
                dealer.hand.setBusted(true);
                //everyone who stood wins
                for(Player player: table.players) {
                    for(Hand hand : player.hands) {
                        if(!hand.isBusted()) {
                            player.wallet+= (hand.bet*2);
                        }
                    }
                }
            }

            for(Player player: table.players) {
                System.out.println(player.name+" balance : "+player.wallet);
            }



    }





}

