package wingstud.com.bookingmaster.activitys.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import wingstud.com.bookingmaster.activitys.AppInitialization;

/**
 * Created by wingstud on 25-05-2017.
 */
public class TextViewSemiBold extends TextView {
    public TextViewSemiBold(Context context) {
        super(context);
        init();
    }

    public TextViewSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewSemiBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setTypeface(AppInitialization.getFontSemiBold());
    }

}
