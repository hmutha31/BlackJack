package src;
import src.Action;
import src.Card;
import src.Deck;
import src.Table;

import java.util.*;
public class Dealer {
    public String name;
    public ArrayList<Card> cards;
    public ArrayList<Deck> decks;
    public Action action;
    public Table table;

    public Dealer(Table table){
        this.table = table;
    }

    public void deal(){
        for (Player player:table.players) {
            player.hands.get(0).cards.add(pick_a_card());

        }

    }

    public Card pick_a_card() {
        while (true) {
            int random_card = (int) (Math.random() * ((52) + 1));
            int random_deck = (int) (Math.random() * ((table.no_of_decks) + 1));

            int[] id = {random_deck,random_card};
            if (!table.used_cards.contains(id)){
                table.used_cards.add(id);
                return table.decks.get(random_deck).cards.get(random_card);
            }
        }
    }


}