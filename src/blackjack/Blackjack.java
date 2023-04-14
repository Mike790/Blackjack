package blackjack;

/**
 *
 * @author Michael Sousa
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Blackjack {

    private static final int BLACKJACK_VALUE = 21;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            Deck deck = new Deck();
            Dealer dealer = new Dealer("Dealer", deck);
            System.out.println("Enter your name!");
            String playerName = input.nextLine();
            Player player = new Player(playerName);

            dealer.dealInitialCards(player, dealer);

            System.out.println("Welcome to Blackjack!");
            System.out.println(playerName + "'s Hand: " + player.getHand() + " (Total Value: " + player.getHand().getHandValue() + ")");
            System.out.println("Dealer's face card: " + dealer.getHand().firstCard());

            // Player's turn
            boolean playerBusted = false;
            while (true) {
                try {
                    System.out.println("1. Hit");
                    System.out.println("2. Stand");
                    System.out.print("Enter your choice: ");
                    int choice = input.nextInt();
                    if (choice == 1) {
                        dealer.dealCard(player);
                        System.out.println(playerName + " 's Hand: " + player.getHand() + " (Total Value: " + player.getHand().getHandValue() + ")");
                        if (busted(player)) {
                            System.out.println(playerName + " busts! Dealer wins!");
                            playerBusted = true;
                            break;
                        }
                    } else if (choice == 2) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please choose again.");
                    }
                } catch (InputMismatchException exception) {
                    System.out.println("Invalid input type. Please enter 1 or 2.");
                    input.next();
                }
            }

            // Dealer's turn
            if (!playerBusted) {
                dealer.playTurn();
                System.out.println("Dealer's Hand: " + dealer.getHand() + " (Total Value: " + dealer.getHand().getHandValue() + ")");
                if (busted(dealer)) {
                    System.out.println("Dealer busts! " + playerName + " wins!");
                } else {
                    // Determine winner
                    int playerHandValue = player.getHand().getHandValue();
                    int dealerHandValue = dealer.getHand().getHandValue();
                    if (playerHandValue > dealerHandValue) {
                        System.out.println(playerName + " wins!");
                    } else if (dealerHandValue > playerHandValue) {
                        System.out.println("Dealer wins!");
                    } else {
                        System.out.println("It's a tie! Dealer wins on tie.");
                    }
                }
            }

            // Play again
            System.out.println("Do you want to play again? (yes/no)");
            input.nextLine();
            String playAgainChoice = input.nextLine();
            if (playAgainChoice.equalsIgnoreCase("yes")) {
                playAgain = true;
            } else {
                playAgain = false;
            }

        }
    }

    static boolean busted(Player player) {
        return player.getHand().getHandValue() > BLACKJACK_VALUE;
    }

    static boolean busted(Dealer dealer) {
        return dealer.getHand().getHandValue() > BLACKJACK_VALUE;
    }

}
