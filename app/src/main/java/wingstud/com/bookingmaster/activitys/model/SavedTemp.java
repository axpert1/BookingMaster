package wingstud.com.bookingmaster.activitys.model;

/**
 * Created by wingstud on 15/3/17.
 */
public class SavedTemp {
    private String id,  slug,  name,  price,  image,  address;

    public SavedTemp(String id, String slug, String name, String price, String image, String address) {
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
}
