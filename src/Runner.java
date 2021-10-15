package src;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        System.out.println("What do you want to play? \nEnter 1 for BlackJack, 2 for Trianta Ena");
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
