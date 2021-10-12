import src.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        System.out.println("--------------Welcome to BlackJack-------------");

        ArrayList<Player> players = new ArrayList<Player>();

        System.out.println("Enter the number of Players:");
        Scanner scan = new Scanner(System.in);
        int number_of_players = scan.nextInt();
        for (int i=0;i<number_of_players;i++){
            Player temp_player = new Player();
            System.out.println("Enter the name of Player"+" "+(i+1));
            String name = scan.next();
            temp_player.name=name;
        }


    }
}
