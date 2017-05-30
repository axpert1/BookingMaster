package wingstud.com.bookingmaster.activitys.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.activityss.Hotels;
import wingstud.com.bookingmaster.activitys.model.HomeTemp;
import wingstud.com.bookingmaster.activitys.utils.AppUrl;
import wingstud.com.bookingmaster.activitys.utils.Utils;

/**
 * Created by wingstud on 10/3/17.
 */
public class HotelCategoryAdapter extends RecyclerView.Adapter<HotelCategoryAdapter.MyViewHolder> {

    private List<HomeTemp.HotelCategory> moviesList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView hotelimage;


        public MyViewHolder(View view) {
            super(view);
            hotelimage = (ImageView) view.findViewById(R.id.cathotelimage);

        }
    }

    public HotelCategoryAdapter(List<HomeTemp.HotelCategory> moviesList, Context mContext) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_cat_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final HomeTemp.HotelCategory hotel = moviesList.get(position);
        Utils.setImegePicasso(mContext, hotel.image, holder.hotelimage);
        holder.hotelimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Hotels.class);
                intent.putExtra(AppUrl.INTENT_SEND, hotel.id);
                intent.putExtra(AppUrl.INTENT_INT, 2);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
