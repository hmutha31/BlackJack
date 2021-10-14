package src;

import java.util.ArrayList;

public class Table {
    public ArrayList<Player> players;
    public ArrayList<Deck> decks;
    public int number_of_players;
    public int no_of_decks;
    public ArrayList<int[]> used_cards;

    public Table(){
        this.players=new ArrayList<Player>();
        this.decks= new ArrayList<Deck>();
        this.used_cards= new ArrayList<int[]>();
    }

    public void add_player(Player player){
        this.players.add(player);
        this.number_of_players++;
    }

    public void print_all_cards(){
        for (Player player: this.players) {
            System.out.println("The cards of "+" "+player.name + " are:");
            for (Hand hand: player.hands) {
                for (Card card: hand.cards){
                    System.out.println(card.face_value);
                }

            }
        }
    }

}
