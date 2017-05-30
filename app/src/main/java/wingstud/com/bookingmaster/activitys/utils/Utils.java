package wingstud.com.bookingmaster.activitys.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import wingstud.com.bookingmaster.R;

/**
 * Created by wingstud on 04-03-2017.
 */
public class Utils {
    private static Dialog apiCallingProgressDialog;
    static AlertDialog retryCustomAlert;
    private static Dialog confirmation;

    public static void setImegePicasso(Context context, String imgaeUrl, ImageView image) {
        Picasso.with(context)
                .load(imgaeUrl)
                .placeholder(R.drawable.ic_city_placeholder)
                .into(image);


    }

    //parse the response coming from server using gson library.
    public static Object getJsonToClassObject(String jsonStr, Class<?> classs) {
        Object obj = null;
        try {
            Gson gson = new GsonBuilder().create();
            obj = gson.fromJson(jsonStr, classs);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("errors", "While parsing: " + e.getMessage());

        }
        return obj;
    }


    public static void progressDialog(Context mContext) {
        apiCallingProgressDialog = new Dialog(mContext);
        apiCallingProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        apiCallingProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //  progressbar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);

        apiCallingProgressDialog.setContentView(R.layout.progress_alert);
        TextView txtProgressMsg = (TextView) apiCallingProgressDialog.findViewById(R.id.txtProgressMsg);
        txtProgressMsg.setText("please wait...");
        ProgressBar progressBar = (ProgressBar) apiCallingProgressDialog.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(mContext.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_IN);
        apiCallingProgressDialog.setCancelable(false);
        apiCallingProgressDialog.show();
    }

    public static void dismissProgressDialog() {
        if (apiCallingProgressDialog != null) {
            apiCallingProgressDialog.dismiss();
        }
    }

    public static void ScankBarView(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.RED)
                .show();
    }
}
