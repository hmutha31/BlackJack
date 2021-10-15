package src;

public interface Game {

    public void play();
    public void resetRound(Table table, Dealer dealer);
    public void ask_for_actions(int[] minmax,Dealer dealer);
}
