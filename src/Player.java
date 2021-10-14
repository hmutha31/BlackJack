package src;
import src.Action;
import src.Card;

import java.util.*;

public class Player {
    public int wallet;
    public String name;
    public ArrayList<Hand> hands;
    public Action action;

    public Player(String name, int wallet_balance){
        this.name=name;
        this.wallet = wallet_balance;
        this.hands=new ArrayList<Hand>();
    }

    public Player(){
    this.name="";
    this.wallet=1000;
    }




}