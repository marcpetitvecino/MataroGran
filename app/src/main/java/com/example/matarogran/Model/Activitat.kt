package com.example.matarogran.Model

data class Activitat (
    val activitatId: Int,
    val activitatTitol: String,
    val activitatDescripcio: String,
    val activitatImatges: String,
    val activitatDataInici: String,
    val activitatHoraInici: String,
    val activitatDuracio: Int,
    val activitatLocalitzacio: Localitzacio,
    val activitatDificultat: Int,
    val activitatCooperativa: Int,
    val activitatAccessibilitat: Int,
    val activitatExperiencia: Int
)