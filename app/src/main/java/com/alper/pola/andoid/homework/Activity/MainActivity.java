package com.alper.pola.andoid.homework.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.alper.pola.andoid.homework.adapter.ImageAdapter;
import com.alper.pola.andoid.homework.model.MovieList;
import com.alper.pola.andoid.homework.model.MovieModel;
import com.alper.pola.andoid.homework.R;
import com.alper.pola.andoid.homework.util.RecyclerItemClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageAdapter imageAdapter;
    EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        search = findViewById(R.id.searchedt);
        setCardList();
        search();

        MovieModel movieModel = MovieModel.getInstance(this);
        movieModel.getSearch(this, new MovieModel.GetSearchListCallBack() {
            @Override
            public void onSuccess(final ArrayList<MovieList> movieLists) {
                Log.d("movielist", movieLists.toString());
                imageAdapter = new ImageAdapter(MainActivity.this, movieLists);
                recyclerView.setAdapter(imageAdapter);
                recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        MovieList movieList = movieLists.get(position);
                        Intent intent =new Intent(MainActivity.this,DeatilsActivity.class);
                        intent.putExtra("deatils", movieList);
                        startActivity(intent);
                    }
                }));

            }

            @Override
            public void onFail(String message) {

            }
        });


    }

    public void setCardList() {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public void search() {
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_SEARCH) {

                    Intent intent1 = new Intent(MainActivity.this, Search_Activity.class);
                    intent1.putExtra("search", search.getText().toString());
                    search.setText("");

                    startActivity(intent1);


                    return true;
                }
                return false;
            }

        });
    }
}
