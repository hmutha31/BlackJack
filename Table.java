
import java.util.ArrayList;
import java.util.List;
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

    public void set_refree(Refree refree){
        this.refree=refree;
    }

    public void get_players() {
        System.out.println("Enter the number of Players playing:");
        while(true) {
        	this.number_of_players = scan.nextInt();
        	if(this.number_of_players<1 || this.number_of_players > 9) 
        		System.out.println("Please enter the number of players between 1 to 9");
        	else break;
        }
        
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
        while(true) {
        	this.no_of_decks = scan.nextInt();
        	if(this.no_of_decks<1 || this.no_of_decks > 8)
        		System.out.println("Please enter the number of decks between 1 to 8");
        	else break;
        		
        }
        
        for (int i =0;i<this.no_of_decks;i++){
            Deck temp = new Deck(52);
            this.decks.add(temp);
        }
    }

    public void place_bets(){
        for (Player player: this.players) {
            if(player.isBusted()){
                continue;
            }
            boolean flag = true;
            int temp_bet = 0;
            while (flag) {
                System.out.println("Please enter your bet " + player.name+" :");
                while(true) {
                	 temp_bet = scan.nextInt();
                	 if(temp_bet < 1)
                		 System.out.println("The bet amount should not be less than $1");
                 	else break;
                		 
                }
                	
               
                player.wallet.subtract_value(temp_bet);
                if (player.wallet.value < 0) {
                    System.out.println("You don't have enough balance!");
                    player.wallet.add_value(temp_bet);
                    continue;
                }
                flag=false;
            }
            player.hands.get(0).bet = temp_bet;
        }
    }

    public ArrayList<Player> cash_out(){
        ArrayList<Player> playersInGame = new ArrayList<>();
        for(Player player : this.players) {
            playersInGame.add(player);
        }


        Scanner scanner = new Scanner(System.in);
        int intialPlayersSize = this.players.size();
        for(int i=0;i<intialPlayersSize;i++) {
            System.out.println("Do you want to cash out "+this.players.get(i).name+" ?\n Enter 1 for Yes and 2 for No");
            int choice;
            
            while(true) {
            	choice = scanner.nextInt();
            	if(choice<1 || choice >2) 
            		System.out.println("Please enter either 1 or 2");
            	else
            		break;
            }
            
            if(choice==1) {
                playersInGame.remove(this.players.get(i));
            }
        }
        return playersInGame;
    }
}
