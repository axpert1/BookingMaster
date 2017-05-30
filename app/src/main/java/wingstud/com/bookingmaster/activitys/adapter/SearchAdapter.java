package wingstud.com.bookingmaster.activitys.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.model.SearchTemp;

/**
 * Created by wingstud on 06-03-2017.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private List<SearchTemp.Search> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_search;

        public MyViewHolder(View view) {
            super(view);
            name_search = (TextView) view.findViewById(R.id.name_search);

        }
    }


    public SearchAdapter(List<SearchTemp.Search> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_raw, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SearchTemp.Search search=moviesList.get(position);
        holder.name_search.setText(search.value);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
