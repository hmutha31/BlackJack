public class Deck {
    public enum Suit {
        CLUBS,
        SPADES,
        HEARTS,
        DIAMONDS;
    }

    public enum Rank {
        ACE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING;}

    public int no_of_cards; //no of cards in one deck
    public ArrayList<Card> cards = new ArrayList<Cards>();

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
                cards[i] = new Card(rank,suit);
                i+=1;
                if (i>no_of_cards) {
                    return;
                }
            }
        }
    }
}

