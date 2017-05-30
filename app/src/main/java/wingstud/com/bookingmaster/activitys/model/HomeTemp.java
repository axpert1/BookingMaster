package wingstud.com.bookingmaster.activitys.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Created by wingstud on 04-03-2017.
 */
public class HomeTemp {
    @SerializedName("packages")
    @Expose
    public List<Pakg> packages = null;
    @SerializedName("hotel_category")
    @Expose
    public List<HotelCategory> hotel_category = null;
    @SerializedName("banners")
    @Expose
    public List<Banners> banner = null;
    public class Pakg {
        @SerializedName("price")
        @Expose
        public String price;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("description")
        @Expose
        public String description;
    }
    public class Banners {
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("description")
        @Expose
        public String description;
    }
    public class HotelCategory {
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("name")
        @Expose
        public String name;
    }
}
