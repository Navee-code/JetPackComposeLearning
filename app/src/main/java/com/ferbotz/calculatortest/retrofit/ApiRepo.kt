package com.ferbotz.calculatortest.retrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun ApiRepo() {

    Client.instance.getPhotos().enqueue(object : Callback<List<Gallery>> {
        override fun onResponse(call: Call<List<Gallery>>, response: Response<List<Gallery>>) {
            if (response.isSuccessful) {
                response.body()?.let { photos ->
                    photos.forEach {
                        Log.d("MainActivity", "Photo: ${it.title}, URL: ${it.url}")
                    }
                }
            }
        }

        override fun onFailure(call: Call<List<Gallery>>, t: Throwable) {
            Log.e("MainActivity", "API Call Failed: ${t.message}")
        }
    })
}
