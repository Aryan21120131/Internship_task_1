package com.example.internship_task_1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder {

    @GET("crew")
    Call<List<Post>> getPost();
}
