package com.alper.pola.andoid.homework.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.alper.pola.andoid.homework.Activity.Search_Activity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pola alper on 17-Nov-17.
 */

public class MovieModel {
    private static final String TAG = "Webservice";
    private static RequestQueue requestQueue;
    private static MovieModel INSTANCE;


    private MovieModel() {
    }

    public static MovieModel getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            requestQueue = Volley.newRequestQueue(context);



            INSTANCE = new MovieModel();

            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    public void getSearch(Context context, final GetSearchListCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/top_rated?api_key=43a7ea280d085bd0376e108680615c7f&language=en-US&page=1", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());

                        // display response
                        try {

                            Type listType = new TypeToken<List<MovieList>>() {
                            }.getType();
                            ArrayList<MovieList> movieLists = new Gson().fromJson(response.getString("results"), listType);
                            callBack.onSuccess(movieLists);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                        callBack.onFail(error.toString());
                    }
                }
        );

        queue.add(getRequest);
    }
    public void getSearchList(Context context, final GetSearchListCallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, Search_Activity.url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());

                        // display response
                        try {

                            Type listType = new TypeToken<List<MovieList>>() {
                            }.getType();
                            ArrayList<MovieList> movieLists = new Gson().fromJson(response.getString("results"), listType);
                            callBack.onSuccess(movieLists);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                        callBack.onFail(error.toString());
                    }
                }
        );

        queue.add(getRequest);
    }
    public interface GetSearchListCallBack{
        void onSuccess(ArrayList<MovieList> movieLists);
        void onFail(String message);
    }
}
