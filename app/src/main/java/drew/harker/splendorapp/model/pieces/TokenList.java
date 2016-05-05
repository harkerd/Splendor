package drew.harker.splendorapp.model.pieces;

import drew.harker.splendorapp.model.exceptions.InsufficientResourcesException;
import drew.harker.splendorapp.model.exceptions.InvalidTypeException;

public class TokenList
{
    private int diamond;
    private int emerald;
    private int sapphire;
    private int ruby;
    private int onyx;
    private int gold;

    public TokenList()
    {
        diamond = 0;
        emerald = 0;
        sapphire = 0;
        ruby = 0;
        onyx = 0;
        gold = 0;
    }

    public TokenList(int tokenMax)
    {
        diamond = tokenMax;
        emerald = tokenMax;
        sapphire = tokenMax;
        ruby = tokenMax;
        onyx = tokenMax;
        gold = 5;
    }

    public int get(Token token) throws InvalidTypeException
    {
        int value;
        switch(token)
        {
            case DIAMOND: value = diamond; break;
            case EMERALD: value = emerald; break;
            case SAPPHIRE: value = sapphire; break;
            case RUBY: value = ruby; break;
            case ONYX: value = onyx; break;
            case GOLD: value = gold; break;
            default: throw new InvalidTypeException();
        }
        return value;
    }

    public void increment(TokenList tokens)
    {
        diamond += tokens.diamond;
        emerald += tokens.emerald;
        sapphire += tokens.sapphire;
        ruby += tokens.ruby;
        onyx += tokens.onyx;
        gold += tokens.gold;
    }

    public void increment(Token token) throws InvalidTypeException
    {
        switch(token)
        {
            case DIAMOND: diamond++; break;
            case EMERALD: emerald++; break;
            case SAPPHIRE: sapphire++; break;
            case RUBY: ruby++; break;
            case ONYX: onyx++; break;
            case GOLD: gold++; break;
            default: throw new InvalidTypeException();
        }
    }

    public void decrement(Token token) throws InvalidTypeException, InsufficientResourcesException
    {
        switch(token)
        {
            case DIAMOND: diamond--; break;
            case EMERALD: emerald--; break;
            case SAPPHIRE: sapphire--; break;
            case RUBY: ruby--; break;
            case ONYX: onyx--; break;
            case GOLD: gold--; break;
            default: throw new InvalidTypeException();
        }

        if(diamond < 0 || emerald < 0 || sapphire < 0 || ruby < 0 || onyx < 0 || gold < 0)
        {
            throw new InsufficientResourcesException();
        }
    }

    public GemList getGemList()
    {
        return new GemList(diamond, emerald, sapphire, ruby, onyx);
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == null)
        {
            return false;
        }
        else if(o.getClass() != this.getClass())
        {
            return false;
        }
        else if(o == this)
        {
            return true;
        }
        else
        {
            TokenList that = (TokenList) o;
            return diamond == that.diamond &&
                    emerald == that.emerald &&
                    sapphire == that.sapphire &&
                    ruby == that.ruby &&
                    onyx == that.onyx &&
                    gold == that.gold;
        }
    }
}
