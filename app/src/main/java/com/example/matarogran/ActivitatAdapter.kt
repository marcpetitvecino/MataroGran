package com.example.matarogran

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlin.random.Random

class ActivitatAdapter(val context: Context, cursor: Cursor): CursorAdapter(context, cursor, true) {

    private lateinit var icona: ImageView
    private lateinit var titol: TextView
    private lateinit var isAccess: ImageView
    private lateinit var isCoop: ImageView
    private lateinit var difficulty: ImageView
    private lateinit var distancia: TextView

    override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View {

        return LayoutInflater.from(context).inflate(R.layout.cell_activitat, p2, false)

    }

    override fun bindView(p0: View?, p1: Context?, p2: Cursor?) {


        icona = p0!!.findViewById(R.id.accountIcon)
        titol = p0.findViewById(R.id.accountTitol)
        isAccess = p0.findViewById(R.id.accountIsAcces)
        isCoop = p0.findViewById(R.id.accountIsCoop)
        difficulty = p0.findViewById(R.id.accountDiff)
        distancia = p0.findViewById(R.id.accountDistancia)

        val iconString = cursor.getString(cursor.getColumnIndex(ACTIVITIES_IMAGE))
        val distanciaTotal = cursor.getString(cursor.getColumnIndex(ACTIVITIES_DISTANCIA))

        titol.text = cursor.getString(cursor.getColumnIndex(ACTIVITIES_TITLE))
        distancia.text = "Recorregut: $distanciaTotal"

        if (cursor.getInt(cursor.getColumnIndex(ACTIVITIES_ACCESSIBLE)) == 0) {

            isAccess.setImageResource(R.drawable.accessible_icon_disabled)

        } else {

            isAccess.setImageResource(R.drawable.accessible_icon_enabled)

        }

        if (cursor.getInt(cursor.getColumnIndex(ACTIVITIES_COOP)) == 0) {

            isCoop.setImageResource(R.drawable.coop_icon_disabled)

        } else {

            isCoop.setImageResource(R.drawable.coop_icon_enabled)

        }

        when (cursor.getInt(cursor.getColumnIndex(ACTIVITIES_DIFF))) {

            1 -> difficulty.setImageResource(R.drawable.ic_walker)
            2 -> difficulty.setImageResource(R.drawable.ic_hiker)
            else -> difficulty.setImageResource(R.drawable.ic_walker)

        }

    }


}