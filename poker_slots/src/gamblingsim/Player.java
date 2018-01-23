package gamblingsim;
import java.util.*;

public class Player {
    public static String name;
    public static double money = -1;
    public Bank playerBank = new Bank(money);
    Scanner input = new Scanner(System.in);
    
    //Method for setting the player's name and money
    public void playerInfo() {
        boolean success = false;
        System.out.println("Enter your name:");
        name = input.next();
        System.out.println("\nHow much money do you have?");
        while (success == false) {
            try {
                money = input.nextDouble();
                while (money <= 0) {
                    System.out.println("Please type in a number higher than 0: ");
                    money = input.nextDouble();
                }
                success = true;
            } catch (InputMismatchException e){
                System.out.println("Please type in a number:");
                input.next();
                success = false;
            }
        }
        playerBank.setMoney(money);
    }

    //Returns the player's money 
    public double getPlayerMoney(){
        money = playerBank.getMoney();
        return money;
    }
    
    public double remove(double money) {
        playerBank.remove(money);
        return money;
    }
    
    public double add(double money) {
        playerBank.add(money);
        return money;
    }

    public Player(String name, double money) {
        this.name = name;
        this.money = money;
    }
}
