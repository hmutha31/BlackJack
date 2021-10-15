package src;

public class Wallet {
    public int value;

    public Wallet(int value){
        this.value=value;
    }

    public void add_value(int value){
        this.value+=value;
    }

    public void subtract_value(int value){
        this.value-=value;
    }


}
