package src;

public class Action {

    public boolean split(Player player, Hand hand,Dealer dealer){
        if (player.wallet.value< hand.bet){
            System.out.println("You don't have enough balance to split!");
            return true;
        }
        if(hand.cards.size()!=2 || (!hand.cards.get(0).face_value.equals(hand.cards.get(1).face_value))) {
            System.out.println("You can't split this hand. You need minimum 2 cards");
            return true;
        }

        //split hand
        Hand new_hand = new Hand();
        new_hand.addCard(player.hands.get(0).cards.get(0));
        new_hand.bet=player.hands.get(0).bet;
        player.hands.get(0).cards.remove(0);
        player.hands.add(new_hand);
        player.setBusted(false);

        //deal one card to each hand
        dealer.deal_player(player);
        Printer.print_player_cards(player);
        //hand.display_Bet();

        return true;
    }

    public boolean hit(Hand hand,Dealer dealer,Player player,Table table,int[] minmax) {
        //Hand newHand = new Hand();
        hand.cards.add(dealer.pick_a_card());
        Printer.print_cards(hand.cards, player);
        int stateAfterHit = checkHandState(hand,table,minmax);
        if(stateAfterHit==-1) return false;
        else return true;

    }

    public boolean stand(Hand hand, Player player){
        Printer.print_cards(hand.cards, player);
        hand.display_Bet();
        return false;
    }
    public boolean double_up(Player player, Hand hand,Dealer dealer){
        if (player.wallet.value< hand.bet){
            System.out.println("You don't have enough balance to double!");
            return true;
        }
        player.wallet.subtract_value(hand.bet);
        hand.bet = hand.bet * 2;
        hand.cards.add(dealer.pick_a_card());
        //hand.print_cards();
        Printer.print_cards(hand.cards, player);
        hand.display_Bet();
        return false;
    }

    public int checkHandState(Hand hand,Table table,int[] minmax) {
        int handState = table.refree.checkHand(hand,minmax);
        switch(handState) {
            case 1 :
                //player wins
                System.out.println("You hit Blackjack");
                handState =1;
                //player.wallet+=(hand.bet*2);
                break;
            case -1 :
                hand.setBusted(true);
                System.out.println("Busted!!");
                handState = -1;
                break;
            case 0 :
                //player can still play
                handState = 0;
                break;
        }
        return handState;
    }

}
