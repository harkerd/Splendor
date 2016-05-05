package drew.harker.splendorapp;

import org.junit.Test;

import java.util.List;

import drew.harker.splendorapp.model.pieces.Deck;
import drew.harker.splendorapp.model.pieces.GameBuilder;
import drew.harker.splendorapp.model.pieces.Noble;

import static org.junit.Assert.*;

public class GameBuilderTest
{
    @Test
    public void testNoblesSize() throws Exception
    {
        for(int i = GameBuilder.MIN_NUMBER_OF_PLAYERS; i <= GameBuilder.MAX_NUMBER_OF_PLAYERS; i++)
        {
            List<Noble> nobles = GameBuilder.initNobles(i);
            assertEquals(nobles.size(), i + 1);
        }
    }

    @Test
    public void testNoblesRandom() throws Exception
    {
        for(int i = GameBuilder.MIN_NUMBER_OF_PLAYERS; i <= GameBuilder.MAX_NUMBER_OF_PLAYERS; i++)
        {
            List<Noble> list1 = GameBuilder.initNobles(i);
            List<Noble> list2 = GameBuilder.initNobles(i);
            List<Noble> list3 = GameBuilder.initNobles(i);
            List<Noble> list4 = GameBuilder.initNobles(i);
            List<Noble> list5 = GameBuilder.initNobles(i);

            assertFalse(list1.equals(list2));
            assertFalse(list1.equals(list3));
            assertFalse(list1.equals(list4));
            assertFalse(list1.equals(list5));
            assertFalse(list2.equals(list3));
            assertFalse(list2.equals(list4));
            assertFalse(list2.equals(list5));
            assertFalse(list3.equals(list4));
            assertFalse(list3.equals(list5));
            assertFalse(list4.equals(list5));
        }
    }

    @Test
    public void testLevelOne() throws Exception
    {
        Deck deck1 = GameBuilder.initLevelOne();
        Deck deck2 = GameBuilder.initLevelOne();
        Deck deck3 = GameBuilder.initLevelOne();
        Deck deck4 = GameBuilder.initLevelOne();
        Deck deck5 = GameBuilder.initLevelOne();

        assertFalse(deck1.equals(deck2));
        assertFalse(deck1.equals(deck3));
        assertFalse(deck1.equals(deck4));
        assertFalse(deck1.equals(deck5));
        assertFalse(deck2.equals(deck3));
        assertFalse(deck2.equals(deck4));
        assertFalse(deck2.equals(deck5));
        assertFalse(deck3.equals(deck4));
        assertFalse(deck3.equals(deck5));
        assertFalse(deck4.equals(deck5));
    }

    @Test
    public void testLevelTwo() throws Exception
    {
        Deck deck1 = GameBuilder.initLevelTwo();
        Deck deck2 = GameBuilder.initLevelTwo();
        Deck deck3 = GameBuilder.initLevelTwo();
        Deck deck4 = GameBuilder.initLevelTwo();
        Deck deck5 = GameBuilder.initLevelTwo();

        assertFalse(deck1.equals(deck2));
        assertFalse(deck1.equals(deck3));
        assertFalse(deck1.equals(deck4));
        assertFalse(deck1.equals(deck5));
        assertFalse(deck2.equals(deck3));
        assertFalse(deck2.equals(deck4));
        assertFalse(deck2.equals(deck5));
        assertFalse(deck3.equals(deck4));
        assertFalse(deck3.equals(deck5));
        assertFalse(deck4.equals(deck5));
    }

    @Test
    public void testLevelThree() throws Exception
    {
        Deck deck1 = GameBuilder.initLevelThree();
        Deck deck2 = GameBuilder.initLevelThree();
        Deck deck3 = GameBuilder.initLevelThree();
        Deck deck4 = GameBuilder.initLevelThree();
        Deck deck5 = GameBuilder.initLevelThree();

        assertFalse(deck1.equals(deck2));
        assertFalse(deck1.equals(deck3));
        assertFalse(deck1.equals(deck4));
        assertFalse(deck1.equals(deck5));
        assertFalse(deck2.equals(deck3));
        assertFalse(deck2.equals(deck4));
        assertFalse(deck2.equals(deck5));
        assertFalse(deck3.equals(deck4));
        assertFalse(deck3.equals(deck5));
        assertFalse(deck4.equals(deck5));
    }
}