package com.example.internship_task_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] name={"A","B","C","D","E"};
        String[] agency={"A","B","C","D","E"};
        String[] wikipedia={"A","B","C","D","E"};
        boolean[] status={true,false,true,false,true};
        int[] img={R.drawable.space_x_icon,R.drawable.spacex_logo,R.drawable.space_x_icon,R.drawable.spacex_logo,R.drawable.space_x_icon};

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholder jsonPlaceholder=retrofit.create(JSONPlaceholder.class);
        Call<List<Post>> call=jsonPlaceholder.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> postList=response.body();
                Adapter adapter=new Adapter(getApplicationContext(),postList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }
}