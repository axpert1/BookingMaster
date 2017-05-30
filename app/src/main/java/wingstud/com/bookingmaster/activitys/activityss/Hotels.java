package wingstud.com.bookingmaster.activitys.activityss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.adapter.HotelAdapter;
import wingstud.com.bookingmaster.activitys.model.HotelsTemp;
import wingstud.com.bookingmaster.activitys.utils.AppUrl;
import wingstud.com.bookingmaster.activitys.utils.Utils;

public class Hotels extends AppCompatActivity {
    private RecyclerView recyclerviewsearch;
    private List<HotelsTemp.Hotel> movieList = new ArrayList<>();
    private HotelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Hotels");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerviewsearch = (RecyclerView) findViewById(R.id.recyclerview_hotel);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewsearch.setLayoutManager(mLayoutManager);
        recyclerviewsearch.setItemAnimator(new DefaultItemAnimator());
        Intent i = getIntent();
        if (i != null) {
            String getValue = i.getExtras().getString(AppUrl.INTENT_SEND);
            int which = i.getExtras().getInt(AppUrl.INTENT_INT);
            hotelRequest(getValue, which);
        }

//
//        recyclerviewsearch.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerviewsearch, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
////                Intent intent = new Intent(getApplicationContext(), SearchCity.class);
////                startActivity(intent);
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));

    }

    private void hotelRequest(String title, int which) {
        Utils.progressDialog(Hotels.this);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        if (which == 0) {
            params.put("city_location", title);
        } else if (which == 1) {
            params.put("pack", title);
        } else if (which == 2) {
            params.put("hotel_cat", title);

        }

        client.post(AppUrl.URL_PACKAGEURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String str = String.valueOf(new String(response));
                Utils.dismissProgressDialog();
                HotelsTemp hotelsTemp = (HotelsTemp) Utils.getJsonToClassObject(str, HotelsTemp.class);
                if (hotelsTemp.hotels != null && hotelsTemp.hotels.size() > 0) {
                    mAdapter = new HotelAdapter(hotelsTemp.hotels, Hotels.this);
                    recyclerviewsearch.setAdapter(mAdapter);
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Utils.dismissProgressDialog();
                Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
            }


        });
    }
}
