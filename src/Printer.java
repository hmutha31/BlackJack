package src;

import java.util.ArrayList;

public class Printer {
    public static void print_cards_with_hidden(ArrayList<Card> cards){
        System.out.println("The dealer's cards are");
        int flag=0;
        for (Card card:cards) {
            if (flag==0){
                System.out.println("HIDDEN");
                flag=1;
                continue;
            }
            System.out.println(card.face_value+ " of "+card.type);
        }

    }

    public static void print_cards(ArrayList<Card> cards, Object person){
        if(person instanceof Dealer) {
            System.out.println("The dealers cards are : ");
        }else {
            Player player = (Player)person;
            System.out.println("The cards of "+player.name+ " are :");
        }
        for (Card card: cards) {
            System.out.println(card.face_value+ " of "+card.type);
        }
    }

    public static void print_player_cards(Player player) {
        for(Hand hand : player.hands) {
            System.out.println("The cards in hand "+player.hands.indexOf(hand)+1 + " of "+player.name + " are : ");
            for(Card card : hand.cards) {
                System.out.println(card.face_value+ " of "+card.type);
            }
        }
    }
}
