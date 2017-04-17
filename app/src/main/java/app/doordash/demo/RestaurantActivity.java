package app.doordash.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import app.doordash.demo.adapter.RestaurantDetailRecycler;
import app.doordash.demo.fragments.SingleMapViewFragment;
import app.doordash.demo.services.DoorDashService;
import app.doordash.demo.services.FavoriteService;
import app.doordash.demo.util.FragmentLoader;
import app.doordash.demo.util.RestaurantDetailBuilder;
import app.doordash.demo.views.RestaurantDetailModel;

import static app.doordash.demo.services.DoorDashService.DoorDashServiceEvent.RESTAURANT_DETAIL_EVENT;


public class RestaurantActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RestaurantDetailRecycler restaurantDetailRecycler;
    private SingleMapViewFragment singleMapViewFragment;
    private static final String TAG = "MyActivity";
    private Button addFavButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(DoorDashService.get().getCurrentRestaurant().getName());

        singleMapViewFragment = new SingleMapViewFragment();
        singleMapViewFragment.setRestaurant(DoorDashService.get().getCurrentRestaurant());
        singleMapViewFragment.setUpMap(); //create and set up map
        FragmentLoader.loadFragment(singleMapViewFragment, this, R.id.map_fragment); //load map fragment

        restaurantDetailRecycler = new RestaurantDetailRecycler();
        addFavButton = (Button) findViewById(R.id.add_favorites_button);
        if (FavoriteService.get().isFavourited(DoorDashService.get().getCurrentRestaurant().getId())) {
            addFavButton.setText("Remove to Favorites");
        }

        recyclerView = (RecyclerView) findViewById(R.id.menu_list);
        recyclerView.setAdapter(restaurantDetailRecycler); //set cycler
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        DoorDashService.get().getRestaurantDetail(DoorDashService.get().getCurrentRestaurant().getId());
        Log.d(TAG, DoorDashService.get().getCurrentRestaurant().getId().toString() + " Iddetail");
        setUpSearchButton();
    }

    public void setUpSearchButton(){
        addFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FavoriteService.get().isFavourited(DoorDashService.get().getCurrentRestaurant().getId())) {
                    FavoriteService.get().removeFromFavorites(DoorDashService.get().getCurrentRestaurant().getId());
                    addFavButton.setText("Add to Favorites");
                } else {
                    FavoriteService.get().addToFavorites(DoorDashService.get().getCurrentRestaurant());
                    addFavButton.setText("Remove to Favorites");

                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DoorDashService.DoorDashEvent doorDashEvent) {
        //Log.d(TAG, "onMessageEvent");
        //Log.d(TAG, doorDashEvent.event.toString());
        if (doorDashEvent.event.equals(RESTAURANT_DETAIL_EVENT)) {// wait for menu event and then add it to list
            List<RestaurantDetailModel> restaurantDetaillList = RestaurantDetailBuilder.getBuiltDetail(DoorDashService.get().getCurrentRestaurantDetail());
            //Log.d(TAG, "After onMessageEvent");
            restaurantDetailRecycler.setList(restaurantDetaillList); // got food http call now set and display
        }
    }
}
