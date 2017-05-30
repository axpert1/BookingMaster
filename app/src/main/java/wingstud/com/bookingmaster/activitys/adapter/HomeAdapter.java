package wingstud.com.bookingmaster.activitys.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.activityss.Hotels;
import wingstud.com.bookingmaster.activitys.model.HomeTemp;
import wingstud.com.bookingmaster.activitys.utils.AppUrl;
import wingstud.com.bookingmaster.activitys.utils.Utils;

/**
 * Created by wingstud on 04-03-2017.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<HomeTemp.Pakg> moviesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price;
        public ImageView imageView;
        public LinearLayout text_layout;

        public MyViewHolder(View view) {
            super(view);
            price = (TextView) view.findViewById(R.id.price);
            imageView = (ImageView) view.findViewById(R.id.imageviewhome);
            text_layout = (LinearLayout) view.findViewById(R.id.text_layout);
        }
    }


    public HomeAdapter(List<HomeTemp.Pakg> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_raw, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final HomeTemp.Pakg movie = moviesList.get(position);
        holder.price.setText(context.getResources().getString(R.string.Rs) + " " + movie.price);
        Utils.setImegePicasso(context, movie.image, holder.imageView);
        holder.text_layout.getBackground().setAlpha(150);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Hotels.class);
                intent.putExtra(AppUrl.INTENT_SEND, movie.price);
                intent.putExtra(AppUrl.INTENT_INT, 1);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}