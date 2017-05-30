package wingstud.com.bookingmaster.activitys.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.utils.Utils;

/**
 * Created by wingstud on 06-03-2017.
 */
public class DeatilsAdapter extends RecyclerView.Adapter<DeatilsAdapter.MyViewHolder> {

    private List<String> moviesList;
    private List<String> imagesurl;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView images;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            images = (ImageView) view.findViewById(R.id.images);

        }
    }


    public DeatilsAdapter(List<String> moviesList,List<String> images,Context context) {
        this.moviesList = moviesList;
        this.imagesurl = images;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.details_raw, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.name.setText(moviesList.get(position));
        Utils.setImegePicasso(context,"http://bookingmaster.org/admin/uploads/amenities-images/"+imagesurl.get(position),holder.images);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}