package src;
import src.Action;
import src.Card;

import java.util.*;

public class Player {
    public int wallet;
    public String name;
    public ArrayList<Card> cards;
    public Action action;

    public Player(String name, int wallet_balance){
        this.name=name;
        this.wallet = wallet_balance;
        this.cards=new ArrayList<Card>();
    }

    public Player(){
    this.name="";
    this.wallet=1000;
    }

    public void addCard(Card card){
        this.cards.add(card);
    }


}