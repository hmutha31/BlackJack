package src;

import java.util.ArrayList;
import java.util.Scanner;

public class Table {
    public ArrayList<Player> players;
    public ArrayList<Deck> decks;
    public int number_of_players;
    public int no_of_decks;
    public ArrayList<int[]> used_cards;
    public Refree refree;

    Scanner scan = new Scanner(System.in);


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

    public void set_refree(Refree refree){
        this.refree=refree;
    }

    public void get_players() {
        System.out.println("Enter the number of Players:");
        this.number_of_players = scan.nextInt();
        for (int i = 0; i < this.number_of_players; i++) {
            Player temp_player = new Player();
            System.out.println("Enter the name of Player" + " " + (i + 1));
            String name = scan.next();
            temp_player.name = name;
            this.players.add(temp_player);
        }
        System.out.println("Every player's wallet has been loaded with 1000 USD!");
    }

    public void get_decks(){
        System.out.println("Enter the number of Decks you want to play with:");
        this.no_of_decks = scan.nextInt();
        for (int i =0;i<this.no_of_decks;i++){
            Deck temp = new Deck(52);
            this.decks.add(temp);
        }
    }

    public void place_bets(){
        for (Player player: this.players) {
            boolean flag = true;
            int temp_bet = 0;
            while (flag) {
                System.out.println("Please enter your bet " + player.name);
                temp_bet = scan.nextInt();
                player.wallet.subtract_value(temp_bet);
                if (player.wallet.value < 0) {
                    System.out.println("You don't have enough balance");
                    player.wallet.add_value(temp_bet);
                    continue;
                }
                flag=false;
            }
            player.hands.get(0).bet = temp_bet;
        }
    }

    public void print_player_balances(){
        for(Player player: this.players) {
            System.out.println(player.name+" balance is : "+player.wallet.value);
        }
    }

}
