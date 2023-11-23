package com.beasthub.greedycashandearn.sharedprefsection

import android.content.Context
import android.content.SharedPreferences

class UserSharePreferancess(ctx: Context) {
    private val editor: SharedPreferences.Editor
    var context: Context

    init {
        settings = ctx.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)
        editor = settings.edit()
        context = ctx
    }

    fun setStringValue(key: String, value: String?): String {
        editor.putString(key, value)
        editor.commit()
        return key
    }

    fun setIntValue(key: String?, value: Int) {
        editor.putInt(key, value)
        editor.commit()
    }

    fun getStringValue(key: String?): String? {
        return settings.getString(key, "")
    }

    companion object {
        lateinit var settings: SharedPreferences
        private const val FILENAME = "HeloKings"

        fun getIntValue(key: String?): Int {
            return settings.getInt(key, 0)
        }
    }
}