package com.example.matarogran

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.matarogran.Model.Activitat
import com.example.matarogran.Model.User

val DATABASE_NAME = "MATAROGRAN"

// TAULA USERS

val USERS_TABLENAME = "Usuaris"
val USERS_INDEX = "_id"
val USERS_ID = "userId"
val USERS_FIRSTNAME = "FirstName"
val USERS_LASTNAME = "LastName"
val USERS_USERNAME = "Username"
val USERS_PASSWORD = "Password"
val USERS_PHONE = "Tel"
val USERS_CITY = "City"
val USERS_EXP = "Exp"
val USERS_ACCOUNTTYPE = "AccountType"

// TAULA ACTIVITATS

val ACTIVITIES_TABLENAME = "Activitats"
val ACTIVITIES_INDEX = "_id"
val ACTIVITIES_ID = "activitatId"
val ACTIVITIES_TITLE = "Titol"
val ACTIVITIES_DESCRIPTION = "Descripcio"
val ACTIVITIES_IMAGE = "ImageURI"
val ACTIVITIES_LATIDUTE = "Latitude"
val ACTIVITIES_LOCATION = "Location"
val ACTIVITIES_LONGITUDE = "Longitude"
val ACTIVITIES_ACCESSIBLE = "isAcessible"
val ACTIVITIES_COOP = "isCoop"
val ACTIVITIES_EXP = "Exp"
val ACTIVITIES_DIFF = "Dificultat"
val ACTIVITIES_STARTDATE = "StartDate"
val ACTIVITIES_STARTHOUR = "StartHour"
val ACTIVITIES_APROXLENGHT = "DuradaAprox"
val ACTIVITIES_DISTANCIA = "DistanciaActivitat"



class Repository(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {


        val tableUsers = "CREATE TABLE IF NOT EXISTS " + USERS_TABLENAME +" (" +
                USERS_INDEX +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERS_ID +" VARCHAR(80), " +
                USERS_FIRSTNAME + " VARCHAR(80), " +
                USERS_LASTNAME + " VARCHAR(80), " +
                USERS_USERNAME + " VARCHAR(80), " +
                USERS_PASSWORD + " VARCHAR(300)," +
                USERS_PHONE + " VARCHAR(80), " +
                USERS_CITY + " VARCHAR(80), " +
                USERS_EXP + " INTEGER, " +
                USERS_ACCOUNTTYPE + " INTEGER)";

        val tableActivities = "CREATE TABLE IF NOT EXISTS " + ACTIVITIES_TABLENAME +" (" +
                ACTIVITIES_INDEX +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ACTIVITIES_ID +" VARCHAR(80), " +
                ACTIVITIES_TITLE + " VARCHAR(80), " +
                ACTIVITIES_DESCRIPTION + " TEXT, " +
                ACTIVITIES_IMAGE + " VARCHAR(200), " +
                ACTIVITIES_LATIDUTE + " VARCHAR(80), " +
                ACTIVITIES_LONGITUDE + " VARCHAR(80), " +
                ACTIVITIES_LOCATION + " VARCHAR(80), " +
                ACTIVITIES_ACCESSIBLE + " INT, " +
                ACTIVITIES_COOP + " INT, " +
                ACTIVITIES_EXP + " INT, " +
                ACTIVITIES_DIFF + " INT, " +
                ACTIVITIES_STARTDATE + " VARCHAR(80), " +
                ACTIVITIES_STARTHOUR + " VARCHAR(80), " +
                ACTIVITIES_APROXLENGHT + " VARCHAR(80), " +
                ACTIVITIES_DISTANCIA + " VARCHAR(80))";

        p0!!.execSQL(tableUsers)
        p0.execSQL(tableActivities)


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertUsers(user: User) {

        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(USERS_ID, user.cuentaId)
        cv.put(USERS_FIRSTNAME, user.nom)
        cv.put(USERS_LASTNAME, user.cognoms)
        cv.put(USERS_USERNAME, user.user)
        cv.put(USERS_PASSWORD, user.password)
        cv.put(USERS_PHONE, user.telefon)
        cv.put(USERS_CITY, user.ciutat)
        cv.put(USERS_EXP, user.nivell)
        cv.put(USERS_ACCOUNTTYPE, user.accountType)

        db.insert(USERS_TABLENAME, null, cv)

    }

    fun insertActivities(activity: Activitat) {

        val db = this.writableDatabase
        val cv = ContentValues()

        if (!checkIfExists(activity.activitatId)) {

            cv.put(ACTIVITIES_ID, activity.activitatId)
            cv.put(ACTIVITIES_TITLE, activity.titolActivitat)
            cv.put(ACTIVITIES_DESCRIPTION, activity.descActivitat)
            cv.put(ACTIVITIES_IMAGE, activity.imgDesc)
            cv.put(ACTIVITIES_LOCATION, activity.localitzacio)
            cv.put(ACTIVITIES_LATIDUTE, activity.lat)
            cv.put(ACTIVITIES_LONGITUDE, activity.lon)
            cv.put(ACTIVITIES_ACCESSIBLE, activity.accesible)
            cv.put(ACTIVITIES_COOP, activity.cooperativa)
            cv.put(ACTIVITIES_EXP, activity.exp)
            cv.put(ACTIVITIES_DIFF, activity.dificultat)
            cv.put(ACTIVITIES_STARTDATE, activity.startDate)
            cv.put(ACTIVITIES_STARTHOUR, activity.startHour)
            cv.put(ACTIVITIES_APROXLENGHT, activity.duradaAprox)
            cv.put(ACTIVITIES_DISTANCIA, activity.distanciaRecorreguda)

            db.insert(ACTIVITIES_TABLENAME, null, cv)

        }


    }

    fun getActivityCursor(): Cursor {

        val db = this.readableDatabase
        val query = "SELECT * FROM $ACTIVITIES_TABLENAME ORDER BY $ACTIVITIES_STARTDATE"

        val result = db.rawQuery(query, null)

        return result


    }

    fun getUsersCursor(): Cursor {

        val db = this.readableDatabase
        val query = "SELECT * FROM $USERS_TABLENAME"

        val result = db.rawQuery(query, null)

        return result

    }

    fun cursorToActivity(c: Cursor): Activitat? {

        if (c.count > 0) {

            val activitatId = c.getString(c.getColumnIndex(ACTIVITIES_ID))
            val titolActivitat = c.getString(c.getColumnIndex(ACTIVITIES_TITLE))
            val descActivitat = c.getString(c.getColumnIndex(ACTIVITIES_DESCRIPTION))
            val imgDesc = c.getString(c.getColumnIndex(ACTIVITIES_IMAGE))
            val localitzacio = c.getString(c.getColumnIndex(ACTIVITIES_LOCATION))
            val lat = c.getString(c.getColumnIndex(ACTIVITIES_LATIDUTE))
            val lon: String = c.getString(c.getColumnIndex(ACTIVITIES_LONGITUDE))
            val dificultat = c.getInt(c.getColumnIndex(ACTIVITIES_DIFF))
            val accesible = c.getInt(c.getColumnIndex(ACTIVITIES_ACCESSIBLE))
            val cooperativa = c.getInt(c.getColumnIndex(ACTIVITIES_COOP))
            val exp = c.getInt(c.getColumnIndex(ACTIVITIES_EXP))
            val startDate = c.getString(c.getColumnIndex(ACTIVITIES_STARTDATE))
            val startHour = c.getString(c.getColumnIndex(ACTIVITIES_STARTHOUR))
            val duradaAprox = c.getString(c.getColumnIndex(ACTIVITIES_APROXLENGHT))
            val distanciaRecorreguda = c.getString(c.getColumnIndex(ACTIVITIES_DISTANCIA))

            return Activitat(
                activitatId,
                titolActivitat,
                descActivitat,
                imgDesc,
                localitzacio,
                lat,
                lon,
                dificultat,
                accesible,
                cooperativa,
                exp,
                0,
                startDate,
                startHour,
                duradaAprox,
                distanciaRecorreguda
            )

        }

        return null

    }

    fun findActivityById(id: Int): Activitat {

        val db = this.readableDatabase
        val query = "SELECT * FROM $ACTIVITIES_TABLENAME WHERE $ACTIVITIES_INDEX = '$id'"

        val cursor = db.rawQuery(query, null)

        cursor.moveToFirst()

        val retorn = cursorToActivity(cursor)!!

        cursor.close()

        return retorn

    }

    fun checkIfExists(id: String): Boolean {

        val db = this.writableDatabase
        val query = "SELECT * FROM $ACTIVITIES_TABLENAME WHERE $ACTIVITIES_ID = '$id'"
        val cursor = db.rawQuery(query, null)

        return cursor.moveToFirst()

    }

    fun searchByTitle(title: String): Cursor {

        val db = this.readableDatabase
        val query = "SELECT * FROM $ACTIVITIES_TABLENAME WHERE $ACTIVITIES_DESCRIPTION LIKE '%$title%'"

        val result = db.rawQuery(query, null)

        return result

    }

}