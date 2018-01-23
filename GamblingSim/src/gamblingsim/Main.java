package gamblingsim;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
* @authors Anttu Suhonen & Henri Mankki
*/

public class Main {
    public static boolean run = false;
    static boolean success = false;
    public static int user_int = -1;
    
    public static void main(String[] args) { 
        Scanner input = new Scanner(System.in);
        Player player = new Player(Player.name, Player.money);
        player.playerInfo();
        Bank playerBank = new Bank(player.money);
        
        while (user_int != 0 && PokerHand.end != true && Slots.end != true) {
            System.out.println("\nMain menu:");
            System.out.println(">> Input 1 to play poker.");
            System.out.println(">> Input 2 to play slots.");
            System.out.println(">> Input 3 to start poker's simulation.");
            System.out.println(">> Input 4 to start slots's simulation.");
            System.out.println(">> Input 0 to end. \n");
            
            while (success == false && user_int < 1 || user_int > 5) {
                try {
                    user_int = input.nextInt();
                    while (user_int < 0 || user_int > 4){
                        System.out.println("Please choose a number between 0-4: ");
                        user_int = input.nextInt();
                    }
                    success = true;
                }
                catch (InputMismatchException e){
                    System.out.println("Please choose a number between 0-4: ");
                    input.next();
                    success = false;
                }
            }
            switch (user_int) {
                case 1:
                    //Poker
                    run = true;
                    System.out.println("-- Poker");
                    Poker poker = new Poker();
                    poker.playPoker();
                    break;
                case 2:
                    //Slots
                    run = true;
                    System.out.println("-- Slots");
                    Slots slots = new Slots();
                    slots.roll();
                    break;
                case 3:
                    //Poker simulation
                    System.out.println("-- Poker simulation");
                    Poker pokerSim = new Poker();
                    PokerHand hand = new PokerHand();
                    pokerSim.playPoker();
                    break;
                case 4:
                    //Slots simulation
                    System.out.println("-- Slots simulation");
                    Slots slotsSim = new Slots();
                    slotsSim.roll();
                    break;
                case 0:
                    System.out.println("\nThanks for playing! \n");
                    break;
                default:
                    break;
            }
        }    
    }    
}