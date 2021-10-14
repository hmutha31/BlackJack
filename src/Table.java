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

}
