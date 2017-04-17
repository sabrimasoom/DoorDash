package app.doordash.demo.http;

import java.util.List;


import app.doordash.demo.http.models.restaurants.Restaurant;
import app.doordash.demo.http.models.restaurants.RestaurantDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Masoom on 4/16/17.
 */

public interface Api {

    @GET("restaurant/") //get all places in lat lng area
    Call<List<Restaurant>> getRestaurants(@Query("lat") double lat, @Query("lng")  double lng);

    //@GET("restaurant/{id}/") //get info on place
    @GET("restaurant/{id}/") //get info on place
    Call<RestaurantDetail> getRestaurantDetail(@Path("id") String id);

}
