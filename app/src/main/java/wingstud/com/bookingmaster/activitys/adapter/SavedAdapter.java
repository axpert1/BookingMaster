package wingstud.com.bookingmaster.activitys.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.Others.DatabaseHandler;
import wingstud.com.bookingmaster.activitys.activityss.Details;
import wingstud.com.bookingmaster.activitys.model.SavedTemp;
import wingstud.com.bookingmaster.activitys.utils.AppUrl;
import wingstud.com.bookingmaster.activitys.utils.Utils;

/**
 * Created by wingstud on 15/3/17.
 */
public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.MyViewHolder> {

    private List<SavedTemp> moviesList;
    private Context mContext;
    private DatabaseHandler db;
    private LinearLayout linearLayout;

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

    public SavedAdapter(List<SavedTemp> moviesList, Context mContext, LinearLayout linearLayout) {
        this.moviesList = moviesList;
        this.mContext = mContext;
        this.linearLayout = linearLayout;
        db = new DatabaseHandler(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SavedTemp hotel = moviesList.get(position);
        holder.name.setText(hotel.getName());
        holder.price.setText(mContext.getResources().getString(R.string.Rs) + " " + hotel.getPrice());
        holder.address.setText(hotel.getAddress());
        Utils.setImegePicasso(mContext, hotel.getImage(), holder.hotelimage);

        holder.image_saved.setImageResource(R.drawable.ic_favorite_24dp);

        holder.image_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     hotelRequest(hotel.id, "1");
                db.delete_byID(hotel.getId());
                holder.image_saved.setImageResource(R.drawable.ic_favorite_outline_24dp);
                moviesList.remove(position);
                notifyDataSetChanged();
                Log.d("SizeList",""+moviesList.size());
                if (moviesList.size() == 0) {
                    linearLayout.setVisibility(View.VISIBLE);
                }

            }
        });
        holder.hotelimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Details.class);
                intent.putExtra(AppUrl.INTENT_SEND, hotel.getSlug());
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}