public class Card {

    private int suit;
    private int value;

    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        if (value < 11) {
            return value;
        } else if (value == 14) {
            return 11;
        } else {
            return 10;
        }
    }

    public String toString() {
        String[] suits = {"♥", "♣", "♦", "♠"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        return values[this.value-2] + " " + suits[this.suit];
    }
}
