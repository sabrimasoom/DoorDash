package app.doordash.demo.views;

import app.doordash.demo.http.models.restaurants.RestaurantDetail;

/**
 * Created by Masoom on 4/17/17.
 */

public class RestaurantDetailModel {
    public enum ViewType {CATEGORY,FOOD}
    public RestaurantDetailModel.ViewType viewType;
    public RestaurantDetail item;
}
