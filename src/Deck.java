package src;
import java.util.*;


public class Deck {
    public enum Suit {
        CLUBS,
        SPADES,
        HEARTS,
        DIAMONDS;
    }

    public enum Rank {
        ACE(11),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);
    private final int value_of_rank;

    Rank(int value_of_rank)
    {
        this.value_of_rank = value_of_rank;
    }
    public int getValue_of_rank(){
        return value_of_rank;
    }
    }

    public int no_of_cards; //no of cards in one deck
    public ArrayList<Card> cards = new ArrayList<Card>();

    public Deck(int no_of_cards) {
        this.no_of_cards = no_of_cards;
        fillDeck();
    }

    public Deck() {}
    public Deck(int no_of_cards, ArrayList<Card> cards) {
        this.no_of_cards = no_of_cards;
        this.cards = cards;
    }

    public void fillDeck() {
        int i=0;
        for(Suit suit:Suit.values()) {
            for(Rank rank : Rank.values()) {
                cards.add(new Card(rank,suit));
                i+=1;
                if (i>no_of_cards) {
                    return;
                }
            }
        }
    }
}

