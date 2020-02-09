package com.example.matarogran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class ListAct : AppCompatActivity() {

    private val context = this
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listView = findViewById(R.id.activitiesListView)

        val adapter = ActivitatAdapter(context, Repository(context).getActivityCursor())

        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->

            val activitats = Repository(context).getActivityCursor()

            activitats.moveToPosition(position)
            val id = activitats.getString(activitats.getColumnIndex("_id"))

            val intent = Intent(context, ActivitatDetail::class.java)
            intent.putExtra("id", id)
            startActivity(intent)

        }

    }



}
