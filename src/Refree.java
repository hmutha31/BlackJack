package src;

public class Refree {
    public Table table;
    public Dealer dealer;

    public Refree(Table table, Dealer dealer){
        this.table=table;
        this.dealer=dealer;
    }

    public int checkHand(Hand hand) {
        int sum = hand.get_value_of_hand();
        if(sum==21) return 1;
        else if(sum>21) return -1;
        else return 0;
    }

    public int checkDealerHand(Hand hand,Dealer dealer) {
        int sum = hand.get_value_of_hand();
        if(sum<17) {
            while(sum<17) {
                hand.addCard(dealer.pick_a_card());
                sum = hand.get_value_of_hand();
            }
        }
        if(sum>=17 && sum<=21) return 0;
        else return -1;
    }

    public void check_winners(){
        int dealerState = this.checkDealerHand(dealer.hand,dealer);
        if(dealerState==1) {
            //dealer is above 17 & less than 21
            int dealerSum = dealer.hand.get_value_of_hand();
            for(Player player : table.players) {
                for(Hand hand : player.hands) {
                    if(!hand.isBusted()) {
                        if(hand.get_value_of_hand() > dealerSum) {
                            System.out.println(player.name+" Wins!!");
                            player.wallet.add_value(hand.bet*2);
                        }else if(hand.get_value_of_hand()==dealerSum) {
                            player.wallet.add_value(hand.bet);
                            System.out.println(player.name+" ties with dealer");
                        }else {
                            System.out.println(player.name+ " loses");
                        }
                    }
                }
            }
        }
        else {
            //dealer is busted
            System.out.println("Dealer Busted!!");
            dealer.hand.setBusted(true);
            //everyone who stood wins
            for(Player player: table.players) {
                for(Hand hand : player.hands) {
                    if(!hand.isBusted()) {
                        player.wallet.add_value(hand.bet*2);
                    }
                }
            }
        }
    }
}
