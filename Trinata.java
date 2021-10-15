
import java.util.ArrayList;
import java.util.Scanner;

public class Trinata implements Game{

    static Table table = new Table();
    static int[] minmax = {27,31};
    Action action = new Action();

    @Override
    public void play() {
        System.out.println("--------------Welcome to Trianta Ena!-------------");
        table.get_players();
        table.get_decks();
        Scanner scan = new Scanner(System.in);
        do {
            Dealer dealer = new Dealer(table);
            Refree refree = new Refree(table, dealer);
            table.set_refree(refree);

            dealer.deal();
            System.out.println("1 Card given to every player and the dealer");
            Printer.print_cards(dealer.hand.cards, dealer);
            for(Player player : table.players) {
                Printer.print_player_cards(player);
            }
            int choice;
            for(Player player : table.players){
                System.out.println("Player "+player.name+" Do you want to fold? Please enter 1 for yes and 2 for no ");
              
                while(true) {
                	choice = scan.nextInt();
                	if(choice<1 || choice >2) 
                		System.out.println("Please enter either 1 or 2");
                	else
                		break;
                }
                
                if(choice == 1){
                    player.setBusted(true);
                }

            }
            table.place_bets();

            dealer.deal();                  //give 1 card to each player
            dealer.deal();                  //give 1 card to each player
            Printer.print_cards(dealer.hand.cards, dealer);

            //check natural trianta for dealer
            int sum = dealer.hand.get_value_of_hand(minmax[1]);
            if(sum==minmax[1]) {
                //dealer wins
                System.out.println("Dealer got natural trianta!");
                for(Player player : table.players) {
                    dealer.wallet.add_value(player.hands.get(0).bet);
                }
                break;
            }

            for(Player player : table.players) {
                if(player.isDidHitMax()){
                    continue;
                }
                if(action.checkNatural(player,minmax[1])) {
                    System.out.println("You've hit natural trianta!");
                    player.wallet.add_value(player.hands.get(0).bet*2);
                    dealer.wallet.subtract_value(player.hands.get(0).bet*2);
                    player.setDidHitMax(true);
                }
                Printer.print_cards_with_hidden(player.hands.get(0).cards,player);
            }
            ask_for_actions(minmax,dealer);       //ask for actions
            if (!refree.all_busted()) {
                refree.check_winners(minmax);
            }
            Printer.print_balances(table.players);
            System.out.println("---------This Round ends---------");
            //ask for cashing out
            table.players = table.cash_out();
            resetRound(table,dealer);
        }while (!table.players.isEmpty());

        System.out.println("---------Everybody cashed out!---------");
    }

    @Override
    public void resetRound(Table table, Dealer dealer) {
        /*
        1. reset hands for each player
        2. reset dealers hand
        3. reset deck
         */
        for(Player player : table.players) {
            player.hands= new ArrayList<>();
            player.hands.add(new Hand());
        }
        //reset dealer hand
        dealer.hand = new Hand();

        //reset used cards
        table.used_cards = new ArrayList<>();

    }

    @Override
    public void ask_for_actions(int[] minmax, Dealer dealer) {

        Scanner scanner = new Scanner(System.in);
        for (Player player: table.players) {
            if(player.isBusted()) continue;
            for(int i = 0;i<player.hands.size(); i++) {
                Hand hand = new Hand();
                hand = player.hands.get(i);
                if(hand.isBusted()) continue;
                boolean flag = true;
                while(flag) {
                    System.out.println("What do you want to do with hand " + (player.hands.indexOf(hand) + 1) + " " + player.name + "?");
                    System.out.println("Enter 1 for Hit , 2 for Stand");
                    System.out.println("Current value of your hand is "+hand.get_value_of_hand(minmax[1]));
                    int player_choice;
                    
                    while(true) {
                    	player_choice = scanner.nextInt();
                    	if(player_choice <1 || player_choice >2) 
                    		System.out.println("Please enter either 1 or 2");
                    	else
                    		break;
                    }
                    
                    if (player_choice == 1) {
                        ////////do hit stuff
                        flag = action.hit(hand,dealer,player,table,minmax);

                    } else if (player_choice == 2) {
                        ////////do stand stuff
                        flag = action.stand(hand,player);

                    }
                }
            }
        }
    }
}
