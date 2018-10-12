package com.jge.nychs;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {

    private static final String SAT_BASE_URL =  "https://data.cityofnewyork.us/resource/734v-jeq5.json";
    private static final String PARAM_QUERY = "dbn";

    public static String buildStringUrl(String dbn) {
        Uri builtUri = Uri.parse(SAT_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY,dbn)
                .build();
        return builtUri.toString();
    }
}
