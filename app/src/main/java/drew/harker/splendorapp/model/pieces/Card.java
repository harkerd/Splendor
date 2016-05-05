package drew.harker.splendorapp.model.pieces;

import drew.harker.splendorapp.exceptions.InvalidTypeException;

public class Card
{
    private Gem source;
    private GemList requirement;
    private int victoryPoints;

    public Card(Gem gem, GemList requirement, int victoryPoints) throws InvalidTypeException
    {
        this.source = gem;
        this.requirement = requirement;
        this.victoryPoints = victoryPoints;
    }

    public Gem getSource()
    {
        return source;
    }

    public GemList getRequirement()
    {
        return requirement;
    }

    public int getVictoryPoints()
    {
        return victoryPoints;
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
            Card that = (Card) o;
            return source.equals(that.source) && requirement.equals(that.requirement) && victoryPoints == that.victoryPoints;
        }
    }
}
