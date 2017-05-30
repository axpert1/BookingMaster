package wingstud.com.bookingmaster.activitys.activityss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.model.Temp;
import wingstud.com.bookingmaster.activitys.utils.AppUrl;
import wingstud.com.bookingmaster.activitys.utils.Utils;

public class MyAccount extends AppCompatActivity implements View.OnClickListener {
    private Button button_profile, button_changepassword;
    private View profileview, changepasswordview;
    private CardView passwordchangeCard, profileCard;
    private EditText name, email, phone;
    private Button save_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setTitle("Profile");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findsIds();
        hotelRequest("9782414161", 0);

    }

    private void findsIds() {
        button_profile = (Button) findViewById(R.id.button_profile);
        button_changepassword = (Button) findViewById(R.id.button_changepassword);
        profileview = (View) findViewById(R.id.profileinfo_view);
        changepasswordview = (View) findViewById(R.id.changepassword_view);
        profileCard = (CardView) findViewById(R.id.profileCard);
        passwordchangeCard = (CardView) findViewById(R.id.passwordchangeCard);
        //
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        save_update = (Button) findViewById(R.id.save_update);
        findsIdsClisks();
    }

    private void findsIdsClisks() {
        button_profile.setOnClickListener(this);
        button_changepassword.setOnClickListener(this);
        save_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_profile) {
            viewsetColor(v);
        } else if (v.getId() == R.id.button_changepassword) {
            viewsetColor(v);
        } else if (v.getId() == R.id.save_update) {
            if (vilidationEdittext()) {
                hotelRequest("9782414161", 1);
            }

        }
    }

    private boolean vilidationEdittext() {
        boolean status = false;
        if (name.getText().toString().length() > 0 && email.getText().toString().length() > 0 && phone.getText().toString().length() > 0) {
            status = true;
        } else {
            String empety = "";
            if (name.getText().toString().length() == 0) {
                empety = "Enter name";
            } else if (phone.getText().toString().length() == 0) {
                empety = "Enter phone";
            } else if (email.getText().toString().length() == 0) {
                empety = "Enter email";
            }
            Toast.makeText(MyAccount.this, empety, Toast.LENGTH_SHORT).show();
        }
        return status;
    }

    private void viewsetColor(View id) {
        if (id.getId() == R.id.button_profile) {
            profileview.setBackgroundColor(getResources().getColor(R.color.white));
            changepasswordview.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            profileCard.setVisibility(View.VISIBLE);
            passwordchangeCard.setVisibility(View.GONE);

        } else if (id.getId() == R.id.button_changepassword) {
            profileview.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            changepasswordview.setBackgroundColor(getResources().getColor(R.color.white));
            passwordchangeCard.setVisibility(View.VISIBLE);
            profileCard.setVisibility(View.GONE);
        }

    }

    private void hotelRequest(String phonenumber, int value) {
        Utils.progressDialog(MyAccount.this);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        if (value == 0) {
            //method=user_profile_details&phone=9782414161
            params.put("method", "user_profile_details");
            params.put("phone", phonenumber);
        } else if (value == 1) {
            //method=update_profile&phone=9782414161&name=Deepak Jangid&email=deepakjangid04@gmail.com
            params.put("method", "update_profile");
            params.put("phone", phonenumber);
            params.put("name", name.getText().toString());
            params.put("email", email.getText().toString());

        }

        client.post(AppUrl.URL_FULL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String str = String.valueOf(new String(response));
                Utils.dismissProgressDialog();
                Temp temp = (Temp) Utils.getJsonToClassObject(str, Temp.class);
                if (temp.status == null) {
                    name.setText(temp.name);
                    phone.setText(temp.mobile_no);
                    email.setText(temp.email);
                } else if (temp.status.equals("1")) {
                    Toast.makeText(getApplicationContext(), "Update success", Toast.LENGTH_SHORT).show();

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
