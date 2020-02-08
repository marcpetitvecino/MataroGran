package com.example.matarogran.Interfaces

import com.example.matarogran.Model.Activitat
import com.example.matarogran.Model.User
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("cuentas")
    fun getUsers(): Call<List<User>>

    @GET("activitats")
    fun getActivities(): Call<List<Activitat>>


}