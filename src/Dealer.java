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


}