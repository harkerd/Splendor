package drew.harker.splendorapp.model.exceptions;

public class CanPickMultipleNoblesException extends Exception
{
    private boolean[] validNobles;

    public CanPickMultipleNoblesException(boolean[] validNobles)
    {
        this.validNobles = validNobles;
    }

    public boolean[] getValidNobles()
    {
        return validNobles;
    }
}
