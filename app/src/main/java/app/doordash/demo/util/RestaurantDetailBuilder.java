package app.doordash.demo.util;

import java.util.ArrayList;
import java.util.List;


import app.doordash.demo.http.models.restaurants.RestaurantDetail;
import app.doordash.demo.views.RestaurantDetailModel;

import static app.doordash.demo.views.RestaurantDetailModel.ViewType.FOOD;
//import android.util.Log;

public class RestaurantDetailBuilder {
    private static final String TAG = "MyActivity";
    public static List<RestaurantDetailModel> getBuiltDetail(RestaurantDetail restaurantDetail){ // builds restaurant detail for adapter a head of time
        //Log.d(TAG, "RestaurantDetailBuilder");
        List<RestaurantDetailModel> restaurantDetailModel = new ArrayList<>();
        RestaurantDetailModel singleItem = new RestaurantDetailModel();
        singleItem.viewType = FOOD; // its a food view
        singleItem.item = restaurantDetail; // set item
        restaurantDetailModel.add(singleItem);
        return restaurantDetailModel;
    }
}
