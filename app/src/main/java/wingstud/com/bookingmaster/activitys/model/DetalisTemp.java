package wingstud.com.bookingmaster.activitys.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wingstud on 06-03-2017.
 */
public class DetalisTemp {
    @SerializedName("hotel_details")
    @Expose
    public List<HotelDetail> hotelDetails = null;

    public class HotelDetail {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("address")
        @Expose
        public String address;
        @SerializedName("amenities_images_base_url")
        @Expose
        public String amenities_images_base_url;
        @SerializedName("price")
        @Expose
        public String price;
        @SerializedName("amenities_images")
        @Expose
        public List<String> amenities_images = null;
        @SerializedName("amenities_name")
        @Expose
        public List<String> amenities_name = null;
        @SerializedName("rules")
        @Expose
        public String rules;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("lat")
        @Expose
        public String lat;
        @SerializedName("long")
        @Expose
        public String longs;
        @SerializedName("images")
        @Expose
        public String images;
        @SerializedName("slug")
        @Expose
        public String slug;

    }
}
