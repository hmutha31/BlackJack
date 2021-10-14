package src;

import java.util.ArrayList;

public class Hand {
    public ArrayList<Card> cards;
    public int bet;

    public Hand(){
        this.cards= new ArrayList<Card>();
        this.bet=0;
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public void place_bet(int bet){
        this.bet = this.bet+bet;
    }

    public void print_cards(){
        System.out.println("The cards"+" "+ " are:");
            for (Card card : cards) {
                System.out.println(card.face_value);
            }
    }




}
