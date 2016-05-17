package drew.harker.splendorapp.model.pieces;

public enum Gem
{
    DIAMOND, RUBY, EMERALD, SAPPHIRE, ONYX;

    public String toString()
    {
        switch(this)
        {
            case DIAMOND: return "d";
            case RUBY: return "r";
            case EMERALD: return "e";
            case SAPPHIRE: return "s";
            case ONYX: return "o";

            default: return "Error";
        }
    }

    public static Gem fromString(String s)
    {
        switch(s)
        {
            case "d": return DIAMOND;
            case "r": return RUBY;
            case "e": return EMERALD;
            case "s": return SAPPHIRE;
            case "o": return ONYX;

            default: return ONYX;
        }
    }
}
