package com.example.matarogran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.CursorAdapter
import android.widget.ListView
import androidx.appcompat.widget.SearchView

class ListAct : AppCompatActivity() {

    private val context = this
    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var adapter: CursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listView = findViewById(R.id.activitiesListView)

        adapter = ActivitatAdapter(context, Repository(context).getActivityCursor())

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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)

        supportActionBar!!.title = "Llista d'activitats"

        searchView = menu!!.findItem(R.id.searchView).actionView as SearchView
        initListeners()

        return super.onCreateOptionsMenu(menu)
    }

    private fun initListeners() {

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {

                    val queryCursor = Repository(context).searchByTitle(newText)
                    listView.adapter = ActivitatAdapter(context, queryCursor)
                    adapter.notifyDataSetChanged()

                }

                return true

            }


        })

    }

}
