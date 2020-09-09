package com.example.hatewait


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {
    @Headers("Authorization: KakaoAK 76abaa10a56836ded479aaec76eb6df3")
    @GET("/v2/local/search/category.json")
    fun requestSearchRestaurant(
        @Query("category_group_code") category_group_code: String = "FD6",
//        @Query("x") x: Double,
//        @Query("y") y: Double,
//        @Query("radius") radius: Int,
        @Query("rect") rect: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 15
//        @Query("sort") sort: String = "distance"
    
    ): Call<Restaurant>
}