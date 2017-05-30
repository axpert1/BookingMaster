package wingstud.com.bookingmaster.activitys.activityss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import cz.msebera.android.httpclient.Header;
import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.adapter.DeatilsAdapter;
import wingstud.com.bookingmaster.activitys.custom.SimpleDividerItemDecoration;
import wingstud.com.bookingmaster.activitys.model.DetalisTemp;
import wingstud.com.bookingmaster.activitys.utils.AppUrl;
import wingstud.com.bookingmaster.activitys.utils.Utils;
public class Details extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    ImageView fullimage;
    TextView name, price, address, description, rules;
    RecyclerView amenitiesRecycleview;
    DeatilsAdapter mAdapter;
    double lats, longs;
    ImageView mapimage;
    Spinner adults, room;
    List<String> roomaslist = new ArrayList<String>();
    List<String> adultlist = new ArrayList<String>();
    TextView check_in_text, check_out_text;
    LinearLayout checkinlayout, checkoutlayout;
    int whitch = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
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
        final Intent i = getIntent();
        if (i != null) {
            String getValue = i.getExtras().getString(AppUrl.INTENT_SEND);
            hotelRequest(getValue);
        }
        fullimage = (ImageView) findViewById(R.id.fullimage);
        mapimage = (ImageView) findViewById(R.id.mapimage);
        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);
        address = (TextView) findViewById(R.id.address);
        description = (TextView) findViewById(R.id.description);
        check_in_text = (TextView) findViewById(R.id.check_in_text);
        check_out_text = (TextView) findViewById(R.id.check_out_text);
        checkinlayout = (LinearLayout) findViewById(R.id.checkinlayout);
        checkoutlayout = (LinearLayout) findViewById(R.id.checkoutlayout);
        checkinlayout.setOnClickListener(this);
        checkoutlayout.setOnClickListener(this);
        rules = (TextView) findViewById(R.id.rules);
        amenitiesRecycleview = (RecyclerView) findViewById(R.id.amenitiesRecycleview);
        amenitiesRecycleview.setItemAnimator(new DefaultItemAnimator());
        amenitiesRecycleview.addItemDecoration(new SimpleDividerItemDecoration(this));
        mapimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra(AppUrl.INTENT_LAT, lats);
                intent.putExtra(AppUrl.INTENT_LONG, longs);
                startActivity(intent);
            }
        });
        room = (Spinner) findViewById(R.id.room);
        adults = (Spinner) findViewById(R.id.adults);
        roomaslist.add("1 Room");
        roomaslist.add("2 Room");
        roomaslist.add("3 Room");
        roomaslist.add("4 Room");
        roomaslist.add("5 Room");

        adultlist.add("1 Adult");
        adultlist.add("2 Adult");
        adultlist.add("3 Adult");
        adultlist.add("4 Adult");
        adultlist.add("5 Adult");

        // Creating adapter for spinner
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roomaslist);
        ArrayAdapter<String> adultsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adultlist);
        // Drop down layout style - list view with radio button
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adultsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        room.setAdapter(roomAdapter);
        adults.setAdapter(adultsAdapter);
    }

    private void hotelRequest(String title) {
        Utils.progressDialog(Details.this);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("slug", title);
        params.put("method", "hotel_details");
        client.post(AppUrl.URL_FULL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String str = String.valueOf(new String(response));
                Utils.dismissProgressDialog();
                DetalisTemp detalisTemp = (DetalisTemp) Utils.getJsonToClassObject(str, DetalisTemp.class);
                if (detalisTemp != null) {
                    String image[] = detalisTemp.hotelDetails.get(0).images.split(",");
                    Log.d("StringDAte", image + "");
                    Utils.setImegePicasso(Details.this, AppUrl.URL_IMAGE_BASE + image[0], fullimage);
                    name.setText(detalisTemp.hotelDetails.get(0).name);
                    lats = Double.parseDouble(detalisTemp.hotelDetails.get(0).lat);
                    longs = Double.parseDouble(detalisTemp.hotelDetails.get(0).longs);
                    price.setText(getResources().getString(R.string.Rs) + " " + detalisTemp.hotelDetails.get(0).price);
                    address.setText(detalisTemp.hotelDetails.get(0).address);
                    rules.setText(detalisTemp.hotelDetails.get(0).rules);
                    description.setText(detalisTemp.hotelDetails.get(0).description);
                    mAdapter = new DeatilsAdapter(detalisTemp.hotelDetails.get(0).amenities_name, detalisTemp.hotelDetails.get(0).amenities_images, Details.this);
                    amenitiesRecycleview.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkinlayout) {
            whitch = 1;
            dateDilog();
        } else if (v.getId() == R.id.checkoutlayout) {
            whitch = 2;
            dateDilog();
        }
    }

    private void dateDilog() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                Details.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setMinDate(now);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        //String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        String date = dayOfMonth + "/" + (++monthOfYear);
        if (whitch == 1) {
            check_in_text.setText(date);
        } else if (whitch == 2) {
            check_out_text.setText(date);
        }
    }
}
