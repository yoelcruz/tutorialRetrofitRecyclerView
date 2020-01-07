package com.vogella.android.tutorialretrofitrecyclerview.webservice;

import com.vogella.android.tutorialretrofitrecyclerview.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServiceClient {

    @GET("people")
    Call<Data> getPersonajes();
}
