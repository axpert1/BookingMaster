package wingstud.com.bookingmaster.activitys.activityss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.Others.DatabaseHandler;
import wingstud.com.bookingmaster.activitys.adapter.SavedAdapter;
import wingstud.com.bookingmaster.activitys.model.SavedTemp;

public class SavedHotels extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerviewsearch;
    private List<SavedTemp> movieList = new ArrayList<>();
    private SavedAdapter mAdapter;
    private LinearLayout mainLayout;
    DatabaseHandler db;
    private Button startbrowsing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_hotels);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Saved Hotel");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerviewsearch = (RecyclerView) findViewById(R.id.recyclerview_hotel);
        startbrowsing = (Button) findViewById(R.id.startbrowsing);
        startbrowsing.setOnClickListener(this);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewsearch.setLayoutManager(mLayoutManager);
        recyclerviewsearch.setItemAnimator(new DefaultItemAnimator());
        db = new DatabaseHandler(this);
        ArrayList<HashMap<String, String>> hashMaps = db.get_saved();
        if (hashMaps.size() > 0) {
            for (int i = 0; i < hashMaps.size(); i++) {
                movieList.add(new SavedTemp(hashMaps.get(i).get("h_id"), hashMaps.get(i).get("h_slug"), hashMaps.get(i).get("h_name"), hashMaps.get(i).get("h_price"), hashMaps.get(i).get("h_image"), hashMaps.get(i).get("h_address")));
            }
            recyclerviewsearch.setVisibility(View.VISIBLE);
            mAdapter = new SavedAdapter(movieList, SavedHotels.this,mainLayout);
            recyclerviewsearch.setAdapter(mAdapter);
        } else {
            mainLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startbrowsing) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

//    private void hotelRequest(String Userid) {
//        Utils.progressDialog(SavedHotels.this);
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams params = new RequestParams();
//        params.put("user_id", Userid);
//        params.put("method", "save_hotel_list");
//        client.post(AppUrl.URL_FULL, params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
//                // called when response HTTP status is "200 OK"
//                String str = String.valueOf(new String(response));
//                Utils.dismissProgressDialog();
//                HotelsTemp hotelsTemp = (HotelsTemp) Utils.getJsonToClassObject(str, HotelsTemp.class);
//                if (hotelsTemp.hotels != null && hotelsTemp.hotels.size() > 0) {
//                    recyclerviewsearch.setVisibility(View.VISIBLE);
//                    mAdapter = new SavedAdapter(hotelsTemp.hotels, SavedHotels.this);
//                    recyclerviewsearch.setAdapter(mAdapter);
//                } else {
//                    mainLayout.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
//                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//                Utils.dismissProgressDialog();
//                Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
//            }
//        });
    // }
}
