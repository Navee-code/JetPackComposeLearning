package com.ferbotz.calculatortest.retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Client {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val instance: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}



