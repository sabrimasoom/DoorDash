package app.doordash.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import java.text.NumberFormat;
import com.squareup.picasso.Picasso;

import java.util.List;

import app.doordash.demo.R;
import app.doordash.demo.views.RestaurantDetailModel;

/**
 * Created by Masoom on 4/16/17.
 */

public class RestaurantDetailRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RestaurantDetailModel> list;
    private static final String TAG = "MyActivity";
    private Context context;


    public class HeaderView extends RecyclerView.ViewHolder {
        public TextView title; //my view holder

        public HeaderView(View v) {
            super(v);
        }
    }

    public class SingleFoodView extends RecyclerView.ViewHolder {
        //public TextView itemName; //my view holder
        public TextView itemDescription;
        public ImageView restLogo;
        public TextView deliveryFee;
        public TextView status;

        public SingleFoodView(View v) {
            super(v);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).item == null) { //check for view type
            return 0; //header
        } else {
            return 1; //food item
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        Log.v(TAG, "RecyclerView.ViewHolder");
        if (viewType == 0) { // get view type and inflate
            //Do header
            v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_layout, parent, false);

            RestaurantDetailRecycler.HeaderView headerView = new RestaurantDetailRecycler.HeaderView(v);
            headerView.title = (TextView) v.findViewById(R.id.header);
            Log.d(TAG, headerView.title.toString());
            return headerView;
        } else {
            v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout, parent, false);
            RestaurantDetailRecycler.SingleFoodView singleFoodView = new RestaurantDetailRecycler.SingleFoodView(v);
            //singleFoodView.itemName = (TextView) v.findViewById(R.id.item_title);
            //Log.d(TAG, singleFoodView.itemName.toString());
            singleFoodView.restLogo = (ImageView) v.findViewById(R.id.rest_logo);
            singleFoodView.itemDescription = (TextView) v.findViewById(R.id.item_description);
            singleFoodView.status = (TextView) v.findViewById(R.id.item_status);
            singleFoodView.deliveryFee = (TextView) v.findViewById(R.id.item_delivery_fees);
            return singleFoodView;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        if (getItemViewType(position) == 0) {
            RestaurantDetailRecycler.HeaderView headerView = (RestaurantDetailRecycler.HeaderView) holder; //just the header view
            headerView.title.setText(list.get(position).item.getName());
        } else {
            RestaurantDetailRecycler.SingleFoodView singleFoodView = (RestaurantDetailRecycler.SingleFoodView) holder; //just the food view
            Picasso.with(context).load(list.get(position).item.getCoverImgUrl()).into(singleFoodView.restLogo);
            //singleFoodView.itemName.setText(list.get(position).item.getName());
            singleFoodView.itemDescription.setText(list.get(position).item.getDescription());
            singleFoodView.status.setText(list.get(position).item.getStatus());
            singleFoodView.deliveryFee.setText(getCurrency(list.get(position).item.getDeliveryfee()));
        }
    }

    public void setList(List<RestaurantDetailModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }


    public String getCurrency(String deliverfee) {
        String deliver_fee ="";
        if (deliverfee != null) {
            double amount = Double.parseDouble(deliverfee) /100;
            deliver_fee = NumberFormat.getCurrencyInstance().format(amount);
        }
        return deliver_fee;
    }

}
