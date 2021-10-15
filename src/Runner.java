package src;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        System.out.println("Choose Blackjack or Trinata? \n 1.BJ, 2.T31");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        Game game = null;
        if(choice==1) {
            game = new Blackjack();
        }else {
            game = new Trinata();
        }

        game.play();
    }
}
