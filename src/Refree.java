package src;

public class Refree {
    Table table;

    public Refree(Table table){
        this.table = table;
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
}
