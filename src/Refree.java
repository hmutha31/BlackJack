package src;

public class Refree {
    public Table table;
    public Dealer dealer;

    public Refree(Table table, Dealer dealer){
        this.table=table;
        this.dealer=dealer;
    }

    public int checkHand(Hand hand,int[] minmax) {
        int sum = hand.get_value_of_hand(minmax[1]);
        if(sum==minmax[1]) return 1;
        else if(sum>minmax[1]) return -1;
        else return 0;
    }

    public int checkDealerHand(Hand hand,Dealer dealer, int [] minmax) {
        int sum = hand.get_value_of_hand(minmax[1]);
        if(sum<minmax[0]) {
            while(sum<minmax[0]) {
                Printer.print_cards(dealer.hand.cards,dealer);
                hand.addCard(dealer.pick_a_card());
                sum = hand.get_value_of_hand(minmax[1]);
            }
        }
        if(sum>=minmax[0] && sum<=minmax[1]) return 1;
        else return -1;
    }
    public boolean all_busted(){
        int number_of_non_busted =0;
        for (Player player: this.table.players) {
            for (Hand hand: player.hands) {
                if (!hand.isBusted()){
                    number_of_non_busted+=1;
                }
            }

        }
        if (number_of_non_busted<1){
            System.out.println("Dealer Wins! Everybody busted!");
            return true;
        }
        return false;
    }

    public void check_winners(int [] minmax){

        int dealerState = this.checkDealerHand(dealer.hand,dealer,minmax);
        Printer.print_cards(dealer.hand.cards,dealer);
        if(dealerState==1) {
            //dealer is above 17 & less than 21
            int dealerSum = dealer.hand.get_value_of_hand(minmax[1]);
            for(Player player : table.players) {
                for(Hand hand : player.hands) {
                    if(!hand.isBusted()) {
                        if(hand.get_value_of_hand(minmax[1]) > dealerSum) {
                            System.out.println(player.name+" Wins!!");
                            player.wallet.add_value(hand.bet*2);
                            dealer.wallet.subtract_value(hand.bet*2);
                        }else if(hand.get_value_of_hand(minmax[1])==dealerSum && minmax[0]<27) {
                            player.wallet.add_value(hand.bet);
                            System.out.println(player.name+" ties with dealer");
                        }else {
                            dealer.wallet.add_value(hand.bet*2);
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
