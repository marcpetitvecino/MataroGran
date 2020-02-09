package com.example.matarogran.Interfaces

import com.example.matarogran.Model.Activitat
import com.example.matarogran.Model.User
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("cuentas.json")
    fun getUsers(): Call<List<User>>

    @GET("activitats.json")
    fun getActivities(): Call<List<Activitat>>

}