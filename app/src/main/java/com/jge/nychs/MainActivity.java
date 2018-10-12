package com.jge.nychs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SchoolAdapter.ListItemClickListener {

    private RecyclerView mRecyclerView;
    private SchoolAdapter mSchoolAdapter;
    private static final String SCHOOL_URL = "https://data.cityofnewyork.us/resource/97mf-9njv.json";
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private Gson gson;
    private List<School> schools;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview_list_of_schools);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);


        mSchoolAdapter = new SchoolAdapter(this);

        mRecyclerView.setAdapter(mSchoolAdapter);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        volleyRequest();
        loadSchoolData();

        mSchoolAdapter.notifyDataSetChanged();
    }

    private void loadSchoolData() {
        showSchoolDataView();
        mLoadingIndicator.setVisibility(View.INVISIBLE);
    }

    private void showSchoolDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }
    private void gsonMap(JSONArray jsonArray) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();
            if (jsonArray.length() > 0) {
                schools = Arrays.asList(gson.fromJson(jsonArray.toString(), School[].class));
                mSchoolAdapter.setSchoolData(schools);
            }
    }


    private void volleyRequest() {
        queue = Volley.newRequestQueue(getBaseContext());
        String url = SCHOOL_URL;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            gsonMap(response);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("VOLLEY", volleyError.toString());
                            showErrorMessage();
                        }
                    });
        queue.add(jsonArrayRequest);
        }

    @Override
    public void onListItemClick(School schoolIndexClicked) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("schoolName", schoolIndexClicked.getSchoolName());
        intent.putExtra("overview", schoolIndexClicked.getOverview());
        intent.putExtra("dbn",schoolIndexClicked.getDbn());
        startActivity(intent);
    }
}
