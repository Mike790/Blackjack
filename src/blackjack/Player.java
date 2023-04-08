package blackjack;

/**
 *
 * @aMichael Sousa
 */
class Player {

    private final String name;
    protected final Hand hand = new Hand();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void clearHand() {
        hand.clear();
    }
}
