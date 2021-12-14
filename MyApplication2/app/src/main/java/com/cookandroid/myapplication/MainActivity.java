package com.cookandroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ModuleInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btn;

    LinearLayoutManager linearLayoutManager;
    MovieAdapter movieAdapter;
    List<Movie> list;
    private MovieInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= findViewById(R.id.button);
        recyclerView= findViewById(R.id.recyclerview);
        list= new ArrayList<>();

        linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        movieAdapter= new MovieAdapter(list);
        recyclerView.setAdapter(movieAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface= MovieClient.getClient().create(MovieInterface.class);
                Call<List<Movie>> call= apiInterface.doGetMovie();
                call.enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        Log.d("code",response.code()+"");
                        Log.d("data",response.toString()+"");
                        List<Movie> resource= response.body();
                        Log.d("count",resource.size()+"");

                        for(Movie movie:resource){
                            list.add(movie);
                        }
                        movieAdapter.notifyDataSetChanged(); //내용이 변경시 자동으로 경신
                        Log.d("count",movieAdapter.getItemCount()+"");
                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {

                    }
                });
            }
        });
    }
}