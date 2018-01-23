package gamblingsim;
import java.util.Scanner;
import java.util.InputMismatchException;

public class PokerHand {
    private Card[] cards;
    private int[] handvalue;
    private int hand_int;
    private int highCard=0, pair=0, twoPair=0, threeOfKind=0, straight=0, flush=0, fullHouse=0, fourOfKind=0, straightFlush=0;
    double total = 0;
    private boolean play = true;
    private boolean swap = false;
    private boolean cont = false;
    private boolean success = false;
    public static boolean end = false;
    
    Bet pokerBet = new Bet();
    Scanner user_input = new Scanner(System.in);
    Bank pokerBank = new Bank(500);
    Player player = new Player(Player.name, Player.money);
    
//PICKS AND EVALUATES HAND
    public void PokerHand(Deck deck) {
        handvalue = new int[6];
        cards = new Card[5];
        for (int i=0;i<5;i++){
            cards[i] = deck.draw();
            //This inserts the picked cards to the cards[] array
        }
        evaluateHand();
    }
    
//EVALUATES THE HAND
    void evaluateHand() {
        int[] values = new int[14];
        
        for(int i=0;i<=13;i++){
            values[i] = 0; 
            //Empties the array
        }
        for(int i=0;i<=4;i++){
            values[cards[i].getCard()]++;
            //This increments the 'values' array 
            //at the index of each card's value
        }
        
        //Checking for same card values:
        //Pair, 2 pairs, three of a kind, four of a kind and full house
        int sameCards1 = 1, sameCards2 = 1;
        int groupValue1 = 0, groupValue2 = 0; //groupValue1 is the first pair or set of cards
        
        for (int i=13;i>=0;i--){
            if(values[i] > sameCards1){
                if (sameCards1 != 1) {
                    sameCards2 = sameCards1;
                    groupValue2 = groupValue1;
                }
                sameCards1 = values[i];
                groupValue1 = i;
            }
            else if (values[i] > sameCards2){
                sameCards2 = values[i];
                groupValue2 = i;
            }
        }
        
        //Flush (if 2 cards are not the same suit, there's no flush)
        boolean flush = true;
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                if (cards[i].getSuit() != cards[i+1].getSuit()) {
                    flush = false;
                }
                break;
            }
            if (cards[i].getSuit() != cards[i+1].getSuit()) {
                flush = false;
            break;
            }
        }
        
        //Straight (5 cards in a row)
        int topStraight = 0;
        boolean straight = false;
        
        for(int i=1;i<=9;i++){ //There's no straight if the lowest value is more than 10
            if (values[i] == 1 && values[i+1] == 1 && values[i+2] == 1 && values[i+3] == 1 && values[i+4] == 1) {
                straight = true;
                topStraight = i+4;
                break;
            }
            if (values[10] == 1 && values[11] == 1 && values[12] == 1 && values[13] == 1 && values[1] == 1){
                //This is for ace high because ace is values[1]
                straight = true;
                topStraight = 14;
            }
        }

        //Putting the card values in order
        int[] orderedValues = new int[5];
        int index = 0;
        
        if (values[1] == 1) {
            orderedValues[index] = 14; //Turn ace into 14, the highest
            index++;
        }
        for (int i=13;i>=2;i--) {
            if (values[i] == 1) {
                orderedValues[index] = i;
                index++;
            }
        }
        
        //Making an array of the hand ranks
        //Case 1
        if (sameCards1 == 1) { //High card
            handvalue[0] = 1;
            handvalue[1] = orderedValues[0]; 
            handvalue[2] = orderedValues[1]; 
            handvalue[3] = orderedValues[2];
            handvalue[4] = orderedValues[3];
            handvalue[5] = orderedValues[4];         
        }
        //1 pair
        //Case 2
        if (sameCards1 == 2 && sameCards2 == 1) { 
            handvalue[0] = 2;
            handvalue[1] = groupValue1;
            handvalue[2] = orderedValues[0];
            handvalue[3] = orderedValues[1];
            handvalue[4] = orderedValues[2];
        }
        //2 pairs
        //Case 3
        if (sameCards1 == 2 && sameCards2 == 2) { 
            handvalue[0] = 3;
            handvalue[1] = groupValue1 > groupValue2 ? groupValue1 : groupValue2;
            handvalue[2] = groupValue1 < groupValue2 ? groupValue1 : groupValue2;
            handvalue[3] = orderedValues[0];
        }
        //3 of a kind
        //Case 4
        if (sameCards1 == 3) { 
            handvalue[0] = 4;
            handvalue[1] = groupValue1;
            handvalue[2] = orderedValues[0];
            handvalue[3] = orderedValues[1];
        }
        //Straight
        //Case 5
        if (straight) { 
            handvalue[0] = 5;
            handvalue[1] = topStraight; 
            //If there's two straights, the one with the top value wins
        }
        //Flush
        //Case 6
        if (flush) { 
            handvalue[0] = 6;
            handvalue[1] = orderedValues[0];
            handvalue[2] = orderedValues[1];
            handvalue[3] = orderedValues[2];
            handvalue[4] = orderedValues[3];
            handvalue[5] = orderedValues[4];  
        }
        //Full house
        //Case 7
        if(sameCards1 == 3 && sameCards2 == 2) {
            handvalue[0] = 7;
            handvalue[1] = groupValue1;
            handvalue[2] = groupValue2;
        }
        //Four of a kind
        //Case 8
        if (sameCards1 == 4) {
            handvalue[0] = 8;
            handvalue[1] = groupValue1;
            handvalue[2] = groupValue2;
        }
        //Straight flush
        //Case 9
        if (straight && flush) {
            handvalue[0] = 9;
            handvalue[1] = topStraight;
        }
    }    
          
//Displaying the hand
    void displayHand() {        
        String hand;
        switch (handvalue[0]) {
            case 1: hand = "High card. ";
                hand_int = 1;
                highCard++;
                break;
            case 2: hand = "A pair of " + Card.suitAsString(handvalue[1]) + "\'s";
                hand_int = 2;
                pair++;
                break;
            case 3: hand = "Two pairs: " + Card.suitAsString(handvalue[1]) + "\'s" + " and " + Card.suitAsString(handvalue[2]) + "\'s";
                hand_int = 3;
                twoPair++;
                break;
            case 4: hand = "Three of a kind: " + Card.suitAsString(handvalue[1]) + "\'s";
                hand_int = 4;
                threeOfKind++;
                break;
            case 5: hand = Card.suitAsString(handvalue[1]) + " high straight.";
                hand_int = 5;
                straight++;
                break;
            case 6: hand = "Flush.";
                hand_int = 6;
                flush++;
                break;
            case 7: hand = "Full house: " + Card.suitAsString(handvalue[1]) + "\'s" + " and " + Card.suitAsString(handvalue[2]) + "\'s";
                hand_int = 7;
                fullHouse++;
                break;
            case 8: hand = "Four of a kind: " + Card.suitAsString(handvalue[1]) + "\'s";
                hand_int = 8;
                fourOfKind++;
                break;
            case 9: hand = Card.suitAsString(handvalue[1]) + "high straight flush.";
                hand_int = 9;
                straightFlush++;
                break;
            default: hand = "Error.";
        }
        if (Main.run == true) {
            System.out.println("\n-- Your hand: \n");
            System.out.println(hand);
            System.out.println();
            displayAll(); 
        }
    } 
    
//Print out all the cards you have
    void displayAll() { 
        int x = 1;
        for (int i=0;i<5;i++){
            System.out.println(x + ": " + cards[i]);
            x++;
        }
    }
    
//CHECKING HAND
    public boolean hand(int value) {
        if (hand_int == value) {
            return true;
        }
        else {
            return false;
        }
    }
    
//BETTING FACTORS   
    void printFactors(double factor) {
        pokerBank.remove(factor * pokerBet.bet);
        player.add(factor * pokerBet.bet);
        if (Main.run == true) {
            System.out.println("\nYou won " + factor * pokerBet.bet + " credits. \n");
            System.out.println("-- " + player.name + "\'s" + " balance: " + player.getPlayerMoney());
            System.out.println("-- Machine's balance (credits): " + pokerBank.getMoney());
        }
        cont = false;
    }
    /*  double factor:
    *   1 = High card
    *   2 = Pair
    *   3 = Two pairs
    *   4 = Three of a kind
    *   5 = Straight
    *   6 = Flush
    *   7 = Full house
    *   8 = Four of a kind
    *   9 = Straight flush
    */
    
    void factors(){
        if ((hand(1) && swap == true) || (hand(1) && cont == true)) {
            printFactors(0.0);
        }
        else if ((hand(2) && swap == true) || (hand(2) && cont == true)) {
            printFactors(1.0);
        }
        else if ((hand(3) && swap == true) || (hand(3) && cont == true)) {
            printFactors(2.0);
        }
        else if ((hand(4) && swap == true) || (hand(4) && cont == true)) {
            printFactors(2.0);
        }
        else if ((hand(5) && swap == true) || (hand(5) && cont == true)) {
            printFactors(3.0);
        }
        else if ((hand(6) && swap == true) || (hand(6) && cont == true)) {
            printFactors(4.0);
        }
        else if ((hand(7) && swap == true) || (hand(7) && cont == true)) {
            printFactors(8.0);
        }
        else if ((hand(8) && swap == true) || (hand(8) && cont == true)) {
            printFactors(15.0);
        }
        else if ((hand(9) && swap == true) || (hand(9) && cont == true)) {
            printFactors(75.0);
        }
        else {
        }
    }
    
//SWAPPING CARDS
    void swapCards() {
        swap = true;
        Deck newDeck = new Deck();
        newDeck.deck();
        int j = 1;
        int choice = 0;
        System.out.println(">> Input 1 to swap the card. ");
        System.out.println(">> Input 0 to skip the card.");
        while (success == false && choice <= 1 || choice >= 0) {
            try {
                //The for-loop is for the five cards
                for (int i=0;i<5;i++) {
                    System.out.println("Card " + j + ": " + cards[i]);
                    choice = user_input.nextInt();
                    while (choice > 1 || choice < 0) {
                        System.out.println("Input 1 or 0.");
                        choice = user_input.nextInt();
                    }
                    if (choice == 1) {
                        cards[i] = newDeck.draw();
                        evaluateHand();
                    }
                    else if (choice == 0) {
                        evaluateHand();
                    }
                    evaluateHand();
                    j++;
                }
                success = true;
                break;
            }
            catch(InputMismatchException e) {
                System.out.println("Please input 1 or 0.");
                user_input.next();
                success = false;
            }
        }
        success = false;
        displayHand();
        factors();
        playAgain();
        cont = false;
    }
    
//Play again?
    void playAgain(){
        int inputChoice = 4;
        Deck newDeck = new Deck();
        if (Main.run == true) {
            System.out.println();
            System.out.println(">> Input 1 to continue.");
        }
        if (swap == false && Main.run == true) {
            System.out.println(">> Input 2 to swap cards. ");
        }
        if (swap == true && Main.run == true) {
            System.out.println(">> Input 3 to change bet.");
            System.out.println(">> Input 0 to end.\n");
        }
        
//Main.run is to check if the simulation is running or not (if true, then not)
        if (Main.run == true) {
            while (success == false && inputChoice < 0 || inputChoice > 3) {
                try {
                    inputChoice = user_input.nextInt();
                    if (swap == false) {
                        while (inputChoice < 1 || inputChoice > 2){
                            System.out.println("Please input 1 or 2.");
                            inputChoice = user_input.nextInt();
                        }
                    } 
                    if (swap == true) {
                        while (inputChoice < 0 || inputChoice > 3 || inputChoice == 2){
                            System.out.println("Please input 1, 3 or 0.");
                            inputChoice = user_input.nextInt();
                        }
                    }
                    success = true;
                }
                catch(InputMismatchException e) {
                    if (swap == false) {
                        System.out.println("Please input 1 or 2.");
                        user_input.next();
                        success = false;
                    }
                    else if (swap == true) {
                        System.out.println("Please input 1, 3 or 0.");
                        user_input.next();
                        success = false;
                    }
                }
                success = true;
            }
            success = false;
        }
        
        //Simulation mode
        else if (Main.run == false) {
            inputChoice = 1;
            cont = true;
            swap = false;
            newDeck.deck();
            PokerHand(newDeck);
        }
        
        //Switch for the user's input
        switch (inputChoice) {
            case 0:
                play = false;
                Main.success = false;
                Main.run = false;
                Main.user_int = -1;
                break;
            case 1:
                if (swap == false) {
                    cont = true;
                    swap = true;
                    break;
                }
                else {
                    cont = false;
                    swap = false;
                    newDeck.deck();
                    PokerHand(newDeck);
                    break;
                }
            case 2:
                swapCards();
                break;
            case 3: 
                if (swap == false) {
                    cont = true;
                    swap = false;
                    break;
                }
                else {
                    cont = false;
                    swap = false;
                    newDeck.deck();
                    PokerHand(newDeck);
                    pokerBet.betChoice();             
                    break;
                }
            default:
                break;
        }       
    }
    
//MAIN PLAYING METHOD
    public void play(){
        pokerBet.betChoice();
        System.out.println(player.name + "\'s" + " starting balance: " + player.getPlayerMoney());
        if (Main.run == false && player.getPlayerMoney() >= 100000.0) {
            System.out.println("\nPlease wait... \n");
        }
        Deck newDeck = new Deck();
        newDeck.deck();
        PokerHand(newDeck);
        
        while (play == true && end == false) {            
            
            pokerBank.add(pokerBet.bet);
            player.remove(pokerBet.bet);
            
            if (Main.run == true) {
                System.out.println("\nYour bet: " + pokerBet.bet);
            }
            
            displayHand();   
            factors();
            playAgain();
            
            //If you run out of money (Simulation)
            if (pokerBet.bet >= player.getPlayerMoney() && Main.run == false) {
                statistics();
                end = true;
                play = false;
            }
            //If you run out of money (Normal game)
            else if (pokerBet.bet >= player.getPlayerMoney()) {
                System.out.println("\n-- You can't bet anymore. Thanks for playing! \n");
                end = true;
                play = false;
            }   
        }
    }
    
//Stats of the simulation
    void statistics() {
        total = highCard+pair+twoPair+threeOfKind+straight+flush+fullHouse+straightFlush;
        System.out.println();
        System.out.println("-- Statistics:");
        System.out.printf("Your balance:      %-2.1f\n", player.getPlayerMoney());
        System.out.printf("Machine's balance: %-2.1f\n", pokerBank.getMoney());
        System.out.printf("High cards:        %-5d (%-2.2f%s\n", highCard, (highCard / total * 100), "%)");
        System.out.printf("Pairs:             %-5d (%-2.2f%s\n", pair, (pair / total * 100), "%)");
        System.out.printf("Two pairs:         %-5d (%-2.2f%s\n", twoPair, (twoPair / total * 100), "%)");
        System.out.printf("Three of a kinds:  %-5d (%-2.2f%s\n", threeOfKind, (threeOfKind / total * 100), "%)");
        System.out.printf("Straights:         %-5d (%-2.2f%s\n", straight, (straight / total * 100), "%)");
        System.out.printf("Flushes:           %-5d (%-2.2f%s\n", flush, (flush / total * 100), "%)");
        System.out.printf("Full houses:       %-5d (%-2.2f%s\n", fullHouse, (fullHouse / total * 100), "%)");
        System.out.printf("Four of a kinds:   %-5d (%-2.3f%s\n", fourOfKind, (fourOfKind / total * 100), "%)");
        System.out.printf("Straight flushes:  %-5d (%-2.3f%s\n", straightFlush, (straightFlush / total * 100), "%)");
        System.out.printf("Total played:      %-5d\n", ((int) total));   
    }
    //Comparing two hands 
    //THIS IS NOT NEEDED ON THIS PROJECT
    
    /*String compare(PokerHand hand){
        for(int i=0;i<6;i++){
            if (this.handvalue[i] > hand.handvalue[i])
                return "You win!";
            else if (this.handvalue[i] < hand.handvalue[i])
                return "You lose.";
        }
        return "It's a tie."; //If the hands are the same
    }*/
}