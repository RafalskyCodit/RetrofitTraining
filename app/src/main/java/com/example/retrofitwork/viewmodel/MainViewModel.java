package com.example.retrofitwork.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.retrofitwork.RetrofitAPI;
import com.example.retrofitwork.model.Answer;
import com.example.retrofitwork.model.Hit;

import java.util.List;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainViewModel extends ViewModel {
    private static final String BASE_URL = "https://pixabay.com/api/";
    private static final int PER_PAGE = 30;
    private int currentPage = 1;

    private MutableLiveData<List<Hit>> hits;

    private RetrofitAPI api;

    public MainViewModel(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitAPI.class);

        hits = new MutableLiveData<>();
    }

    public MutableLiveData<List<Hit>> getHits() {
        return hits;
    }

    public void getPictures(String imageRequest, String imageType, String order){
        api.getRequestResult(imageRequest, imageType, order, PER_PAGE, currentPage)
                .enqueue(new Callback<Answer>() {
                    @Override
                    public void onResponse(Response<Answer> response, Retrofit retrofit) {
                        if (response.isSuccess()){
                            hits.postValue(response.body().getHits());
                            currentPage++;
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
    }
}
