package src;

import java.util.ArrayList;

public class Table {
    public int num_of_players;
    public ArrayList<Player> players;

    public Table(){
        this.num_of_players=0;
        this.players=new ArrayList<Player>();
    }

    public void add_player(Player player){
        this.players.add(player);
        this.num_of_players++;
    }

}
