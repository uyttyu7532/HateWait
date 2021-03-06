package com.example.hatewait.retrofit2

import com.example.hatewait.map.RetrofitMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyApi {

    private const val BASE_URL = "https://hatewait-server.herokuapp.com/"

    data class onlyMessageResponseData(var message:String)

    private fun retrofit(): Retrofit { // Singleton
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // JSON
            .build()
    }


    val RegisterService: RetrofitRegister by lazy {
        retrofit().create(RetrofitRegister::class.java)
    }


    val SignUpService: RetrofitSignUp by lazy {
        retrofit().create(RetrofitSignUp::class.java)
    }

    val LoginService: RetrofitLogin by lazy {
        retrofit().create(RetrofitLogin::class.java)
    }

    val WaitingService: RetrofitWaiting by lazy {
        retrofit().create(RetrofitWaiting::class.java)
    }

    val CouponService: RetrofitCoupon by lazy {
        retrofit().create(RetrofitCoupon::class.java)
    }

    val UpdateService: RetrofitInfoUpdate by lazy {
        retrofit().create(RetrofitInfoUpdate::class.java)
    }

    val MapService: RetrofitMap by lazy {
        retrofit().create(RetrofitMap::class.java)
    }

}
