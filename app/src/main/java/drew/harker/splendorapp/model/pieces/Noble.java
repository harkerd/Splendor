package drew.harker.splendorapp.model.pieces;

public class Noble
{
    private GemList requirement;
    private int victoryPoints;

    public Noble(GemList requirement, int victoryPoints)
    {
        this.requirement = requirement;
        this.victoryPoints = victoryPoints;
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
            Noble that = (Noble) o;
            return requirement.equals(that.requirement) && victoryPoints == that.victoryPoints;
        }
    }
}
