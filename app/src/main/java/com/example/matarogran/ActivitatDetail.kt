package com.example.matarogran

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.matarogran.Model.Activitat
import com.squareup.picasso.Picasso

class ActivitatDetail : AppCompatActivity() {

    private lateinit var activitat: Activitat
    private lateinit var imatgeActivitat: ImageView
    private lateinit var titol: TextView
    private lateinit var descripcio: TextView
    private lateinit var localitzacio: TextView
    private lateinit var durada: TextView
    private lateinit var buttonGo: TextView
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detall_activitat)
        this.supportActionBar!!.hide()
        initViews()
        loadData()
        initListeners()

    }

    private fun initViews() {

        imatgeActivitat = findViewById(R.id.detallImage)
        titol = findViewById(R.id.detailTitle)
        descripcio = findViewById(R.id.detailDescription)
        localitzacio = findViewById(R.id.detailLocalitzacio)
        buttonGo = findViewById(R.id.detailGoBtn)
        durada = findViewById(R.id.detailData)


    }

    private fun loadData() {

        val id = intent.extras!!.getString("id")!!.toInt()
        activitat = Repository(context).findActivityById(id)

        Picasso.get().load(activitat.imgDesc).into(imatgeActivitat);
        titol.text = activitat.titolActivitat
        descripcio.text = activitat.descActivitat
        localitzacio.text = activitat.localitzacio
        durada.text = "El ${activitat.startDate} a les ${activitat.startHour} \nDurada: ${activitat.duradaAprox}"

    }

    private fun initListeners() {

        buttonGo.setOnClickListener {

            val coordinatesUri = Uri.parse("geo:0,0?q=${activitat.lat},${activitat.lon}")
            val mapIntent = Intent(Intent.ACTION_VIEW, coordinatesUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }

    }


}
