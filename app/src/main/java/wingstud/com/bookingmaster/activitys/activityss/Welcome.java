package wingstud.com.bookingmaster.activitys.activityss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

import com.viewpagerindicator.CirclePageIndicator;

import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.adapter.CustomPagerAdapter;
import wingstud.com.bookingmaster.activitys.view_type.Myview;

/**
 * Created by wingstud on 22-02-2017.
 */
public class Welcome extends FragmentActivity {
    CardView cardview;
    private static int NUM_PAGES = 5;
    private ViewPager mPager;
    CustomPagerAdapter mCustomPagerAdapter;
    int[] mResources = {
            R.drawable.splash_one,
            R.drawable.splash_two,
            R.drawable.splash_three,
            R.drawable.splash_four,
            R.drawable.splash_five,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);
        slidingwithIndicator();
        cardview = (CardView) findViewById(R.id.cardview);
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Myview.rippleView2(v);
                Intent intent = new Intent(getApplicationContext(), Phonenum.class);
                startActivity(intent);


            }
        });


    }
    public void slidingwithIndicator() {
        mCustomPagerAdapter = new CustomPagerAdapter(this, mResources);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mCustomPagerAdapter);
        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(3 * density);
        NUM_PAGES = mResources.length;
    }

}