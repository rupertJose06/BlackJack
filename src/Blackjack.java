import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    private Deck deck;

    public ArrayList<Card> player;
    public ArrayList<Card> dealer;
    Scanner kb;

    public Blackjack() {
        kb = new Scanner(System.in);
        deck = new Deck();
        player = new ArrayList<>();
        dealer = new ArrayList<>();
    }

    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.run();
    }

    public void printDealer() {
        for (int i = 0; i < dealer.size(); i++) {
            System.out.print(dealer.get(i) + " ");
        }
    }

    public void printPlayer() {
        for (int i = 0; i < player.size(); i++) {
            System.out.print(player.get(i) + " ");
        }
    }

    public int getNumber() {
        int returnedValue = 0;
        for (Card card: player) {
            returnedValue += card.getValue();
        }
        return returnedValue;
    }

    public int getNumberDealer() {
        int returnedValue = 0;
        for (Card card: dealer) {
            returnedValue += card.getValue();
        }
        return returnedValue;
    }

    public void checkResult() {
        if (getNumber() > 21) {
            System.out.println("You've busted!");
        } else if (getNumber() < getNumberDealer()) {
            System.out.println("You've lost!");
        } else if (getNumberDealer() > 21) {
            System.out.println("You've won!");
        } else if (getNumber() > getNumberDealer()) {
            System.out.println("You've won!");
        }
    }

    private void run() {
        boolean playAgain = true;
        deck.shuffle();
        while (playAgain) {
            playRound();
            if (getNumber() != 21 && getNumberDealer() != 21) {
                hitOrStand();
            }
            if (check == false) {
                checkResult();
            }

            System.out.println("Do you want to play again? (yes/no)");
            String response = kb.nextLine();
            playAgain = response.equalsIgnoreCase("yes");
            resetGame();
        }
        kb.close();
    }

    boolean check = false;

    public void hitOrStand() {
        while (true) {
            System.out.println("Hit or Stand?");
            String response = kb.nextLine().trim().toLowerCase();
            if (response.equals("hit")) {
                player.add(deck.getCard());
                System.out.print("Player's Hand: ");
                printPlayer();
                System.out.println();
                if (calculateScore(player) > 21) {
                    System.out.println("Bust! You lose.");
                    check = true;
                    return;
                }
            } else if (response.equals("stand")) {
                break;
            }
        }
    }

    private void playRound() {
        // deal cards
        player.add(deck.getCard());
        dealer.add(deck.getCard());
        player.add(deck.getCard());
        dealer.add(deck.getCard());

        // Print initial hands
        System.out.print("Player's Hand: ");
        printPlayer();
        System.out.println();
        System.out.println("Dealer's Hand: " + dealer.get(0) + " ?");

        // Determine the winner
    }

    private int calculateScore(ArrayList<Card> player) {
        int score = 0;
        int numAces = 0;
        for (Card card : player) {
            int value = card.getValue();
            if (value == 11) {
                numAces++;
            }
            score += value;
        }
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }
        return score;
    }

    private void resetGame() {
        deck.reset(player, dealer);
    }
}