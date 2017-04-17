package app.doordash.demo.http.models.restaurants;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Masoom on 4/16/17.
 */

public class Restaurant { //top level holding all info
    private String id; //Parsing what we need just for now
    @SerializedName("cover_img_url")
    private String coverImgUrl;
    private String name;
    private Address address;
    private String status;
    private String description;
    private String[] tags;

    public String getId() {
        return id;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String[] getTags() {
        return tags;
    }
}
