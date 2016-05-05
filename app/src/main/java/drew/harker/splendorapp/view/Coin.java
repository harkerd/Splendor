package drew.harker.splendorapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import drew.harker.splendorapp.R;

public class Coin extends TextView
{
    private final boolean alwaysVisible;

    public Coin(Context context, AttributeSet set)
    {
        super(context, set);

        TypedArray attributes = context.obtainStyledAttributes(set, R.styleable.Coin);
        alwaysVisible = attributes.getBoolean(R.styleable.Coin_always_visible, false);

        setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setSelectable(false);
            }
        });
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter)
    {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if(!alwaysVisible && text.equals("0"))
        {
            setVisibility(INVISIBLE);
            update();
        }
    }

    public void setSelectable(boolean isSelectable)
    {
        setAlpha(.5f);
        update();
    }

    private void update()
    {
        invalidate();
        requestLayout();
    }
}
