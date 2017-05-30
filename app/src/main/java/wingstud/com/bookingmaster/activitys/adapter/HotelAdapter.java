package wingstud.com.bookingmaster.activitys.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.Others.DatabaseHandler;
import wingstud.com.bookingmaster.activitys.activityss.Details;
import wingstud.com.bookingmaster.activitys.model.HotelsTemp;
import wingstud.com.bookingmaster.activitys.utils.AppUrl;
import wingstud.com.bookingmaster.activitys.utils.Utils;

/**
 * Created by wingstud on 06-03-2017.
 */
public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.MyViewHolder> {

    private List<HotelsTemp.Hotel> moviesList;
    private Context mContext;
    private DatabaseHandler db;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView hotelimage, image_saved;
        public TextView name, price, address;

        public MyViewHolder(View view) {
            super(view);
            hotelimage = (ImageView) view.findViewById(R.id.hotelimage);
            image_saved = (ImageView) view.findViewById(R.id.image_saved);
            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            address = (TextView) view.findViewById(R.id.address);
        }
    }

    public HotelAdapter(List<HotelsTemp.Hotel> moviesList, Context mContext) {
        this.moviesList = moviesList;
        this.mContext = mContext;
        db = new DatabaseHandler(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final HotelsTemp.Hotel hotel = moviesList.get(position);
        holder.name.setText(hotel.name);
        holder.price.setText(mContext.getResources().getString(R.string.Rs) + " " + hotel.price);
        holder.address.setText(hotel.address);
        Utils.setImegePicasso(mContext, hotel.image, holder.hotelimage);
        if (db.getAvelable(hotel.id)) {
            holder.image_saved.setImageResource(R.drawable.ic_favorite_outline_24dp);
        } else {
            holder.image_saved.setImageResource(R.drawable.ic_favorite_24dp);

        }
        holder.image_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     hotelRequest(hotel.id, "1");
                boolean output = db.insert_Saved(hotel.id, hotel.slug, hotel.name, hotel.price, hotel.image, hotel.address);
                if (output) {
                    holder.image_saved.setImageResource(R.drawable.ic_favorite_24dp);
                } else {
                    db.delete_byID(hotel.id);
                    holder.image_saved.setImageResource(R.drawable.ic_favorite_outline_24dp);
                }

            }
        });
        holder.hotelimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Details.class);
                intent.putExtra(AppUrl.INTENT_SEND, hotel.slug);
                mContext.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
