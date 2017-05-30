package wingstud.com.bookingmaster.activitys.utils;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * Created by wingstud on 24-03-2017.
 */
public class SharedPref {
    private static SharedPreferences mPref;
    private static SharedPreferences.Editor editor;

    public static void init(Context context) {
        mPref = context.getSharedPreferences(AppString.SHARED_PREF, Context.MODE_PRIVATE);
        editor = mPref.edit();
    }

    public static String getSP(String Key) {
        return mPref.getString(Key, "");
    }
    public static boolean getboolSP(String Key) {
        return mPref.getBoolean(Key, false);
    }
    public static void putboolSP(String Key, boolean value) {
        editor.putBoolean(Key, value);
        editor.commit();
    }
    public static void putSP(String Key, String value) {
        editor.putString(Key, value);
        editor.commit();
    }

    public static void clearSp() {
        editor.clear();
        editor.commit();
    }

    public static void removeSP(String key) {
        editor.remove(key);
        editor.commit();
    }
}
