package com.example.matarogran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.matarogran.Interfaces.JsonPlaceHolderApi
import com.example.matarogran.Model.Activitat
import com.example.matarogran.Model.User
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var userPassword: EditText
    private lateinit var loginBtn: Button
    private val context = this
    private val db = Repository(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar!!.hide()
        initViews()
        initListeners()
        getData()
    }


    private fun initViews() {

        userName = findViewById(R.id.edtUser)
        userPassword = findViewById(R.id.edtPassword)
        loginBtn = findViewById(R.id.button)

    }

    private fun initListeners() {

        button.setOnClickListener {

            if (userName.text.toString() == "dev" && userPassword.text.toString() == "dev") {

                val intent = Intent(this, ListAct::class.java)
                startActivity(intent)

            } else {

                val dialog = AlertDialog.Builder(context)
                    .setTitle("Oops!")
                    .setMessage("Usuari o contrasenya incorrecte")
                val alert = dialog.create()
                alert.show()

            }



        }

    }

    private fun getData() {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://xaambo.github.io/JSONRestAPI/")
            .addConverterFactory(GsonConverterFactory.create(gson))
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
