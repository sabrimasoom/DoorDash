package app.doordash.demo.services;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import app.doordash.demo.http.HttpService;
import app.doordash.demo.http.models.restaurants.Restaurant;
import app.doordash.demo.http.models.restaurants.RestaurantDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

/**
 * Created by Masoom on 4/16/17.
 */

public class DoorDashService {
    private static DoorDashService doorDashService;
    private List<Restaurant> restaurants;
    private RestaurantDetail currentRestaurantDetail;
    private Restaurant currentRestaurant;
    private static final String TAG = "MyActivity";


    public enum DoorDashServiceEvent {RESTAURANT_SEARCH_EVENT, RESTAURANT_DETAIL_EVENT, ERROR}

    public class DoorDashEvent {
        public String errorMsg; // will be used for event handling
        public DoorDashServiceEvent event;
    }

    public static DoorDashService get() { //
        if (doorDashService == null) { //getApi top event
            doorDashService = new DoorDashService();
            return doorDashService;
        } else {
            return doorDashService;
        }
    }

    public void searchRestaurant(double lat, double lng) {
        HttpService.getApi().getRestaurants(lat,lng).enqueue(new Callback<List<Restaurant>>() { // getApi the top games, this is the default http call
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                restaurants = response.body(); //set current locations
                DoorDashEvent doorDashEvent = new DoorDashEvent();
                doorDashEvent.event = DoorDashServiceEvent.RESTAURANT_SEARCH_EVENT;
                EventBus.getDefault().post(doorDashEvent); // send off event download was ok
            }
            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {

            }
        });
    }

    public void getRestaurantDetail(String id) {
        HttpService.getApi().getRestaurantDetail(id).enqueue(new Callback<RestaurantDetail>() { // getApi the top games, this is the default http call
            @Override
            public void onResponse(Call<RestaurantDetail> call, Response<RestaurantDetail> response) {
                //Log.d(TAG, "getRestaurantDetail response" + response.body());
                currentRestaurantDetail = response.body();
                //Log.d(TAG, "getRestaurantDetail response");
                DoorDashEvent doorDashEvent = new DoorDashEvent();
                doorDashEvent.event = DoorDashServiceEvent.RESTAURANT_DETAIL_EVENT;
                EventBus.getDefault().post(doorDashEvent); // send off event menu download was ok
            }

            @Override
            public void onFailure(Call<RestaurantDetail> call, Throwable t) {
                Log.d("onFailure", t.toString());

            }
        });
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }


    public Restaurant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public void setCurrentRestaurant(Restaurant currentRestaurant) { //sets the currently selected restaurant
        this.currentRestaurant = currentRestaurant;
    }

    public RestaurantDetail getCurrentRestaurantDetail() {
        //Log.d(TAG, "currentRestaurantDetail");
        return currentRestaurantDetail;
    }
}
