package com.antonia.perrosdelmundo.Network

import com.antonia.perrosdelmundo.Data.DogsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface APIService {


    @GET("hound/images")
    suspend fun getDogsByBreeds(): DogsResponse

    companion object{
        private var apiService: APIService? = null
        private var url:String = "https://dog.ceo/api/breed/"
        fun getInstance():APIService{
            if(apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(APIService::class.java)
            }
            return apiService!!
        }
    }
}