package drew.harker.splendorapp.controller;

import drew.harker.splendorapp.model.Game;
import drew.harker.splendorapp.model.StaticCurrentGameAccess;
import drew.harker.splendorapp.exceptions.CanPickMultipleNoblesException;
import drew.harker.splendorapp.exceptions.CardDoesNotExistException;
import drew.harker.splendorapp.exceptions.InsufficientResourcesException;
import drew.harker.splendorapp.exceptions.InvalidActionException;
import drew.harker.splendorapp.exceptions.InvalidTypeException;
import drew.harker.splendorapp.model.pieces.Location;
import drew.harker.splendorapp.model.pieces.Token;
import drew.harker.splendorapp.view.GameView;

public class GameController
{
    private Game currentGame;
    private GameView view;

    public GameController(Game game, GameView view)
    {
        currentGame = game;
        this.view = view;
    }

    //TODO: Fix this design. Now that I am tying it into the view I can see that it does not work well.
    /*protected void takeTwoSameTokens(Token token) throws InvalidActionException
    {
        if(!canTakeTwoSameTokens(token))
        {
            throw new InvalidActionException();
        }
        try
        {
            StaticCurrentGameAccess.takeTwoSameTokens(token);
        }
        catch (InvalidTypeException e)
        {
            throw new InvalidActionException();
        }
        catch (InsufficientResourcesException e)
        {
            throw new InvalidActionException();
        }

    }

    protected boolean canTakeTwoSameTokens(Token token)
    {
        return StaticCurrentGameAccess.canTakeTwoSameTokens(token);
    }

    protected void takeThreeDifferentTokens(Token token1, Token token2, Token token3) throws InvalidActionException
    {
        if(!canTakeThreeDifferentTokens(token1, token2, token3))
        {
            throw new InvalidActionException();
        }
        try
        {
            StaticCurrentGameAccess.takeThreeDifferentTokens(token1, token2, token3);
        }
        catch (InsufficientResourcesException e)
        {
            throw new InvalidActionException();
        }
        catch (InvalidTypeException e)
        {
            throw new InvalidActionException();
        }
    }

    protected boolean canTakeThreeDifferentTokens(Token token1, Token token2, Token token3)
    {
        return StaticCurrentGameAccess.canTakeThreeDifferentTokens(token1, token2, token3);
    }

    protected void buyCard(Location deckIndex, int cardIndex) throws InvalidActionException
    {
        if(!canBuyCard(deckIndex, cardIndex))
        {
            throw new InvalidActionException();
        }
        try
        {
            StaticCurrentGameAccess.buyCard(deckIndex, cardIndex);
        }
        catch (CardDoesNotExistException e)
        {
            throw new InvalidActionException();
        }
        catch (InvalidTypeException e)
        {
            throw new InvalidActionException();
        }
    }

    protected boolean canBuyCard(Location deckIndex, int cardIndex)
    {
        return StaticCurrentGameAccess.canBuyCard(deckIndex, cardIndex);
    }

    protected void reserveCard(Location deckIndex, int cardIndex) throws InvalidActionException
    {
        if(!canReserveCard(deckIndex, cardIndex))
        {
            throw new InvalidActionException();
        }
        try
        {
            StaticCurrentGameAccess.reserveCard(deckIndex, cardIndex);
        }
        catch (CardDoesNotExistException e)
        {
            throw new InvalidActionException();
        }
        catch (InvalidTypeException e)
        {
            throw new InvalidActionException();
        }
    }

    protected boolean canReserveCard(Location deckIndex, int cardIndex)
    {
        return StaticCurrentGameAccess.canReserveCard(deckIndex, cardIndex);
    }

    protected void pickNoble(int nobleIndex) throws InvalidActionException
    {
        if(!canPickNoble(nobleIndex))
        {
            throw new InvalidActionException();
        }
        StaticCurrentGameAccess.pickNoble(nobleIndex);
    }

    protected boolean canPickNoble(int nobleIndex)
    {
        return StaticCurrentGameAccess.canPickNoble(nobleIndex);
    }

    protected void checkNobles() throws CanPickMultipleNoblesException, InvalidActionException
    {
        StaticCurrentGameAccess.checkNobles();
    }

    protected void recalculate()
    {
        StaticCurrentGameAccess.recalculate();
    }

    protected void endTurn()
    {
        StaticCurrentGameAccess.endTurn();
    }*/
}
