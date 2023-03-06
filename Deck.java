import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    
    private List<Card> deckCards = new ArrayList<Card>();
    private Random rng = new Random();

    public Deck() {  
        for (int i = 2; i < 15; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = new Card(i, j);
                deckCards.add(card);
            }
        }
    }

    // remove an array of cards
    public void removeCard(List<Card> visible) {
        for (int i = 0; i < visible.size(); i++) {
            deckCards.remove(visible.get(i));            
        }
    }

    // pull a random card
    public Card draw() {
        return deckCards.remove(rng.nextInt(deckCards.size()));
    }
}
