package com.example.retrofitwork;

import com.example.retrofitwork.model.Answer;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RetrofitAPI {
    @GET("?key=12277686-7e23a4b73c1e6604427408604")
    Call<Answer> getRequestResult(@Query("q") String q,
                                  @Query("image_type") String imageType,
                                  @Query("order") String order,
                                  @Query("per_page") int perPage,
                                  @Query("page") int page);
}
