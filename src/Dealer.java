package src;
import src.Action;
import src.Card;
import src.Deck;
import src.Table;

import java.util.*;
public class Dealer {
    public String name;
    public Table table;
    public Hand hand;
    public Wallet wallet;
    Scanner scan = new Scanner(System.in);

    Action action = new Action();

    public Dealer(Table table){
        this.table = table;
        this.hand = new Hand();
        this.wallet=new Wallet(3000);
    }

    public void deal(){
        this.hand.cards.add(pick_a_card());
        for (Player player:table.players) {
            if(player.isBusted()){
                continue;
            }
            player.hands.get(0).cards.add(pick_a_card());
        }
    }


    public void deal_player(Player player){
        for (Hand hand: player.hands) {
            hand.cards.add(pick_a_card());
        }
    }

    // Method to pick a random card form the deck
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

    //Method to ask for actions
    public void ask_for_actions(int[] minmax){
        for (Player player: table.players) {
            for(int i = 0;i<player.hands.size(); i++) {
                Hand hand = new Hand();
                hand = player.hands.get(i);
                if(hand.isBusted()) continue;
                boolean flag = true;
                while(flag) {
                    System.out.println("What do you want to do with hand " + (player.hands.indexOf(hand) + 1) + " " + player.name + "?");
                    System.out.println("Enter 1 for Hit , 2 for Stand, 3 for Split, 4 for double");
                    System.out.println("Current value of your hand is "+hand.get_value_of_hand(minmax[1]));
                    int player_choice = scan.nextInt();
                    if (player_choice == 1) {
                        ////////do hit stuff
                        flag = action.hit(hand,this,player,table,minmax);

                    } else if (player_choice == 2) {
                        ////////do stand stuff
                        flag = action.stand(hand,player);

                    } else if (player_choice == 3) {
                        ////////do split stuff
                        flag = action.split(player,hand,this);
                    } else if (player_choice == 4) {
                        ////////do double stuff
                        flag = action.double_up(player,hand,this);
                    }
                }
            }
        }
    }


}