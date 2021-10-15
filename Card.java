
public class Card extends Deck {
    public Rank face_value;
    public Suit type;
    public boolean isFaceDown;

    public Card(Rank face_value, Suit type) {
        this.face_value = face_value;
        this.type = type;
        this.isFaceDown = false;
    }

    public Card() {}
}