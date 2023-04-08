package blackjack;

/**
 *
 * @author Michael Sousa
 */
import java.util.ArrayList;
import java.util.List;

class Hand {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getHandValue() {
        int handValue = 0;
        boolean hasAce = false;

        for (Card card : cards) {
            int rankValue = Math.min(10, card.getRank().ordinal() + 1); // Ace has rankValue of 11, but can be reduced to 1 if a bust would otherwise occur
            handValue += rankValue;

            if (card.getRank() == Card.Rank.ACE) {
                hasAce = true;
            }
        }

        if (hasAce && handValue > 21) {
            handValue -= 10; // Reduce Ace rankValue from 11 to 1
        }

        return handValue;
    }

    public void clear() {
        cards.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString()).append(", ");
        }
        if (!cards.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }
}
