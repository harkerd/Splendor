package drew.harker.splendorapp.model;

import java.util.List;

import drew.harker.splendorapp.model.exceptions.InvalidActionException;
import drew.harker.splendorapp.model.exceptions.InvalidTypeException;
import drew.harker.splendorapp.model.pieces.Card;
import drew.harker.splendorapp.model.pieces.GemList;
import drew.harker.splendorapp.model.pieces.Noble;
import drew.harker.splendorapp.model.pieces.Token;
import drew.harker.splendorapp.model.pieces.TokenList;

public class Player
{
    private GemList gems;
    private TokenList tokens;
    private List<Card> cards;
    private List<Card> reservedCards;
    private List<Noble> nobles;
    private int victoryPoints;

    public void increment(Token token) throws InvalidTypeException
    {
        tokens.increment(token);
    }

    public void addReserveCard(Card card) throws InvalidActionException
    {
        if(reservedCards.size() >= 3)
        {
            throw new InvalidActionException();
        }
        else
        {
            reservedCards.add(card);
        }
    }

    public GemList getReservedCardPrice(int index)
    {
        return reservedCards.get(index).getRequirement();
    }

    public Card getReserveCard(int index)
    {
        return reservedCards.remove(index);
    }

    public int getReserveCardCount()
    {
        return reservedCards.size();
    }

    public boolean canPay(GemList cardPrice)
    {
        GemList cardRemainder = gems.subtract(cardPrice);
        if(!cardRemainder.hasNegativeValue())
        {
            return true;
        }
        GemList tokensNeeded = cardRemainder.countNegativeValues();

        GemList tokenRemainder = tokens.getGemList().subtract(tokensNeeded);
        if(!tokenRemainder.hasNegativeValue())
        {
            return true;
        }

        try
        {
            if(tokenRemainder.count() < tokens.get(Token.GOLD))
            {
                return true;
            }
        }
        catch (InvalidTypeException e) {}

        return false;
    }

    public TokenList payPrice(GemList cardPrice)
    {
        //TODO: implement
        return null;
    }

    public boolean canPayWithJustCards(GemList requirement)
    {
        //TODO: implement
        return false;
    }

    public void addCard(Card card)
    {
        cards.add(card);
    }


    public void addNoble(Noble noble)
    {
        nobles.add(noble);
    }
}
