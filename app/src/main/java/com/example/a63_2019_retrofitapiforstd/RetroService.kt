package com.example.a63_2019_retrofitapiforstd

import retrofit2.Call
import retrofit2.http.*

interface RetroService {
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 03b3d31b9a8048b8113967e8f471ef64fead0855ded9c1c61cd2f9891bd5e98c")
    fun getUserList(): Call<ArrayList<User>>

    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 03b3d31b9a8048b8113967e8f471ef64fead0855ded9c1c61cd2f9891bd5e98c")
    fun searchUsers(@Query("name") searchText:String):Call<ArrayList<User>>

    @GET("users{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUser(@Path("user_id")user_id:String):Call<ArrayList<User>>

    @POST("users")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 03b3d31b9a8048b8113967e8f471ef64fead0855ded9c1c61cd2f9891bd5e98c")
    fun createUser(@Body params: User): Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 03b3d31b9a8048b8113967e8f471ef64fead0855ded9c1c61cd2f9891bd5e98c")
    fun updateUser(@Path("user_id")user_id: String, @Body params: User): Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 03b3d31b9a8048b8113967e8f471ef64fead0855ded9c1c61cd2f9891bd5e98c")
    fun deleteUser(@Path("user_id") user_id: String): Call<UserResponse>
}

//Token:b4db9d2f720542172cbd26ef8b766ada5006fa8a4a106bd5335a4088fb5b9807 อาจารย์
//03b3d31b9a8048b8113967e8f471ef64fead0855ded9c1c61cd2f9891bd5e98c อาทิตย์