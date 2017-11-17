package com.alper.pola.andoid.homework.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alper.pola.andoid.homework.R;
import com.alper.pola.andoid.homework.model.MovieList;
import com.bumptech.glide.Glide;

public class DeatilsActivity extends AppCompatActivity {

TextView name;
ImageView poster;
MovieList movieList;
TextView realsedate;
TextView overview;
TextView Language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils);
        name = findViewById(R.id.name_tv);
        poster = findViewById(R.id.poste_iv);
        realsedate = findViewById(R.id.realsedate_tv);
        overview = findViewById(R.id.overview_tv);
        Language = findViewById(R.id.language_tv);
        Intent intent=getIntent();
        movieList = (MovieList) intent.getSerializableExtra("deatils");
        name.setText("Title: "+movieList.getTitle());
        realsedate.setText("Realise Date: "+movieList.getReleaseDate());
        overview.setText("Movie OverView: "+movieList.getOverview());
        Language.setText("Language: "+movieList.getOriginalLanguage());
        Glide.with(this).load("https://image.tmdb.org/t/p/w640"+movieList.getPosterPath()).into(poster);

    }
}
