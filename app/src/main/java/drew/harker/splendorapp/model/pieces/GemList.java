package drew.harker.splendorapp.model.pieces;

import java.io.Serializable;

import drew.harker.splendorapp.exceptions.InvalidTypeException;

public class GemList implements Serializable
{
    private int diamond;
    private int emerald;
    private int sapphire;
    private int ruby;
    private int onyx;

    public GemList()
    {
        this.diamond = 0;
        this.emerald = 0;
        this.sapphire = 0;
        this.ruby = 0;
        this.onyx = 0;
    }

    public GemList(int diamond, int emerald, int sapphire, int ruby, int onyx)
    {
        this.diamond = diamond;
        this.emerald = emerald;
        this.sapphire = sapphire;
        this.ruby = ruby;
        this.onyx = onyx;
    }

    public int get(Gem token) throws InvalidTypeException
    {
        int value;
        switch(token)
        {
            case DIAMOND: value = diamond; break;
            case EMERALD: value = emerald; break;
            case SAPPHIRE: value = sapphire; break;
            case RUBY: value = ruby; break;
            case ONYX: value = onyx; break;
            default: throw new InvalidTypeException();
        }
        return value;
    }

    public void increment(Gem token) throws InvalidTypeException
    {
        switch(token)
        {
            case DIAMOND: diamond++; break;
            case EMERALD: emerald++; break;
            case SAPPHIRE: sapphire++; break;
            case RUBY: ruby++; break;
            case ONYX: onyx++; break;
            default: throw new InvalidTypeException();
        }
    }

    public GemList subtract(GemList that)
    {
        GemList remainder = new GemList();
        remainder.diamond = this.diamond - that.diamond;
        remainder.emerald = this.emerald - that.emerald;
        remainder.sapphire = this.sapphire - that.sapphire;
        remainder.ruby = this.ruby - that.ruby;
        remainder.onyx = this.onyx - that.onyx;

        return remainder;
    }

    public GemList countNegativeValues()
    {
        GemList result = new GemList();
        if(diamond < 0)
        {
            result.diamond = -diamond;
        }
        if(emerald < 0)
        {
            result.emerald = -emerald;
        }
        if(sapphire < 0)
        {
            result.sapphire = -sapphire;
        }
        if(ruby < 0)
        {
            result.ruby = -ruby;
        }
        if(onyx < 0)
        {
            result.onyx = -onyx;
        }

        return result;
    }

    public boolean hasNegativeValue()
    {
        return diamond < 0 || emerald < 0 || sapphire < 0 || ruby < 0 || onyx < 0;
    }

    public int count()
    {
        return diamond + emerald + sapphire + ruby + onyx;
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
            GemList that = (GemList) o;
            return diamond == that.diamond &&
                    emerald == that.emerald &&
                    sapphire == that.sapphire &&
                    ruby == that.ruby &&
                    onyx == that.onyx;
        }
    }
}
