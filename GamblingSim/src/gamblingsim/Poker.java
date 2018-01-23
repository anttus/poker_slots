package gamblingsim;

public class Poker {
    
    Deck deck = new Deck();
    PokerHand hand = new PokerHand();
    
    public void playPoker() {
        deck.deck();
        hand.play();
    }
}


