package wingstud.com.bookingmaster.activitys;

import android.app.Application;
import android.graphics.Typeface;

import wingstud.com.bookingmaster.activitys.utils.SharedPref;

/**
 * Created by wingstud on 25-05-2017.
 */
public class AppInitialization extends Application {
    private static Typeface fontRegular;
    private static Typeface josefinsans_semibold;

    @Override
    public void onCreate() {

        SharedPref.init(this);
        fontRegular = Typeface.createFromAsset(getAssets(), "lato-regular.ttf");
        josefinsans_semibold = Typeface.createFromAsset(getAssets(), "Lato-Semibold.ttf");
        super.onCreate();
    }

    public static Typeface getFontRegular() {
        return fontRegular;
    }

    public static Typeface getFontSemiBold() {
        return josefinsans_semibold;
    }
}
