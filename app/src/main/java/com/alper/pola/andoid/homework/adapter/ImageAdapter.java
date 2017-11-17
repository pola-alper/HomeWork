package com.alper.pola.andoid.homework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.alper.pola.andoid.homework.model.MovieList;
import com.alper.pola.andoid.homework.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


/**
 * Created by shashank on 02-Aug-17.
 */


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ContactViewHolder>  {
    Context context;
    private ArrayList<MovieList> movieLists;


    public ImageAdapter(Context context, ArrayList<MovieList> movieLists1) {
        this.context = context;
        this.movieLists = movieLists1;

    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected ImageView img;



        public ContactViewHolder(View v) {
            super(v);
            img =  v.findViewById(R.id.wallpaper);

        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row, parent, false);

        return new ContactViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {





        final MovieList movieList = movieLists.get(position);


          Glide.with(context).load("https://image.tmdb.org/t/p/w640"+movieList.getPosterPath()).into(holder.img);




    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }
}
