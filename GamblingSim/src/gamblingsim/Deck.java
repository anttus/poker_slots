package gamblingsim;
import java.util.Random;
import java.util.ArrayList;

public class Deck {
    
    public ArrayList<Card> cards; 
    
    public void deck(){
        int index1, index2;
        cards = new ArrayList(); 
        Random randGen = new Random();
        Card temp;
        
        //Adding all of the cards to the 'cards' list
        for (int i=0;i<4;i++) {
            for (int j=0;j<13;j++) {
                cards.add(new Card(i,j));
            }
        }
        
        //Shuffling the deck
        for (int i=0;i<100;i++) {
            index1 = randGen.nextInt(cards.size()-1);
            index2 = randGen.nextInt(cards.size()-1);
            
            temp = cards.get(index2);
            cards.set(index2, cards.get(index1));
            cards.set(index1, temp); 
        }
    }
    
    //Removes the drawn card from the deck
    public Card draw() {
        return cards.remove(0);
    }
    /*public int getTotalCards(){
        return cards.size();
    }*/           
}
