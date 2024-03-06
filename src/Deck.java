import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
        for (int suit = 0; suit <= 3; suit++) {
            for (int value = 2; value <= 14; value++) {
                deck.add(new Card(suit, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card getCard() {
        if (!deck.isEmpty()) {
            return deck.remove(deck.size() - 1);
        } else {
            return null;
        }
    }

    public void reset(ArrayList<Card> player, ArrayList<Card> dealer) {
        deck.addAll(player);
        deck.addAll(dealer);
        player.clear();
        dealer.clear();
        shuffle();
    }
}