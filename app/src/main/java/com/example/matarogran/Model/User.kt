package com.example.matarogran.Model

import com.example.matarogran.Enums.AccountType

data class User(
    val cuentaId: String,
    val nom: String,
    val cognoms: String,
    val user: String,
    val password: String,
    val ciutat: String,
    val telefon: String,
    val accountType: Int,
    val logros: String,
    val nivell: Int
    )