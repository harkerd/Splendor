package drew.harker.splendorapp.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import drew.harker.splendorapp.R;
import drew.harker.splendorapp.exceptions.InvalidTypeException;
import drew.harker.splendorapp.model.pieces.Gem;

public class SourceView extends View
{

    public SourceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void updateColorRes(Gem source) throws InvalidTypeException
    {
        int colorId;
        switch(source)
        {
            case DIAMOND: colorId = R.color.diamond; break;
            case SAPPHIRE: colorId = R.color.sapphire; break;
            case EMERALD: colorId = R.color.emerald; break;
            case RUBY: colorId = R.color.ruby; break;
            case ONYX: colorId = R.color.onyx; break;
            default: throw new InvalidTypeException();
        }

        GradientDrawable sd = (GradientDrawable) getBackground().mutate();
        sd.setColor(getResources().getColor(colorId));
        sd.invalidateSelf();
    }
}
