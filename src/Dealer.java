package src;
import src.Action;
import src.Card;
import src.Deck;
import src.Table;

import java.util.*;
public class Dealer {
    public String name;
    //public ArrayList<Card> cards;
    public Table table;
    public Hand hand;
    Scanner scan = new Scanner(System.in);

    public Dealer(Table table){
        this.table = table;
        this.hand = new Hand();
        //this.hand.cards = new ArrayList<Card>();

    }

    public void deal(){
        this.hand.cards.add(pick_a_card());
        for (Player player:table.players) {
            player.hands.get(0).cards.add(pick_a_card());
        }

    }

    public void print_cards_with_hidden(){
        System.out.println("The dealer's cards are");
        int flag=0;
        for (Card card:this.hand.cards) {
            if (flag==0){
                System.out.println("HIDDEN");
                flag=1;
                continue;
            }
            System.out.println(card.face_value);
        }
    }


    public void print_cards(){
        System.out.println("The dealer's cards are");
        for (Card card:this.hand.cards) {
            System.out.println(card.face_value);
        }
    }


    public void deal_player(Player player){
        for (Hand hand: player.hands) {
            hand.cards.add(pick_a_card());
        }
    }

    public Card pick_a_card() {
        while (true) {
            int random_card = (int) (Math.random() * (52));
            int random_deck = (int) (Math.random() * (table.no_of_decks));

            int[] id = {random_deck,random_card};
            if (!table.used_cards.contains(id)){
                table.used_cards.add(id);
                return table.decks.get(random_deck).cards.get(random_card);
            }
        }
    }

    public void ask_for_actions(){
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
                        hand.cards.add(this.pick_a_card());
                        hand.print_cards();
                        hand.display_Bet();
                        int handState = table.refree.checkHand(hand);
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
                        if (player.wallet.value< hand.bet){
                            System.out.println("You don't have enough balance to split!");
                            continue;
                        }
                        if(hand.cards.size()!=2 || (!hand.cards.get(0).face_value.equals(hand.cards.get(1).face_value))) {
                            System.out.println("You can't split this hand. You need minimum 2 cards");
                            continue;
                        }
                        player.split();
                        this.deal_player(player);   //add one card to both hands
                        player.print_cards();
                        hand.display_Bet();
                    } else if (player_choice == 4) { //for double up
                        if (player.wallet.value< hand.bet){
                            System.out.println("You don't have enough balance to double!");
                            continue;
                        }
                        player.wallet.subtract_value(hand.bet);
                        hand.bet = hand.bet * 2;
                        hand.cards.add(this.pick_a_card());
                        flag=false;
                        hand.print_cards();
                        hand.display_Bet();
                    }
                }
            }
        }
    }


}