package drew.harker.splendorapp.model.pieces;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import drew.harker.splendorapp.exceptions.InvalidActionException;
import drew.harker.splendorapp.exceptions.InvalidTypeException;
import drew.harker.splendorapp.model.Game;

public class GameBuilder
{


    public static Deck initLevelOne()
    {
        List<Card> cards = new LinkedList();
        try
        {
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 4, 0, 0, 0), 1));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 0, 3, 0, 0), 0));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 0, 0, 2, 1), 0));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 0, 2, 0, 2), 0));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 2, 2, 0, 1), 0));
            cards.add(new Card(Gem.DIAMOND, new GemList(3, 0, 1, 0, 1), 0));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 1, 1, 1, 1), 0));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 2, 1, 1, 1), 0));

            cards.add(new Card(Gem.EMERALD, new GemList(0, 0, 0, 0, 4), 1));
            cards.add(new Card(Gem.EMERALD, new GemList(0, 0, 0, 3, 0), 0));
            cards.add(new Card(Gem.EMERALD, new GemList(2, 0, 1, 0, 0), 0));
            cards.add(new Card(Gem.EMERALD, new GemList(0, 0, 2, 2, 0), 0));
            cards.add(new Card(Gem.EMERALD, new GemList(0, 0, 1, 2, 2), 0));
            cards.add(new Card(Gem.EMERALD, new GemList(1, 1, 3, 0, 0), 0));
            cards.add(new Card(Gem.EMERALD, new GemList(1, 0, 1, 1, 1), 0));
            cards.add(new Card(Gem.EMERALD, new GemList(1, 0, 1, 1, 2), 0));

            cards.add(new Card(Gem.SAPPHIRE, new GemList(0, 0, 0, 4, 0), 1));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(0, 0, 0, 0, 3), 0));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(1, 0, 0, 0, 2), 0));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(0, 2, 0, 0, 2), 0));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(1, 2, 0, 2, 0), 0));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(0, 3, 1, 1, 0), 0));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(1, 1, 0, 1, 1), 0));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(1, 1, 0, 2, 1), 0));

            cards.add(new Card(Gem.RUBY, new GemList(4, 0, 0, 0, 0), 1));
            cards.add(new Card(Gem.RUBY, new GemList(3, 0, 0, 0, 0), 0));
            cards.add(new Card(Gem.RUBY, new GemList(0, 1, 2, 0, 0), 0));
            cards.add(new Card(Gem.RUBY, new GemList(2, 0, 0, 2, 0), 0));
            cards.add(new Card(Gem.RUBY, new GemList(2, 1, 0, 0, 2), 0));
            cards.add(new Card(Gem.RUBY, new GemList(1, 0, 0, 1, 3), 0));
            cards.add(new Card(Gem.RUBY, new GemList(1, 1, 1, 0, 1), 0));
            cards.add(new Card(Gem.RUBY, new GemList(2, 1, 1, 0, 1), 0));

            cards.add(new Card(Gem.ONYX, new GemList(0, 0, 4, 0, 0), 1));
            cards.add(new Card(Gem.ONYX, new GemList(0, 3, 0, 0, 0), 0));
            cards.add(new Card(Gem.ONYX, new GemList(0, 2, 0, 1, 0), 0));
            cards.add(new Card(Gem.ONYX, new GemList(2, 2, 0, 0, 0), 0));
            cards.add(new Card(Gem.ONYX, new GemList(2, 0, 2, 1, 0), 0));
            cards.add(new Card(Gem.ONYX, new GemList(0, 1, 0, 3, 1), 0));
            cards.add(new Card(Gem.ONYX, new GemList(1, 1, 1, 1, 0), 0));
            cards.add(new Card(Gem.ONYX, new GemList(1, 1, 2, 1, 0), 0));

            return new Deck(cards);
        }
        catch (InvalidTypeException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Deck initLevelTwo()
    {
        List<Card> cards = new LinkedList();
        try
        {
            cards.add(new Card(Gem.DIAMOND, new GemList(6, 0, 0, 0, 0), 3));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 0, 0, 5, 0), 2));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 0, 0, 5, 3), 2));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 1, 0, 4, 2), 2));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 3, 0, 2, 2), 1));
            cards.add(new Card(Gem.DIAMOND, new GemList(2, 0, 3, 3, 0), 1));

            cards.add(new Card(Gem.EMERALD, new GemList(0, 6, 0, 0, 0), 3));
            cards.add(new Card(Gem.EMERALD, new GemList(0, 5, 0, 0, 0), 2));
            cards.add(new Card(Gem.EMERALD, new GemList(0, 3, 5, 0, 0), 2));
            cards.add(new Card(Gem.EMERALD, new GemList(4, 0, 2, 0, 1), 2));
            cards.add(new Card(Gem.EMERALD, new GemList(2, 0, 3, 0, 2), 1));
            cards.add(new Card(Gem.EMERALD, new GemList(3, 2, 0, 3, 0), 1));

            cards.add(new Card(Gem.SAPPHIRE, new GemList(0, 0, 6, 0, 0), 3));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(0, 0, 5, 0, 0), 2));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(5, 0, 3, 0, 0), 2));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(2, 0, 0, 1, 4), 2));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(0, 2, 2, 3, 0), 1));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(0, 3, 2, 0, 3), 1));

            cards.add(new Card(Gem.RUBY, new GemList(0, 0, 0, 6, 0), 3));
            cards.add(new Card(Gem.RUBY, new GemList(0, 0, 0, 0, 5), 2));
            cards.add(new Card(Gem.RUBY, new GemList(3, 0, 0, 0, 5), 2));
            cards.add(new Card(Gem.RUBY, new GemList(1, 2, 4, 0, 0), 2));
            cards.add(new Card(Gem.RUBY, new GemList(2, 0, 0, 2, 3), 1));
            cards.add(new Card(Gem.RUBY, new GemList(0, 0, 3, 2, 3), 1));

            cards.add(new Card(Gem.ONYX, new GemList(0, 0, 0, 0, 6), 3));
            cards.add(new Card(Gem.ONYX, new GemList(5, 0, 0, 0, 0), 2));
            cards.add(new Card(Gem.ONYX, new GemList(0, 5, 0, 3, 0), 2));
            cards.add(new Card(Gem.ONYX, new GemList(0, 4, 1, 2, 0), 2));
            cards.add(new Card(Gem.ONYX, new GemList(3, 2, 2, 0, 0), 1));
            cards.add(new Card(Gem.ONYX, new GemList(3, 3, 0, 0, 2), 1));

            return new Deck(cards);
        }
        catch (InvalidTypeException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Deck initLevelThree()
    {
        List<Card> cards = new LinkedList();
        try
        {
            cards.add(new Card(Gem.DIAMOND, new GemList(3, 0, 0, 0, 7), 5));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 0, 0, 0, 7), 4));
            cards.add(new Card(Gem.DIAMOND, new GemList(3, 0, 0, 3, 6), 4));
            cards.add(new Card(Gem.DIAMOND, new GemList(0, 3, 3, 5, 3), 3));

            cards.add(new Card(Gem.EMERALD, new GemList(0, 3, 7, 0, 0), 5));
            cards.add(new Card(Gem.EMERALD, new GemList(0, 0, 7, 0, 0), 4));
            cards.add(new Card(Gem.EMERALD, new GemList(3, 3, 6, 0, 0), 4));
            cards.add(new Card(Gem.EMERALD, new GemList(5, 0, 3, 3, 3), 3));

            cards.add(new Card(Gem.SAPPHIRE, new GemList(7, 0, 3, 0, 0), 5));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(7, 0, 0, 0, 0), 4));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(6, 0, 3, 0, 3), 4));
            cards.add(new Card(Gem.SAPPHIRE, new GemList(3, 3, 0, 3, 5), 3));

            cards.add(new Card(Gem.RUBY, new GemList(0, 7, 0, 3, 0), 5));
            cards.add(new Card(Gem.RUBY, new GemList(0, 7, 0, 0, 0), 4));
            cards.add(new Card(Gem.RUBY, new GemList(0, 6, 3, 3, 0), 4));
            cards.add(new Card(Gem.RUBY, new GemList(3, 3, 5, 0, 3), 3));

            cards.add(new Card(Gem.ONYX, new GemList(0, 0, 0, 7, 3), 5));
            cards.add(new Card(Gem.ONYX, new GemList(0, 0, 0, 7, 0), 4));
            cards.add(new Card(Gem.ONYX, new GemList(0, 3, 0, 6, 3), 4));
            cards.add(new Card(Gem.ONYX, new GemList(3, 5, 3, 3, 0), 3));

            return new Deck(cards);
        }
        catch (InvalidTypeException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    private static final Random r = new Random(System.currentTimeMillis());
    public static List<Noble> initNobles(int numberOfPlayers) throws InvalidActionException
    {
        if(numberOfPlayers > Game.MAX_NUMBER_OF_PLAYERS || numberOfPlayers < Game.MIN_NUMBER_OF_PLAYERS)
        {
            throw new InvalidActionException();
        }
        else
        {
            List<Noble> nobles = new LinkedList();

            nobles.add(new Noble(new GemList(4, 0, 4, 0, 0), 3));
            nobles.add(new Noble(new GemList(4, 0, 0, 0, 4), 3));
            nobles.add(new Noble(new GemList(0, 4, 0, 4, 0), 3));
            nobles.add(new Noble(new GemList(0, 0, 0, 4, 4), 3));
            nobles.add(new Noble(new GemList(0, 4, 4, 0, 0), 3));

            nobles.add(new Noble(new GemList(3, 0, 0, 3, 3), 3));
            nobles.add(new Noble(new GemList(0, 3, 0, 3, 3), 3));
            nobles.add(new Noble(new GemList(3, 0, 3, 0, 3), 3));
            nobles.add(new Noble(new GemList(3, 3, 3, 0, 0), 3));
            nobles.add(new Noble(new GemList(0, 3, 3, 3, 0), 3));

            List<Noble> toDisplay = new LinkedList<>();
            for (int i = 0; i < numberOfPlayers + 1; i++)
            {
                int index = r.nextInt(nobles.size());
                toDisplay.add(nobles.remove(index));
            }

            return toDisplay;
        }
    }
}
