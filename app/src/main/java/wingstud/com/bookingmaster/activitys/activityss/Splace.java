package wingstud.com.bookingmaster.activitys.activityss;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;

import wingstud.com.bookingmaster.R;

public class Splace extends AppCompatActivity {
    Button hello;
    ImageView splacebariamge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace);
        setupWindowAnimations();
        splacebariamge = (ImageView) findViewById(R.id.splacebariamge);

        splacebariamge.setBackgroundResource(R.drawable.splacelogo);
        AnimationDrawable rocketAnimation = (AnimationDrawable) splacebariamge.getBackground();

        rocketAnimation.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(Splace.this, Welcome.class);
                    startActivity(intent);
                    Splace.this.finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(500);
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }


}
