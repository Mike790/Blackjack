package blackjack;

/**
 *
 * @author Michael Sousa
 */
class Dealer extends Player {

    private final Deck deck;

    public Dealer(String name, Deck deck) {
        super(name);
        this.deck = deck;
    }

    public void dealCard(Player player) {
        Card card = deck.drawCard();
        if (card != null) {
            player.getHand().addCard(card);
        }
    }

    public void dealInitialCards(Player player1, Player player2) {
        for (int i = 0; i < 2; i++) {
            dealCard(player1);
            dealCard(player2);
        }
    }

    public void playTurn() {
        while (getHand().getHandValue() < 17) {
            dealCard(this);
        }
    }

    public void resetDeck() {
        deck.shuffle();
    }
}
