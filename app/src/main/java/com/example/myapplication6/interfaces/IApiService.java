package com.example.myapplication6.interfaces;

import com.example.myapplication6.model.Rss;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IApiService {
    @GET("rss")
    Observable<Rss> getRss();
}
