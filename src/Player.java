package src;
import src.Action;
import src.Card;

import java.util.*;

public class Player {
    public int wallet;
    public String name;
    public ArrayList<Hand> hands;
    public Action action;
    private boolean isBusted;

    public boolean isBusted() {
        return isBusted;
    }

    public void setBusted(boolean busted) {
        isBusted = busted;
    }

    public Player(String name, int wallet_balance){
        this.name=name;
        this.isBusted = false;
        this.wallet = wallet_balance;
        this.hands=new ArrayList<Hand>();
        this.hands.add(new Hand());
    }

    public Player(){
        this.name="";
        this.wallet=1000;
        this.hands=new ArrayList<Hand>();
        this.hands.add(new Hand());
        this.isBusted = false;
    }

    public void split(){
        Hand new_hand = new Hand();
        new_hand.addCard(this.hands.get(0).cards.get(0));
        new_hand.bet=this.hands.get(0).bet;
        this.hands.get(0).cards.remove(0);
        this.hands.add(new_hand);
        this.isBusted = false;

    }

    public void print_cards(){
        System.out.println("The cards of "+" "+this.name + " are:");
        for (Hand hand: this.hands) {
            System.out.println("The cards in hand "+this.hands.indexOf(hand));
            for (Card card : hand.cards) {
                System.out.println(card.face_value);
            }
        }
    }








}