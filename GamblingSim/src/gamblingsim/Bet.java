package gamblingsim;

import java.util.*;

public class Bet {
    public int betChoice = 0;
    public double bet = 0.2;
    Scanner user_input = new Scanner(System.in);
    
    public double betChoice() {
        boolean success = false;
        System.out.println("Choose how much you want to bet (1-5): ");
        System.out.println("[1 = 0.2], [2 = 0.4], [3 = 0.6], [4 = 0.8], [5 = 1.0]");
        System.out.println();
        betChoice = 0;
        
        while (success == false && betChoice < 1 || betChoice > 5) {
            try {
                betChoice = user_input.nextInt();
                while (betChoice < 1 || betChoice > 5){
                    System.out.println("Please choose a number between 1-5: ");
                    betChoice = user_input.nextInt();
                }
                success = true;
            }
            catch (InputMismatchException e){
                System.out.println("Please choose a number between 1-5: ");
                user_input.next();
                success = false;
            }
        }
        
        System.out.println();
        switch (betChoice) {
            case 1:
                bet = 0.2;
                break;
            case 2:
                bet = 0.4;
                break;
            case 3:
                bet = 0.6;
                break;
            case 4:
                bet = 0.8;
                break;
            case 5:
                bet = 1;
                break;
            default:
                bet = 0.0;
                break;
        }
        return bet;
    }
}
