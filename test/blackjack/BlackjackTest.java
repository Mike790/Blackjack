package blackjack;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;

public class BlackjackTest {

    @Test
    public void testBustedPlayerGood() {
        Player player = new Player("Test Player");
        player.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.KING));
        player.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.QUEEN));
        player.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.TEN));
        boolean expResult = true;
        boolean result = Blackjack.busted(player);
        assertEquals(expResult, result);
    }

    @Test
    public void testBustedPlayerBad() {
        Player player = new Player("Test Player");
        player.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.KING));
        player.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.QUEEN));
        boolean expResult = false;
        boolean result = Blackjack.busted(player);
        assertEquals(expResult, result);
    }

    @Test
    public void testBustedPlayerBoundary() {
        Player player = new Player("Test Player");
        player.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.KING));
        player.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.NINE));
        player.getHand().addCard(new Card(Card.Suit.HEARTS, Card.Rank.TWO));
        boolean expResult = false;
        boolean result = Blackjack.busted(player);
        assertEquals(expResult, result);
    }

    @Test
    public void testBustedDealerGood() {
        Dealer dealer = new Dealer("Test Dealer", new Deck());
        dealer.getHand().addCard(new Card(Card.Suit.SPADES, Card.Rank.KING));
        dealer.getHand().addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
        dealer.getHand().addCard(new Card(Card.Suit.HEARTS, Card.Rank.FOUR));
        boolean expResult = true;
        boolean result = Blackjack.busted(dealer);
        assertEquals(expResult, result);
    }

    @Test
    public void testBustedDealerBad() {
        Dealer dealer = new Dealer("Test Dealer", new Deck());
        dealer.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.KING));
        dealer.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.NINE));
        boolean expResult = false;
        boolean result = Blackjack.busted(dealer);
        assertEquals(expResult, result);
    }

    @Test
    public void testBustedDealerBoundary() {
        Dealer dealer = new Dealer("Test Dealer", new Deck());
        dealer.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.KING));
        dealer.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.QUEEN));
        dealer.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        boolean expResult = false;
        boolean result = Blackjack.busted(dealer);
        assertEquals(expResult, result);
    }
}
