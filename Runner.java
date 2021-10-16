
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
    	int choice;
        System.out.println("What do you want to play? \nEnter 1 for BlackJack, 2 for Trianta Ena");
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
        	choice = scanner.nextInt();
        	if(choice<1 || choice >2) 
        		System.out.println("Please enter either 1 or 2");
        	else
        		break;
        }
        
        Game game = null;
        if(choice==1) {
            game = new Blackjack();
        }else {
            game = new Trinata();
        }
        game.play();
    }
}
