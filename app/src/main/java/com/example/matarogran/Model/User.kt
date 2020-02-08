package com.example.matarogran.Model

import com.example.matarogran.Enums.AccountType

data class User(
    val userId: Int,
    val userName: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val dataNaixement: String,
    val telefon: String,
    val accountType: AccountType,
    val logros: ArrayList<Logro>,
    val exp: Int,
    val city: String

    )