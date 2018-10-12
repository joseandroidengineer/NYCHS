package com.jge.nychs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    private String overview;
    private String dbn;
    private RequestQueue queue;
    private SAT sat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String name = getIntent().getExtras().getString("schoolName");
        overview = getIntent().getExtras().getString("overview");
        dbn = getIntent().getExtras().getString("dbn");


        TextView numOfSatTakers = findViewById(R.id.num_of_sat_takers);
        TextView avgScoreMath = findViewById(R.id.avg_of_math_score);
        TextView avgScoreReading = findViewById(R.id.avg_reading_score);
        TextView avgScoreWriting = findViewById(R.id.avg_of_writing_score);
        TextView overviewtv = findViewById(R.id.overview);
        TextView schoolName = findViewById(R.id.detail_school_name);

        schoolName.setText(name);
        overviewtv.setText(overview);
        volleyRequest();

        if(sat == null){
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();
        }else{
            numOfSatTakers.setText(sat.getNumOfSATTakers());
            avgScoreMath.setText(sat.getSatMathAverageScore());
            avgScoreReading.setText(sat.getSatCriticalReadingAverageScore());
            avgScoreWriting.setText(sat.getSatWritingAverageScore());
        }
    }

    private void volleyRequest() {
        queue = Volley.newRequestQueue(getBaseContext());
        String url = NetworkUtils.buildStringUrl(dbn);
        Log.e("URL", url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            gsonMapSAT(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("VOLLEY", volleyError.toString());
                        Toast.makeText(getApplicationContext(), "Something went wrong with Volley", Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(jsonArrayRequest);
    }

    private void gsonMapSAT(JSONArray jsonArray) throws JSONException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        sat = gson.fromJson(String.valueOf(jsonObject), SAT.class);
    }
}
