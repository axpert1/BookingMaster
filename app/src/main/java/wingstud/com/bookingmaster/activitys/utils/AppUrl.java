package wingstud.com.bookingmaster.activitys.utils;

/**
 * Created by wingstud on 22-02-2017.
 */
public class AppUrl {
    public static String URL_BASE = "http://bookingmaster.org/api/";
    public static String URL_IMAGE_BASE = "http://bookingmaster.org/admin/uploads/hotels-images/";
    public static String URL_FULL = URL_BASE + "api.php";
    public static String URL_HOME = URL_BASE + "home-page.php";
    public static String URL_SEARCH = URL_BASE + "api.php?method=searching&query=";
    public static String URL_CITY_LOCATION = URL_BASE + "api.php?method=hotel_list_view&city_location=";
    public static String URL_PACKAGEURL = URL_BASE + "api.php?method=hotel_list_view";
    //Intent
    public static String INTENT_SEND = "intent_send";
    public static String INTENT_LAT = "intent_lat";
    public static String INTENT_LONG = "intent_long";
    public static String INTENT_INT = "intent_int";
}
