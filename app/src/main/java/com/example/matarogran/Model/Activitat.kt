package com.example.matarogran.Model

data class Activitat (
    val activitatId: String,
    val titolActivitat: String,
    val descActivitat: String,
    val imgDesc: String,
    val localitzacio: String,
    val lat: String,
    val lon: String,
    val dificultat: Int,
    val accesible: Int,
    val cooperativa: Int,
    val exp: Int,
    val starPlatinum: Int = 0,
    val startDate: String,
    val startHour: String,
    val duradaAprox: String,
    val distanciaRecorreguda: String
)