package app.doordash.demo.http.models.restaurants;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Masoom on 4/16/17.
 */

public class RestaurantDetail {
    //private String id; //Parsing what we need just for now
    @SerializedName("cover_img_url")
    private String coverImgUrl;
    private String status;
    private String description;
    @SerializedName("delivery_fee")
    private String deliveryfee;
    private String name;


 /*   public String getId() {
        return id;
    }*/

   public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getDeliveryfee() {
        return deliveryfee;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/
    public String getDescription() {
        return description;
    }


}
