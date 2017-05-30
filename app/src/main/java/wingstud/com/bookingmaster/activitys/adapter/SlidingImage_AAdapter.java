package wingstud.com.bookingmaster.activitys.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import wingstud.com.bookingmaster.R;

/**
 * Created by wingstud on 22-02-2017.
 */
public class SlidingImage_AAdapter extends PagerAdapter {
    private List<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;

    public SlidingImage_AAdapter(Context context, List<String> IMAGES) {
        this.context = context;
        this.IMAGES = IMAGES;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View imageLayout = inflater.inflate(R.layout.slidingimages_llayout, view,
                false);
        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);
        imageView.setImageResource(R.drawable.splash_one);

       // view.addView(imageLayout, position);
        ((ViewPager) view).addView(imageLayout);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}