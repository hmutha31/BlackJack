package src;

import java.util.ArrayList;

public class Hand {
    public ArrayList<Card> cards;

    public Hand(){
        this.cards= new ArrayList<Card>();
    }

    public void addCard(Card card){
        this.cards.add(card);
    }


}
