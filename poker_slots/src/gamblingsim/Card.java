package gamblingsim;

public class Card {
    private static final String suits[] = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String cards[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    int card, suit;
    
    public static String suitAsString(int suit) {
        return cards[suit];
    }

    public Card(int suit, int card){
        this.card=card;
        this.suit=suit;
    }

    public @Override String toString(){
        return cards[card] + " of " + suits[suit];
    }

    public int getCard() {
         return card;
    }

    public int getSuit() {
        return suit;
    }

    public void setCard(int card) {
        this.card = card;
    }
    
}   
