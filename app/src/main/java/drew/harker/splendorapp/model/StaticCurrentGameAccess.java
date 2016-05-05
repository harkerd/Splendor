package drew.harker.splendorapp.model;

import java.util.List;

import drew.harker.splendorapp.model.exceptions.CanPickMultipleNoblesException;
import drew.harker.splendorapp.model.exceptions.CardDoesNotExistException;
import drew.harker.splendorapp.model.exceptions.InsufficientResourcesException;
import drew.harker.splendorapp.model.exceptions.InvalidActionException;
import drew.harker.splendorapp.model.exceptions.InvalidTypeException;
import drew.harker.splendorapp.model.pieces.Card;
import drew.harker.splendorapp.model.pieces.GemList;
import drew.harker.splendorapp.model.pieces.Location;
import drew.harker.splendorapp.model.pieces.Noble;
import drew.harker.splendorapp.model.pieces.Token;
import drew.harker.splendorapp.model.pieces.TokenList;

public class StaticCurrentGameAccess
{
    private static Game currentGame;
    private static Player currentPlayer;
    private static TokenList bank;
    private static List<Noble> nobles;

    public static void init(Game game)
    {
        currentGame = game;

        int tokenMax = -1;
        if(currentGame.getPlayerCount() == 4)
        {
            tokenMax = 7;
        }
        else if(currentGame.getPlayerCount() == 3)
        {
            tokenMax = 5;
        }
        else if(currentGame.getPlayerCount() == 2)
        {
            tokenMax = 4;
        }

        bank = new TokenList(tokenMax);
    }

    public static void takeTwoSameTokens(Token token) throws InvalidTypeException, InsufficientResourcesException
    {
        bank.decrement(token);
        bank.decrement(token);

        currentPlayer.increment(token);
        currentPlayer.increment(token);
    }

    public static boolean canTakeTwoSameTokens(Token token)
    {
        try
        {
            if(bank.get(token) >= 4)
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (InvalidTypeException e)
        {
            return false;
        }
    }

    public static void takeThreeDifferentTokens(Token token1, Token token2, Token token3) throws InsufficientResourcesException, InvalidTypeException
    {
        bank.decrement(token1);
        bank.decrement(token2);
        bank.decrement(token3);

        currentPlayer.increment(token1);
        currentPlayer.increment(token2);
        currentPlayer.increment(token3);
    }

    public static boolean canTakeThreeDifferentTokens(Token token1, Token token2, Token token3)
    {
        if(token1 == token2 || token1 == token3 || token2 == token3)
        {
            return false;
        }
        else
        {
            try
            {
                if (bank.get(token1) > 0 && bank.get(token2) > 0 && bank.get(token3) > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            } catch (InvalidTypeException e)
            {
                return false;
            }
        }
    }

    public static void buyCard(Location deckIndex, int cardIndex) throws CardDoesNotExistException, InvalidActionException, InvalidTypeException
    {
        GemList cardPrice;
        if(deckIndex == Location.RESERVE)
        {
            cardPrice = currentPlayer.getReservedCardPrice(cardIndex);
        }
        else
        {
            cardPrice = currentGame.getCardPrice(deckIndex, cardIndex);
        }

        bank.increment(currentPlayer.payPrice(cardPrice));
        //TODO: Fix, user needs to provide input probably

        Card card;
        if(deckIndex == Location.RESERVE)
        {
            card = currentPlayer.getReserveCard(cardIndex);
        }
        else
        {
            card = currentGame.getCard(deckIndex, cardIndex);
        }

        currentPlayer.addCard(card);
    }

    public static boolean canBuyCard(Location deckIndex, int cardIndex)
    {
        GemList cardPrice = null;
        if(deckIndex == Location.RESERVE)
        {
            cardPrice = currentPlayer.getReservedCardPrice(cardIndex);
        }
        else
        {
            try
            {
                cardPrice = currentGame.getCardPrice(deckIndex, cardIndex);
            }
            catch (InvalidActionException e) {}
            catch (InvalidTypeException e) {}
            catch (CardDoesNotExistException e) {}
        }

        if(cardPrice != null)
        {
            return currentPlayer.canPay(cardPrice);
        }
        else
        {
            return false;
        }
    }

    public static void reserveCard(Location deckIndex, int cardIndex) throws CardDoesNotExistException, InvalidActionException, InvalidTypeException
    {
        Card card = currentGame.getCard(deckIndex, cardIndex);
        currentPlayer.addReserveCard(card);
    }

    public static boolean canReserveCard(Location deckIndex, int cardIndex)
    {
        if(deckIndex == Location.RESERVE || currentPlayer.getReserveCardCount() >= 3)
        {
            return false;
        }
        else
        {
            try
            {
                if (currentGame.canReserveCard(deckIndex, cardIndex))
                {
                    return true;
                }
            }
            catch (InvalidTypeException e) {}
            catch (InvalidActionException e) {}
        }
        return false;
    }

    public static void pickNoble(int nobleIndex) throws InvalidActionException
    {
        if(canPickNoble(nobleIndex))
        {
            currentPlayer.addNoble(nobles.remove(nobleIndex));
        }
        else
        {
            throw new InvalidActionException();
        }
    }

    public static boolean canPickNoble(int nobleIndex)
    {
        return currentPlayer.canPayWithJustCards(nobles.get(nobleIndex).getRequirement());
    }

    public static void checkNobles() throws CanPickMultipleNoblesException, InvalidActionException
    {
        int count = 0;

        boolean[] possibleNobles = new boolean[nobles.size()];
        for(int i = 0; i < nobles.size(); i++)
        {
            possibleNobles[i] = false;
            if(canPickNoble(i))
            {
                possibleNobles[i] = true;
                count++;
            }
        }

        if(count == 1)
        {
            for(int i = 0; i < possibleNobles.length; i++)
            {
                if(possibleNobles[i])
                {
                    pickNoble(i);
                    break;
                }
            }
        }
        else if(count > 1)
        {
            throw new CanPickMultipleNoblesException(possibleNobles);
        }
    }

    public static void recalculate()
    {
        //re-total the current Player's VPs
        //TODO: figure out how to implement victory point recalculation
    }

    public static void endTurn()
    {
        currentPlayer = currentGame.endTurn();
    }
}
