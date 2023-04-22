package blackjack;

/**
 *
 * @author Michael Sousa
 * @author Ran Ren
 */

public class Blackjack {

    private static final int BLACKJACK_VALUE = 21;

    public static void main(String[] args) {
        IOHandler iohandle = IOHandler.getInstance();
        boolean playAgain = true;
        while (playAgain) {
            Deck deck = new Deck();
            Dealer dealer = new Dealer("Dealer", deck);
            System.out.println("Enter your name!");
            String playerName = iohandle.getString(System.in);
            Player player = new Player(playerName);

            dealer.dealInitialCards(player, dealer);

            System.out.println("Welcome to Blackjack!");
            System.out.println(playerName + "'s Hand: " + player.getHand() + " (Total Value: " + player.getHand().getHandValue() + ")");
            System.out.println("Dealer's face card: " + dealer.getHand().firstCard());

            // Player's turn
            boolean playerBusted = false;
            while (true) {
                System.out.println("1. Hit");
                System.out.println("2. Stand");
                System.out.print("Enter your choice: ");
                String[] options = {"1", "2", "hit", "stand"};
                String choice = iohandle.selectOption(System.in, options);
                if (choice.equals("1") || choice.equals("hit")) {
                    dealer.dealCard(player);
                    System.out.println(playerName + " 's Hand: " + player.getHand() + " (Total Value: " + player.getHand().getHandValue() + ")");
                    if (busted(player)) {
                        System.out.println(playerName + " busts! Dealer wins!");
                        playerBusted = true;
                        break;
                    }
                } else if (choice.equals("2") || choice.equals("stand")) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please choose again.");
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
            System.out.println("Do you want to play again?");
            System.out.println("1. yes");
            System.out.println("2. no");
            String[] choices = {"1", "y", "yes", "2", "n", "no"};
            String playAgainChoice = iohandle.selectOption(System.in, choices);
            if (playAgainChoice.equals("1") || playAgainChoice.equals("y") || playAgainChoice.equals("yes")) {
                playAgain = true;
            } else if (playAgainChoice.equals("2") || playAgainChoice.equals("n") || playAgainChoice.equals("no")) {
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
