package drew.harker.splendorapp;

import org.junit.Test;
import java.util.LinkedList;
import java.util.List;

import drew.harker.splendorapp.model.pieces.Card;
import drew.harker.splendorapp.model.pieces.Deck;
import drew.harker.splendorapp.model.pieces.Gem;
import drew.harker.splendorapp.model.pieces.GemList;

import static org.junit.Assert.*;

public class DeckTest
{
    @Test
    public void testIsEmpty() throws Exception
    {
        Deck deck = new Deck(new LinkedList<Card>());
        assertTrue(deck.isEmpty());

        List<Card> cards = new LinkedList<>();
        Card card = new Card(Gem.DIAMOND, new GemList(), 0);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        cards.add(card);
        deck = new Deck(cards);
        assertFalse(deck.isEmpty());
    }

    @Test
    public void testTakeCard() throws Exception
    {
        List<Card> cards = new LinkedList<>();
        Card card = new Card(Gem.DIAMOND, new GemList(), 0);
        cards.add(card);
        Deck deck = new Deck(cards);

        assertTrue(deck.isEmpty());
        assertFalse(deck.doesCardExist(Deck.TOP_OF_DECK));
        assertFalse(deck.doesCardExist(Deck.CARD_VISIBLE_TWO));
        assertFalse(deck.doesCardExist(Deck.CARD_VISIBLE_THREE));
        assertFalse(deck.doesCardExist(Deck.CARD_VISIBLE_FOUR));

        assertTrue(deck.doesCardExist(Deck.CARD_VISIBLE_ONE));
        assertEquals(card, deck.takeCard(Deck.CARD_VISIBLE_ONE));
        assertFalse(deck.doesCardExist(Deck.CARD_VISIBLE_ONE));
    }

    @Test
    public void testEquals_duplicate() throws Exception
    {
        List<Card> cards1 = new LinkedList<>();
        Card card1 = new Card(Gem.DIAMOND, new GemList(), 0);
        cards1.add(card1);
        Deck deck1 = new Deck(cards1);

        List<Card> cards2 = new LinkedList<>();
        Card card2 = new Card(Gem.DIAMOND, new GemList(), 0);
        cards2.add(card2);
        Deck deck2 = new Deck(cards2);

        assertEquals(deck1, deck2);

    }

    @Test
    public void testEquals_empty() throws Exception
    {
        List<Card> cards1 = new LinkedList<>();
        Deck deck1 = new Deck(cards1);

        List<Card> cards2 = new LinkedList<>();
        Deck deck2 = new Deck(cards2);

        assertEquals(deck1, deck2);
    }

    @Test
    public void testEquals_notEqual() throws Exception
    {
        List<Card> cards1 = new LinkedList<>();
        Card card1 = new Card(Gem.EMERALD, new GemList(), 0);
        cards1.add(card1);
        Deck deck1 = new Deck(cards1);

        List<Card> cards2 = new LinkedList<>();
        Card card2 = new Card(Gem.DIAMOND, new GemList(), 0);
        cards2.add(card2);
        Deck deck2 = new Deck(cards2);

        assertNotEquals(deck1, deck2);
    }
}