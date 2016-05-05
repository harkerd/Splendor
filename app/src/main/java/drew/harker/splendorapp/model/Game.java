package drew.harker.splendorapp.model;

import java.util.List;

import drew.harker.splendorapp.model.exceptions.CardDoesNotExistException;
import drew.harker.splendorapp.model.exceptions.InvalidActionException;
import drew.harker.splendorapp.model.exceptions.InvalidTypeException;
import drew.harker.splendorapp.model.pieces.Card;
import drew.harker.splendorapp.model.pieces.Deck;
import drew.harker.splendorapp.model.pieces.GameBuilder;
import drew.harker.splendorapp.model.pieces.GemList;
import drew.harker.splendorapp.model.pieces.Location;
import drew.harker.splendorapp.model.pieces.Noble;
import drew.harker.splendorapp.model.pieces.TokenList;

public class Game
{
    private List<User> users; //The users index corresponds to the player index.
    private final int victoryPointLimit = 15;
    private int turnIndex = 0;

    private List<Player> players;
    private TokenList bank;
    private List<Noble> nobles;

    private Deck levelThree = GameBuilder.initLevelThree();
    private Deck levelTwo = GameBuilder.initLevelTwo();
    private Deck levelOne = GameBuilder.initLevelOne();

    public Player endTurn()
    {
        turnIndex++;
        if(turnIndex == players.size())
        {
            turnIndex = 0;
        }
        return players.get(turnIndex);
    }

    public boolean canReserveCard(Location deckIndex, int cardIndex) throws InvalidActionException, InvalidTypeException
    {
        if(deckIndex == Location.RESERVE)
        {
            return false;
        }

        return get(deckIndex).doesCardExist(cardIndex);
    }

    public Card getCard(Location deckIndex, int cardIndex) throws InvalidActionException, InvalidTypeException, CardDoesNotExistException
    {
        return get(deckIndex).takeCard(cardIndex);
    }

    public GemList getCardPrice(Location deckIndex, int cardIndex) throws InvalidActionException, InvalidTypeException, CardDoesNotExistException
    {
        return get(deckIndex).getCardPrice(cardIndex);
    }

    private Deck get(Location deck) throws InvalidActionException, InvalidTypeException
    {
        Deck result;
        switch (deck)
        {
            case DECK_ONE: result = levelOne; break;
            case DECK_TWO: result = levelTwo; break;
            case DECK_THREE: result = levelThree; break;
            case RESERVE: throw new InvalidActionException();
            default: throw new InvalidTypeException();
        }
        return result;
    }

    public int getPlayerCount()
    {
        return players.size();
    }
}