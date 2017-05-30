package wingstud.com.bookingmaster.activitys.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wingstud on 06-03-2017.
 */
public class HotelsTemp {
    @SerializedName("hotels")
    @Expose
    public List<Hotel> hotels = null;

    public class Hotel {
        public Hotel(String id, String slug, String name, String price, String image, String address) {
            this.id = id;
            this.slug = slug;
            this.name = name;
            this.price = price;
            this.image = image;
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("slug")
        @Expose
        public String slug;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("price")
        @Expose
        public String price;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("address")
        @Expose
        public String address;
    }
}
