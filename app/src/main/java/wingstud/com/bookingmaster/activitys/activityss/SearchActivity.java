package wingstud.com.bookingmaster.activitys.activityss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import wingstud.com.bookingmaster.R;
import wingstud.com.bookingmaster.activitys.adapter.SearchAdapter;
import wingstud.com.bookingmaster.activitys.custom.RecyclerTouchListener;
import wingstud.com.bookingmaster.activitys.custom.SimpleDividerItemDecoration;
import wingstud.com.bookingmaster.activitys.model.SearchTemp;
import wingstud.com.bookingmaster.activitys.utils.AppUrl;
import wingstud.com.bookingmaster.activitys.utils.Utils;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerviewsearch;
    private List<SearchTemp.Search> movieList = new ArrayList<>();
    private SearchAdapter mAdapter;
    private EditText searchedittext;
    private AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(null);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerviewsearch = (RecyclerView) findViewById(R.id.recyclerviewsearch);
        searchedittext = (EditText) findViewById(R.id.searchedittext);
        searchRequest("");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewsearch.setLayoutManager(mLayoutManager);
        recyclerviewsearch.setItemAnimator(new DefaultItemAnimator());

        recyclerviewsearch.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerviewsearch.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerviewsearch, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), Hotels.class);
                intent.putExtra(AppUrl.INTENT_SEND,movieList.get(position).value);
                intent.putExtra(AppUrl.INTENT_INT, 0);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        searchedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence search, int start, int before, int count) {
                if (client != null) client.cancelRequests(SearchActivity.this, true);
                searchRequest(search.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void searchRequest(String search) {
        client = new AsyncHttpClient();
        client.get(AppUrl.URL_SEARCH + search, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String str = String.valueOf(new String(response));
                movieList.clear();
                SearchTemp orderInfoTemp = (SearchTemp) Utils.getJsonToClassObject(str, SearchTemp.class);
                if (orderInfoTemp.search !=null && orderInfoTemp.search.size()>0){
                    movieList=orderInfoTemp.search;
                    mAdapter = new SearchAdapter(movieList);
                    recyclerviewsearch.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }else {

                    mAdapter = new SearchAdapter(movieList);
                    recyclerviewsearch.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
