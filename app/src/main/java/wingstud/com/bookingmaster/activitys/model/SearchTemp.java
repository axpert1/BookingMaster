package wingstud.com.bookingmaster.activitys.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wingstud on 06-03-2017.
 */
public class SearchTemp {
    @SerializedName("search")
    @Expose
    public List<Search> search = null;

    public class Search {
        @SerializedName("value")
        @Expose
        public String value;
    }
}
