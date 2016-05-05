package drew.harker.splendorapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import drew.harker.splendorapp.R;

public class ValueTokenView extends TextView
{
    private boolean alwaysVisible;

    public ValueTokenView(Context context, AttributeSet set)
    {
        super(context, set);

        TypedArray attributes = context.obtainStyledAttributes(set, R.styleable.ValueTokenView);
        alwaysVisible = attributes.getBoolean(R.styleable.ValueTokenView_always_visible, false);

        boolean selectable = attributes.getBoolean(R.styleable.ValueTokenView_selectable, false);
        if(selectable)
        {
            setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setSelectable(false);
                }
            });
        }

        int colorId = attributes.getResourceId(R.styleable.ValueTokenView_coin_color, R.color.gold);
        GradientDrawable sd = (GradientDrawable) getBackground().mutate();
        sd.setColor(getResources().getColor(colorId));
        sd.invalidateSelf();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter)
    {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        //TODO: find out why this is currently making everything disappear
        /*if(text.equals("0") && !alwaysVisible)
        {
            setVisibility(GONE);
            update();
        }*/
    }

    public void setSelectable(boolean isSelectable)
    {
        //setAlpha(.5f);
        update();
    }

    private void update()
    {
        invalidate();
        requestLayout();
    }
}
