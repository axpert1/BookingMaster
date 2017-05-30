package wingstud.com.bookingmaster.activitys.activityss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import wingstud.com.bookingmaster.R;

/**
 * Created by wingstud on 22-02-2017.
 */

public class Phonenum extends AppCompatActivity {
    CardView submitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);


        submitbutton = (CardView) findViewById(R.id.submitbutton);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Phonenum.this, MainActivity.class);
                startActivity(intent);
                Phonenum.this.finish();
            }
        });
    }


}