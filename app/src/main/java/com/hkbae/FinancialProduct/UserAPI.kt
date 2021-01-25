package com.hkbae.FinancialProduct


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserAPI {

    @GET("/user/exist/")
    fun getUserCount( //id에 따른 사용자 정보 요청
        @Query("id") id : String
    ) : Call<Int>

    @POST("/user/")
    fun postUser( //사용자 정보 insert 요청
        @Body user : User
    ):Call<Int>

    companion object {
        const val SERVER_URL: String = "http://172.26.30.249:8080"
            //"http://172.26.30.249:8080"
    }


}