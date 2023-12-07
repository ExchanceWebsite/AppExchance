package com.example.appexchance.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.appexchance.forms.models.RespostaDoServidor

class SharedPrefsManager(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    fun saveInfo(user: RespostaDoServidor) {
        editor.putInt(USER_ID, user.idEstudante)
        editor.putString(USER_NAME, user.nome)
        editor.putString(USER_EMAIL, user.email)
        editor.apply()
    }

    fun getInfo() = RespostaDoServidor(
        idEstudante = prefs.getInt(USER_ID, 0),
        nome = prefs.getString(USER_NAME, "") ?: "",
        email = prefs.getString(USER_EMAIL, "") ?: "",
        token = ""
    )

    fun deleteInfo() {
        editor.remove(USER_NAME)
        editor.remove(USER_NAME)
        editor.remove(USER_EMAIL)
    }

    private companion object {
        const val PREFS_TOKEN_FILE = "prefs_token_file"
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
        const val USER_EMAIL = "user_email"
    }
}