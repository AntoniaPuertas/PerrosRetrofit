package com.antonia.perrosdelmundo.Network

import com.antonia.perrosdelmundo.Data.DogsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface APIService {


    @GET("hound/images")
    suspend fun getDogsByBreeds(): DogsResponse


    companion object{
        // 2. Crea el interceptor y configura el nivel de logging
        val logging = HttpLoggingInterceptor().apply {
            level = Level.BODY  // Muestra el cuerpo completo de peticiones y respuestas
            // Otros niveles disponibles:
            // Level.BASIC    - Solo logs de peticiones y respuestas
            // Level.HEADERS  - Logs de cabeceras
            // Level.NONE     - Sin logs
        }

        // 3. Configura el cliente OkHttp con el interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        private var apiService: APIService? = null
        private var url:String = "https://dog.ceo/api/breed/"
        fun getInstance():APIService{
            if(apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(APIService::class.java)
            }
            return apiService!!
        }
    }
}