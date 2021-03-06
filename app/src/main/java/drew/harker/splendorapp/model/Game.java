package drew.harker.splendorapp.model;

import java.util.ArrayList;
import java.util.List;

import drew.harker.splendorapp.exceptions.CardDoesNotExistException;
import drew.harker.splendorapp.exceptions.InvalidActionException;
import drew.harker.splendorapp.exceptions.InvalidTypeException;
import drew.harker.splendorapp.model.pieces.Card;
import drew.harker.splendorapp.model.pieces.Deck;
import drew.harker.splendorapp.model.pieces.GameBuilder;
import drew.harker.splendorapp.model.pieces.GemList;
import drew.harker.splendorapp.model.pieces.Location;
import drew.harker.splendorapp.model.pieces.Noble;
import drew.harker.splendorapp.model.pieces.TokenList;

public class Game
{
    public static final int MAX_NUMBER_OF_PLAYERS = 4;
    public static final int MIN_NUMBER_OF_PLAYERS = 2;
    public static final int MAX_NUMBER_OF_RESERVED_CARDS = 3;

    private List<User> users; //The users index corresponds to the player index.
    private final int victoryPointLimit = 15;
    private int turnIndex = -1;

    private List<Player> players;
    private TokenList bank;
    private List<Noble> nobles;

    private Deck levelThree;
    private Deck levelTwo;
    private Deck levelOne;

    public static Game newGame(int numberOfPlayers) throws InvalidActionException
    {
        Game game = new Game();
        game.nobles = GameBuilder.initNobles(numberOfPlayers);

        game.players = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++)
        {
            game.players.add(new Player("Player " + String.valueOf(i + 1)));
        }

        int tokenMax = -1;
        if(numberOfPlayers == 4)
        {
            tokenMax = 7;
        }
        else if(numberOfPlayers == 3)
        {
            tokenMax = 5;
        }
        else if(numberOfPlayers == 2)
        {
            tokenMax = 4;
        }
        game.bank = new TokenList(tokenMax);

        game.levelThree = GameBuilder.initLevelThree();
        game.levelTwo = GameBuilder.initLevelTwo();
        game.levelOne = GameBuilder.initLevelOne();

        game.turnIndex++;
        return game;
    }

    private Game() {}

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

    public Card getCardInfo(Location deckIndex, int cardIndex) throws InvalidActionException, InvalidTypeException, CardDoesNotExistException
    {
        return get(deckIndex).getCardInfo(cardIndex);
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

    public List<Noble> getNobles()
    {
        return nobles;
    }

    public TokenList getBank()
    {
        return bank;
    }

    public Player getCurrentPlayer()
    {
        return players.get(turnIndex);
    }

    public Player getPlayer(int index)
    {
        return players.get(index);
    }

    public int getNumberOfPlayers()
    {
        return players.size();
    }
}
