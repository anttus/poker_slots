package gamblingsim;

import java.util.*;

public class Slots {

    int betChoice = 0;
    int spin1, spin2, spin3;  
    int result;
    int userChoice;
    int threeKiwi=0,kiwiKiwiA=0,kiwiAA=0,threeCherry=0,threeGrape=0,threeBanana=0,threeLemon=0,threeOrange=0,threePlum=0,noWin=0; 
    double totalSpins;
    public boolean play = true;
    public static boolean end = false;
    boolean success = false;
    String fruitName1 = "", fruitName2 = "", fruitName3 = "";

    Bet slotsBet = new Bet();
    Bank slotsBank = new Bank(1000);
    Random generator = new Random();
    Scanner user_input = new Scanner(System.in);
    Player player = new Player(Player.name, Player.money);
    double bet = slotsBet.betChoice();
    
    /*
    double threeKiwiWin = 4 * slotsBet.bet;
    double kiwiKiwiAWin = 2 * slotsBet.bet;
    double kiwiAAWin = 1 * slotsBet.bet;
    double threeCherryWin = 5 * slotsBet.bet;
    double threeGrapeWin = 10 * slotsBet.bet;
    double threeBananaWin = 12 * slotsBet.bet;
    double threeLemonWin = 20 * slotsBet.bet;
    double threeOrangeWin = 25 * slotsBet.bet;
    double threePlumWin = 50 * slotsBet.bet;
    */

    void roll() {
        while (play == true && end == false) {
            spin1 = generator.nextInt(20);
            spin2 = generator.nextInt(20);
            spin3 = generator.nextInt(20);

            slotsBank.add(slotsBet.bet);
            player.remove(slotsBet.bet);
            
            if (Main.run == true) {
                System.out.println("-- Bet: " + slotsBet.bet);
            }
            fruitSwitch();
            menu(); 
            
            //If you run out of money (Simulation)
            if ((slotsBet.bet >= player.getPlayerMoney() && Main.run == false) || (slotsBank.getMoney() <= 1.0 && Main.run == false)) {
                statistics();
                end = true;
                play = false;
                break;
            }
            //If you run out of money (Normal game)
            else if (slotsBet.bet >= player.getPlayerMoney()) {
                System.out.println("\n-- You can't bet anymore. Thanks for playing!");
                System.out.println();
                end = true;
                play = false;
            }     
        }
    }
    
    void menu() {
        userChoice = -1;
        if (Main.run == true) {
                System.out.println();
                System.out.println(fruitName1 + " " + fruitName2 + " " + fruitName3);
                factor();
                System.out.println("");
                System.out.println(">> Input 1 to play again");
                System.out.println(">> Input 2 to change bet");
                System.out.println(">> Input 0 to quit");
                
                while (success == false && userChoice < 1 || userChoice > 2) {
                    try {
                        userChoice = user_input.nextInt();
                        while (userChoice > 2 || userChoice < 0){
                            System.out.println("Please choose 1, 2 or 0.");
                            userChoice = user_input.nextInt();
                        }
                        switch (userChoice) {
                            case 0:
                                success = true;
                                play = false;
                                Main.success = false;
                                Main.run = false;
                                Main.user_int = -1;
                                break;
                            case 1:
                                success = true;
                                break;
                            case 2:
                                slotsBet.betChoice();
                                break;
                            default:
                                break;
                        }
                        //success = true;  
                    }
                    catch(InputMismatchException e) {
                        System.out.println("Please choose 1, 2 or 0.");
                        user_input.next();
                        success = false;
                    }
                }
                success = false;
                userChoice = -1;
            }
            else {
                userChoice = 1;
                factorSim();
            }
        }
    
    //Assigning fruit values
    void fruitSwitch() {
        String[] fruits = {"Kiwi","Cherry","Grape","Banana","Lemon","Orange","Plum"};
        switch (spin1){
            case 0: fruitName1 = fruits[0]; break;
            case 1: fruitName1 = fruits[0]; break;
            case 2: fruitName1 = fruits[0]; break;
            case 3: fruitName1 = fruits[0]; break;
            case 4: fruitName1 = fruits[0]; break;
            case 5: fruitName1 = fruits[1]; break;
            case 6: fruitName1 = fruits[1]; break;
            case 7: fruitName1 = fruits[1]; break;
            case 8: fruitName1 = fruits[1]; break;
            case 9: fruitName1 = fruits[2]; break;
            case 10: fruitName1 = fruits[2]; break;
            case 11: fruitName1 = fruits[2]; break;
            case 12: fruitName1 = fruits[3]; break;
            case 13: fruitName1 = fruits[3]; break;
            case 14: fruitName1 = fruits[3]; break;
            case 15: fruitName1 = fruits[4]; break;
            case 16: fruitName1 = fruits[4]; break;
            case 17: fruitName1 = fruits[4]; break;
            case 18: fruitName1 = fruits[5]; break;
            case 19: fruitName1 = fruits[6]; break;
        }
        switch (spin2){
            case 0: fruitName2 = fruits[0]; break;
            case 1: fruitName2 = fruits[0]; break;
            case 2: fruitName2 = fruits[1]; break;
            case 3: fruitName2 = fruits[1]; break;
            case 4: fruitName2 = fruits[1]; break;
            case 5: fruitName2 = fruits[1]; break;
            case 6: fruitName2 = fruits[2]; break;
            case 7: fruitName2 = fruits[2]; break;
            case 8: fruitName2 = fruits[2]; break;
            case 9: fruitName2 = fruits[2]; break;
            case 10: fruitName2 = fruits[2]; break;
            case 11: fruitName2 = fruits[3]; break;
            case 12: fruitName2 = fruits[3]; break;
            case 13: fruitName2 = fruits[3]; break;
            case 14: fruitName2 = fruits[3]; break;
            case 15: fruitName2 = fruits[4]; break;
            case 16: fruitName2 = fruits[4]; break;
            case 17: fruitName2 = fruits[4]; break;
            case 18: fruitName2 = fruits[5]; break;
            case 19: fruitName2 = fruits[6]; break;
        }
        switch (spin3){
            case 0: fruitName3 = fruits[0]; break;
            case 1: fruitName3 = fruits[0]; break;
            case 2: fruitName3 = fruits[0]; break;
            case 3: fruitName3 = fruits[1]; break;
            case 4: fruitName3 = fruits[1]; break;
            case 5: fruitName3 = fruits[1]; break;
            case 6: fruitName3 = fruits[1]; break;
            case 7: fruitName3 = fruits[2]; break;
            case 8: fruitName3 = fruits[2]; break;
            case 9: fruitName3 = fruits[2]; break;
            case 10: fruitName3 = fruits[2]; break;
            case 11: fruitName3 = fruits[2]; break;
            case 12: fruitName3 = fruits[2]; break;
            case 13: fruitName3 = fruits[3]; break;
            case 14: fruitName3 = fruits[3]; break;
            case 15: fruitName3 = fruits[3]; break;
            case 16: fruitName3 = fruits[3]; break;
            case 17: fruitName3 = fruits[4]; break;
            case 18: fruitName3 = fruits[5]; break;
            case 19: fruitName3 = fruits[6]; break;
        }
    }
    
    //Winnigns factors (for normal game)
    void factor() {
        if (threeKiwi()) {
            System.out.println("Three kiwis! You won " + 4 * slotsBet.bet);
            slotsBank.remove(4 * slotsBet.bet);
            player.add(4 * slotsBet.bet);
            System.out.println("-- Machine's balance (credits): " + slotsBank.getMoney());
            System.out.println("-- " + player.name + "\'s" + " balance: " + player.getPlayerMoney());
            } 
        else if (kiwikiwia()) {
            System.out.println("Two kiwis! You won " + 2 * slotsBet.bet);
            slotsBank.remove(2 * slotsBet.bet);
            player.add(2 * slotsBet.bet);
            System.out.println("-- Machine's balance (credits): " + slotsBank.getMoney());
            System.out.println("-- " + player.name + "\'s" + " balance: " + player.getPlayerMoney());
            } 
        else if (kiwiaa()) {
            System.out.println("One kiwi! You won " + 1 * slotsBet.bet);
            slotsBank.remove(1 * slotsBet.bet);
            player.add(1 * slotsBet.bet);
            System.out.println("-- Machine's balance (credits): " + slotsBank.getMoney());
            System.out.println("-- " + player.name + "\'s" + " balance: " + player.getPlayerMoney());
            } 
        else if (threeCherry()) {
            System.out.println("Three cherries! You won " + 5 * slotsBet.bet);
            slotsBank.remove(5 * slotsBet.bet);
            player.add(5 * slotsBet.bet);
            System.out.println("-- Machine's balance (credits): " + slotsBank.getMoney());
            System.out.println("-- " + player.name + "\'s" + " balance: " + player.getPlayerMoney());
            } 
        else if (threeGrape()) {
            System.out.println("Three grapes! You won " + 10 * slotsBet.bet);
            slotsBank.remove(10 * slotsBet.bet);
            player.add(10 * slotsBet.bet);
            System.out.println("-- Machine's balance (credits): " + slotsBank.getMoney());
            System.out.println("-- " + player.name + "\'s" + " balance: " + player.getPlayerMoney());
            } 
        else if (threeBanana()) {
            System.out.println("Three bananas! You won " + 12 * slotsBet.bet);
            slotsBank.remove(12 * slotsBet.bet);
            player.add(12 * slotsBet.bet);
            System.out.println("-- Machine's balance (credits): " + slotsBank.getMoney());
            System.out.println("-- " + player.name + "\'s" + " balance: " + player.getPlayerMoney());
            } 
        else if (threeLemon()) {
            System.out.println("Three lemons! You won " + 20 * slotsBet.bet);
            slotsBank.remove(20 * slotsBet.bet);
            player.add(20 * slotsBet.bet);
            System.out.println("-- Machine's balance (credits): " + slotsBank.getMoney());
            System.out.println("-- " + player.name + "\'s" + " balance: " + player.getPlayerMoney());
            } 
        else if (threeOrange()) {
            System.out.println("Three oranges! You won " + 25 * slotsBet.bet);
            slotsBank.remove(25 * slotsBet.bet);
            player.add(25 * slotsBet.bet);
            System.out.println("-- Machine's balance (credits): " + slotsBank.getMoney());
            System.out.println("-- " + player.name + "\'s" + " balance: " + player.getPlayerMoney());
            } 
        else if (threePlum()) {
            System.out.println("Three plums! You won " + 50 * slotsBet.bet);
            slotsBank.remove(50 * slotsBet.bet);
            player.add(50 * slotsBet.bet);
            System.out.println("-- Machine's balance (credits): " + slotsBank.getMoney());
            System.out.println("-- " + player.name + "\'s" + " balance: " + player.getPlayerMoney());
            } 
        else {
            System.out.println("No win.");
            System.out.println("-- Machine's balance (credits): " + slotsBank.getMoney());
            System.out.println("-- " + player.name + "\'s" + " balance: " + player.getPlayerMoney());
            }
    }
    
    //Winnings factors (for simulation)
    void factorSim() {
        if (threeKiwi()) {
            threeKiwi++;
            slotsBank.remove(15.0 * slotsBet.bet);
            player.add(15.0 * slotsBet.bet);
        }
        else if (kiwikiwia()) {
            kiwiKiwiA++;
            slotsBank.remove(5.0 * slotsBet.bet);
            player.add(5.0 * slotsBet.bet);
        }
        else if (kiwiaa()) {
            kiwiAA++;
            slotsBank.remove(2.0 * slotsBet.bet);
            player.add(2.0 * slotsBet.bet);
        }
        else if (threeCherry()) {
            threeCherry++;
            slotsBank.remove(25.0 * slotsBet.bet);
            player.add(25.0 * slotsBet.bet);
        }
        else if (threeGrape()) {
            threeGrape++;
            slotsBank.remove(25.0 * slotsBet.bet);
            player.add(25.0 * slotsBet.bet);
        }
        else if (threeBanana()) {
            threeBanana++;
            slotsBank.remove(50.0 * slotsBet.bet);
            player.add(50.0 * slotsBet.bet);
        }
        else if (threeLemon()) {
            threeLemon++;
            slotsBank.remove(100.0 * slotsBet.bet);
            player.add(100.0 * slotsBet.bet);
        }
        else if (threeOrange()) {
            threeOrange++;
            slotsBank.remove(250.0 * slotsBet.bet);
            player.add(250.0 * slotsBet.bet);
        }
        else if (threePlum()) {
            threePlum++;
            slotsBank.remove(1000.0 * slotsBet.bet);
            player.add(1000.0 * slotsBet.bet);
        }
        else {
            noWin++;
        }
    }
    
    public boolean allSame() {
        if (fruitName1 == fruitName2 && fruitName2 == fruitName3) {
            return true;
        } else {
            return false;
        }
    }

    public boolean twoSame() {
        if (fruitName1 == fruitName2 || fruitName2 == fruitName3 || fruitName3 == fruitName1) {
            return true;
        } else {
            return false;
        }
    }

    //Kiwi and two other symbols
    public boolean kiwiaa() {
        if (spin1 <= 4 || spin2 <= 1 || spin3 <= 2) {
            return true;
        } else {
            return false;
        }
    }

    //Two kiwis and one other symbol
    public boolean kiwikiwia() {
        if (spin1 <= 4 && spin2 <= 1 || spin2 <= 1 && spin3 <= 2 || spin3 <= 2 && spin1 <= 4) {
            return true;
        } else {
            return false;
        }
    }

    //Three kiwis
    public boolean threeKiwi() {
        if (spin1 <= 4 && spin2 <= 1 && spin3 <= 2) {
            return true;
        } else {
            return false;
        }
    }

    //Three cherries
    public boolean threeCherry() {
        if (spin1 > 4 && spin1 <= 8 && spin2 > 1 && spin2 <= 5 && spin3 > 2 && spin3 <= 6) {
            return true;
        } else {
            return false;
        }
    }

    //Three grapes
    public boolean threeGrape() {
        if (spin1 > 8 && spin1 <= 11 && spin2 > 5 && spin2 <= 10 && spin3 > 6 && spin3 <= 12) {
            return true;
        } else {
            return false;
        }
    }
    
    //Three bananas
    public boolean threeBanana() {
        if (spin1 > 11 && spin1 <= 14 && spin2 > 10 && spin2 <= 14 && spin3 > 12 && spin3 <= 16) {
            return true;
        } else {
            return false;
        }
    }

    //Three lemons
    public boolean threeLemon() {
        if (spin1 > 14 && spin1 <= 17 && spin2 > 14 && spin2 <= 17 && spin3 > 16 && spin3 <= 17) {
            return true;
        } else {
            return false;
        }
    }

    //Three oranges
    public boolean threeOrange() {
        if (spin1 > 17 && spin1 <= 18 && spin2 > 17 && spin2 <= 18 && spin3 > 17 && spin3 <= 18) {
            return true;
        } else {
            return false;
        }
    }

    //Three plums
    public boolean threePlum() {
        if (spin1 > 18 && spin1 <= 19 && spin2 > 18 && spin2 <= 19 && spin3 > 18 && spin3 <= 19) {
            return true;
        } else {
            return false;
        }
    }
    
    void statistics() {
        totalSpins = kiwiAA+threeKiwi+kiwiKiwiA+threeCherry+threeGrape+threeBanana+threeLemon+threeOrange+threePlum+noWin;
        System.out.println("-- Statistics:");
        System.out.printf("Your balance:      %-2.1f\n", player.getPlayerMoney());
        System.out.printf("Machine's balance: %-2.1f\n", slotsBank.getMoney());
        System.out.printf("No wins:           %-5d (%-2.2f%s\n", noWin, (noWin / totalSpins * 100), "%)");
        System.out.printf("Kiwi and any two:  %-5d (%-2.2f%s\n", kiwiAA, (kiwiAA / totalSpins * 100), "%)");
        System.out.printf("Two kiwis and any: %-5d (%-2.2f%s\n", kiwiKiwiA, (kiwiKiwiA / totalSpins * 100), "%)");
        System.out.printf("Three kiwis:       %-5d (%-2.3f%s\n", threeKiwi, (threeKiwi / totalSpins * 100), "%)");
        System.out.printf("Three cherries:    %-5d (%-2.3f%s\n", threeCherry, (threeCherry / totalSpins * 100), "%)");
        System.out.printf("Three grapes:      %-5d (%-2.3f%s\n", threeGrape, (threeGrape / totalSpins * 100), "%)");
        System.out.printf("Three bananas:     %-5d (%-2.3f%s\n", threeBanana, (threeBanana / totalSpins * 100), "%)");
        System.out.printf("Three lemons:      %-5d (%-2.3f%s\n", threeLemon, (threeLemon / totalSpins * 100), "%)");
        System.out.printf("Three oranges:     %-5d (%-2.4f%s\n", threeOrange, (threeOrange / totalSpins * 100), "%)");
        System.out.printf("Three plums:       %-5d (%-2.4f%s\n", threePlum, (threePlum / totalSpins * 100), "%)");
        System.out.printf("Total spins:       %-5d\n", ((int) totalSpins));
    }
}
