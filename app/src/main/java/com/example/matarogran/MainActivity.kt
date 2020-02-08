package com.example.matarogran

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.matarogran.Interfaces.JsonPlaceHolderApi
import com.example.matarogran.Model.Activitat
import com.example.matarogran.Model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var userName: String
    private lateinit var userPassword: String
    private lateinit var loginBtn: Button
    private val context = this
    private val db = UsersRepository(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        initViews()
        initListeners()
        getData()

        return super.onCreateView(name, context, attrs)
    }

    private fun initViews() {

        userName = findViewById(R.id.loginUserName)
        userPassword = findViewById(R.id.loginPassword)
        loginBtn = findViewById(R.id.loginBtn)

    }

    private fun initListeners() {

        loginBtn.setOnClickListener {



        }

    }

    private fun getData() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://xaambo.github.io/JSONRestAPI/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        var callUsers: Call<List<User>> = jsonPlaceHolderApi.getUsers()
        var callActivities: Call<List<Activitat>> = jsonPlaceHolderApi.getActivities()

        callUsers.enqueue(object: Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>?, t: Throwable?) {

                Toast.makeText(context, "Hi ha hagut un error obtenint les dades", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>) {

                if (!response.isSuccessful) {

                    Toast.makeText(context, "Hi ha hagut un error obtenint les dades 2", Toast.LENGTH_SHORT).show()
                    return

                }

                val userList: List<User>? = response.body()

                if (userList != null) {

                    for (user in userList) {

                        db.insertUsers(user)

                    }

                }

            }


        })

        callActivities.enqueue(object: Callback<List<Activitat>> {
            override fun onFailure(call: Call<List<Activitat>>?, t: Throwable?) {
                Toast.makeText(context, "Hi ha hagut un error obtenint les dades", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Activitat>>?, response: Response<List<Activitat>>) {

                if (!response.isSuccessful) {

                    Toast.makeText(context, "Hi ha hagut un error obtenint les dades 2", Toast.LENGTH_SHORT).show()
                    return

                }

                val activityList: List<Activitat>? = response.body()

                if (activityList != null) {

                    for (activitat in activityList) {

                        db.insertActivities(activitat)

                    }

                }


            }


        })

    }

}
