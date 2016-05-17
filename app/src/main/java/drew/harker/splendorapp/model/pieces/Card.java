package drew.harker.splendorapp.model.pieces;

import android.os.Parcel;
import android.os.Parcelable;

import drew.harker.splendorapp.exceptions.InvalidTypeException;

public class Card implements Parcelable
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

    protected Card(Parcel in)
    {
        victoryPoints = in.readInt();
        source = Gem.fromString(in.readString());
        requirement = (GemList) in.readSerializable();
    }

    public static final Creator<Card> CREATOR = new Creator<Card>()
    {
        @Override
        public Card createFromParcel(Parcel in)
        {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size)
        {
            return new Card[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(victoryPoints);
        dest.writeString(source.toString());
        dest.writeSerializable(requirement);
    }
}
