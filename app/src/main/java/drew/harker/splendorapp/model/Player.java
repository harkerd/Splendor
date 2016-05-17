package drew.harker.splendorapp.model;

import java.util.LinkedList;
import java.util.List;
import drew.harker.splendorapp.exceptions.InvalidActionException;
import drew.harker.splendorapp.exceptions.InvalidTypeException;
import drew.harker.splendorapp.model.pieces.Card;
import drew.harker.splendorapp.model.pieces.Gem;
import drew.harker.splendorapp.model.pieces.GemList;
import drew.harker.splendorapp.model.pieces.Token;
import drew.harker.splendorapp.model.pieces.TokenList;

public class Player
{
    private GemList gems = new GemList();
    private GemList cardTotals = new GemList();
    private TokenList tokens = new TokenList();
    private List<Card> reservedCards;
    private int victoryPoints;
    private String name = "Default Name";

    public Player(String name)
    {
        init();
        this.name = name;
    }

    private void init()
    {
        gems = new GemList();
        cardTotals = new GemList();
        tokens = new TokenList();
        reservedCards = new LinkedList<>();
        try
        {
            reservedCards.add(new Card(Gem.DIAMOND, new GemList(1,0,1,0,1), 0));
            reservedCards.add(new Card(Gem.SAPPHIRE, new GemList(0,1,0,1,0), 1));
            reservedCards.add(new Card(Gem.RUBY, new GemList(1,1,1,1,0), 2));
        } catch (InvalidTypeException e)
        {
            e.printStackTrace();
        }
        victoryPoints = 0;
    }

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

    public String getName()
    {
        return name;
    }

    public GemList getCardTotals()
    {
        return cardTotals;
    }

    public TokenList getTokens()
    {
        return tokens;
    }

    public int getVictoryPoints()
    {
        return victoryPoints;
    }

    public Card[] getReserveCardArray()
    {
        Card[] arr = new Card[Game.MAX_NUMBER_OF_RESERVED_CARDS];
        for(int i = 0; i < reservedCards.size(); i++)
        {
            arr[i] = reservedCards.get(i);
        }
        return arr;
    }
}
