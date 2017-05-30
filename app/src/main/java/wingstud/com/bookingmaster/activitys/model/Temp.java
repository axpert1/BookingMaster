package wingstud.com.bookingmaster.activitys.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wingstud on 10/3/17.
 */
public class Temp {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile_no")
    @Expose
    public String mobile_no;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("password")
    @Expose
    public String password;
}
