package com.example.matarogran

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.matarogran.Model.Activitat
import com.example.matarogran.Model.User

val DATABASE_NAME = "MATAROGRAN"

// TAULA USERS

val USERS_TABLENAME = "Usuaris"
val USERS_ID = "_id"
val USERS_FIRSTNAME = "FirstName"
val USERS_LASTNAME = "LastName"
val USERS_BIRTHDATE = "BirthDate"
val USERS_USERNAME = "Username"
val USERS_PASSWORD = "Password"
val USERS_PHONE = "Tel"
val USERS_CITY = "City"
val USERS_EXP = "Exp"
val USERS_ACCOUNTTYPE = "AccountType"

// TAULA ACTIVITATS

val ACTIVITIES_TABLENAME = "Activitats"
val ACTIVITIES_ID = "_id"
val ACTIVITIES_TITLE = "Titol"
val ACTIVITIES_DESCRIPTION = "Descripcio"
val ACTIVITIES_IMAGE = "ImageURI"
val ACTIVITIES_LATIDUTE = "Latitude"
val ACTIVITIES_LONGITUDE = "Longitude"
val ACTIVITIES_ACCESSIBLE = "isAcessible"
val ACTIVITIES_COOP = "isCoop"
val ACTIVITIES_EXP = "Exp"
val ACTIVITIES_STARTDATE = "StartDate"
val ACTIVITIES_STARTHOUR = "StartHour"
val ACTIVITIES_APROXLENGHT = "DuradaAprox"



class UsersRepository(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {

        val tableUsers = "CREATE TABLE IF NOT EXISTS " + USERS_TABLENAME +" (" +
                USERS_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERS_FIRSTNAME + " VARCHAR(80), " +
                USERS_LASTNAME + " VARCHAR(80), " +
                USERS_USERNAME + " VARCHAR(80), " +
                USERS_PASSWORD + " VARCHAR(300)," +
                USERS_BIRTHDATE + " VARCHAR(80), " +
                USERS_PHONE + " VARCHAR(80), " +
                USERS_CITY + " VARCHAR(80), " +
                USERS_EXP + " INTEGER, " +
                USERS_ACCOUNTTYPE + " INTEGER)";

        val tableActivities = "CREATE TABLE IF NOT EXISTS " + ACTIVITIES_TABLENAME +" (" +
                ACTIVITIES_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ACTIVITIES_TITLE + " VARCHAR(80), " +
                ACTIVITIES_DESCRIPTION + " TEXT, " +
                ACTIVITIES_IMAGE + " VARCHAR(200), " +
                ACTIVITIES_LATIDUTE + " DOUBLE, " +
                ACTIVITIES_LONGITUDE + " DOUBLE, " +
                ACTIVITIES_ACCESSIBLE + " INT, " +
                ACTIVITIES_COOP + " INT, " +
                ACTIVITIES_EXP + " INT, " +
                ACTIVITIES_STARTDATE + " VARCHAR(80), " +
                ACTIVITIES_STARTHOUR + " VARCHAR(80), " +
                ACTIVITIES_APROXLENGHT + " VARCHAR(80))";


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertUsers(user: User) {

        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(USERS_ID, user.userId)
        cv.put(USERS_FIRSTNAME, user.firstName)
        cv.put(USERS_LASTNAME, user.lastName)
        cv.put(USERS_BIRTHDATE, user.dataNaixement)
        cv.put(USERS_USERNAME, user.userName)
        cv.put(USERS_PASSWORD, user.password)
        cv.put(USERS_PHONE, user.telefon)
        cv.put(USERS_CITY, user.city)
        cv.put(USERS_EXP, user.exp)
        cv.put(USERS_ACCOUNTTYPE, user.accountType.accountType)

        db.insert(USERS_TABLENAME, null, cv)

    }

    fun insertActivities(activity: Activitat) {

        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(ACTIVITIES_ID, activity.activitatId)
        cv.put(ACTIVITIES_TITLE, activity.activitatTitol)
        cv.put(ACTIVITIES_DESCRIPTION, activity.activitatDescripcio)
        cv.put(ACTIVITIES_IMAGE, activity.activitatImatges)
        cv.put(ACTIVITIES_LATIDUTE, activity.activitatLocalitzacio.localitzacioLatitud)
        cv.put(ACTIVITIES_LONGITUDE, activity.activitatLocalitzacio.localitzacioLongitud)
        cv.put(ACTIVITIES_ACCESSIBLE, activity.activitatAccessibilitat)
        cv.put(ACTIVITIES_COOP, activity.activitatCooperativa)
        cv.put(ACTIVITIES_EXP, activity.activitatExperiencia)
        cv.put(ACTIVITIES_STARTDATE, activity.activitatDataInici)
        cv.put(ACTIVITIES_STARTHOUR, activity.activitatHoraInici)
        cv.put(ACTIVITIES_APROXLENGHT, activity.activitatDuracio)

        db.insert(USERS_TABLENAME, null, cv)


    }

}