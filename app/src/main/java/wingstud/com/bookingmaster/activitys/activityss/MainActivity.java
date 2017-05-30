package wingstud.com.bookingmaster.activitys.activityss;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.adapter.HomeAdapter;
import wingstud.com.bookingmaster.activitys.adapter.HotelCategoryAdapter;
import wingstud.com.bookingmaster.activitys.model.HomeTemp;
import wingstud.com.bookingmaster.activitys.network.Constant;
import wingstud.com.bookingmaster.activitys.network.NetworkManager;
import wingstud.com.bookingmaster.activitys.utils.AppUrl;
import wingstud.com.bookingmaster.activitys.utils.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NetworkManager.onCallback {
    private SliderLayout mDemoSlider;
    @Override
    public void onSuccess(boolean success, String response, int which) {
        String str = String.valueOf(new String(response));
        HomeTemp orderInfoTemp = (HomeTemp) Utils.getJsonToClassObject(str, HomeTemp.class);
        mAdapter = new HomeAdapter(orderInfoTemp.packages, MainActivity.this);
        recyclerView.setAdapter(mAdapter);
        mhotelCatAdapter = new HotelCategoryAdapter(orderInfoTemp.hotel_category, MainActivity.this);
        recycler_category.setAdapter(mhotelCatAdapter);
        slidingImage(orderInfoTemp.banner);
    }
    @Override
    public void onFailure(boolean success, String response, int which) {
    }

    private RecyclerView recyclerView, recycler_category;
    private HomeAdapter mAdapter;
    private HotelCategoryAdapter mhotelCatAdapter;
    private TextView searchtext;
    //private ImageView priminumeimage;
    private DrawerLayout drawer;
    private LinearLayout layout_myaccount, layoutmybookin, layout_savehotel, layout_mywallet, layout_share, layout_faq, layout_chat_with_us, layout_callus, layout_feedback, layout_logout;
    NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitydrawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("BOOKING MASTER");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_home);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //
        recycler_category = (RecyclerView) findViewById(R.id.recycler_category);
        GridLayoutManager mLayoutManager2 = new GridLayoutManager(getApplicationContext(), 1);
        recycler_category.setLayoutManager(mLayoutManager2);
        recycler_category.setItemAnimator(new DefaultItemAnimator());
        searchtext = (TextView) findViewById(R.id.searchtext);
        searchtext.setOnClickListener(this);
        homeRequest();
       // requestApi(null,AppUrl.URL_HOME,true,1);
        findids();
    }

    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    private void homeRequest() {
        Utils.progressDialog(MainActivity.this);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(AppUrl.URL_HOME, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String str = String.valueOf(new String(response));
                HomeTemp orderInfoTemp = (HomeTemp) Utils.getJsonToClassObject(str, HomeTemp.class);
                mAdapter = new HomeAdapter(orderInfoTemp.packages, MainActivity.this);
                recyclerView.setAdapter(mAdapter);
                mhotelCatAdapter = new HotelCategoryAdapter(orderInfoTemp.hotel_category, MainActivity.this);
                recycler_category.setAdapter(mhotelCatAdapter);
                Utils.dismissProgressDialog();
                Utils.dismissProgressDialog();
                slidingImage(orderInfoTemp.banner);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Utils.dismissProgressDialog();
                Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
            }


        });
    }

    public void slidingImage(List<HomeTemp.Banners> banner) {
        for (int i = 0; i < banner.size(); i++) {
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(null)
                    .image(banner.get(i).image)
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", banner.get(i).image);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        ///////
    }

    private void findids() {
        layout_myaccount = (LinearLayout) findViewById(R.id.layout_myaccount);
        layoutmybookin = (LinearLayout) findViewById(R.id.layoutmybookin);
        layout_savehotel = (LinearLayout) findViewById(R.id.layout_savehotel);
        layout_mywallet = (LinearLayout) findViewById(R.id.layout_mywallet);
        layout_share = (LinearLayout) findViewById(R.id.layout_share);
        layout_faq = (LinearLayout) findViewById(R.id.layout_faq);
        layout_chat_with_us = (LinearLayout) findViewById(R.id.layout_chat_with_us);
        layout_callus = (LinearLayout) findViewById(R.id.layout_callus);
        layout_feedback = (LinearLayout) findViewById(R.id.layout_feedback);
        layout_logout = (LinearLayout) findViewById(R.id.layout_logout);
        layout_myaccount.setOnClickListener(this);
        layoutmybookin.setOnClickListener(this);
        layout_savehotel.setOnClickListener(this);
        layout_mywallet.setOnClickListener(this);
        layout_share.setOnClickListener(this);
        layout_faq.setOnClickListener(this);
        layout_chat_with_us.setOnClickListener(this);
        layout_callus.setOnClickListener(this);
        layout_feedback.setOnClickListener(this);
        layout_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.searchtext) {
            startActivtys(SearchActivity.class);
        } else if (v.getId() == R.id.layout_share) {
            drawerCloseIfOpen();
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } else if (v.getId() == R.id.layout_faq) {
            startActivtys(Faq.class);
        } else if (v.getId() == R.id.layout_chat_with_us) {

        } else if (v.getId() == R.id.layout_callus) {
            startCall();
        } else if (v.getId() == R.id.layout_logout) {

        } else if (v.getId() == R.id.layout_myaccount) {
            startActivtys(MyAccount.class);
        } else if (v.getId() == R.id.layoutmybookin) {
            startActivtys(MyBooking.class);
        } else if (v.getId() == R.id.layout_savehotel) {
            startActivtys(SavedHotels.class);
        } else if (v.getId() == R.id.layout_mywallet) {

        }

    }

    private void startActivtys(Class aClass) {
        drawerCloseIfOpen();
        Intent intent = new Intent(getApplicationContext(), aClass);
        startActivity(intent);
    }

    private void drawerCloseIfOpen() {
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        }
    }

    private void startCall() {
        drawerCloseIfOpen();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + "9887230800"));
        startActivity(intent);
    }

    private void requestApi(RequestParams requestParams, String url, boolean dilog, int which) {
        networkManager = new NetworkManager();
        networkManager.callAPI(MainActivity.this, url, Constant.VAL_POST, requestParams, "", this, dilog, which);
    }
}
