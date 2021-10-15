package src;

import java.util.ArrayList;

public class Hand {
    public ArrayList<Card> cards;
    public int bet;
    private boolean isBusted;

    public Hand() {
        this.cards = new ArrayList<Card>();
        this.bet = 0;
        this.isBusted = false;
    }

    public boolean isBusted() {
        return isBusted;
    }

    public void setBusted(boolean busted) {
        isBusted = busted;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void place_bet(int bet) {
        this.bet = this.bet + bet;
    }

    public void display_Bet() {
        System.out.println("The bet is: " + this.bet);
    }

    public int get_value_of_hand(int maxValue) {
        int temp = 0;
        boolean isThereAnAce = false;
        for (Card card : this.cards) {
            if(card.face_value.getValue_of_rank() == 11)
                isThereAnAce = true;
        }
        for (Card card : this.cards) {
            temp = temp + card.face_value.getValue_of_rank();
        }

        if(temp > maxValue && isThereAnAce == true)
        {
            temp = temp - 10;
        }
        return temp;
    }
}