public class Card extends Deck {
    public Rank face_value;
    public Suit type;

    public Card(Rank face_value, Suit type) {
        this.face_value = face_value;
        this.type = type;
    }

    public Card() {
    }

}