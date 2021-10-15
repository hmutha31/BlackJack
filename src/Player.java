package src;

import java.util.*;

public class Player {
    public Wallet wallet;
    public String name;
    public ArrayList<Hand> hands;
    public Action action;
    private boolean isBusted;
    private boolean didHitMax;

    public boolean isDidHitMax() {
        return didHitMax;
    }

    public void setDidHitMax(boolean didHitMax) {
        this.didHitMax = didHitMax;
    }

    public boolean isBusted() {
        return isBusted;
    }

    public void setBusted(boolean busted) {
        isBusted = busted;
    }

    public Player(String name, int wallet_balance){
        this.name=name;
        this.isBusted = false;
        this.didHitMax = false;
        this.wallet = new Wallet(wallet_balance);
        this.hands=new ArrayList<Hand>();
        this.hands.add(new Hand());
    }

    public Player(){
        this.name="";
        this.wallet= new Wallet(1000) ;
        this.hands=new ArrayList<Hand>();
        this.hands.add(new Hand());
        this.isBusted = false;
        this.didHitMax = false;
    }

}