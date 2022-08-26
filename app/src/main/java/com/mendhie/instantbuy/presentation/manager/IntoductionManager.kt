package com.mendhie.instantbuy.presentation.manager

import android.content.Context
import android.content.SharedPreferences

class IntroductionManager(context: Context) {
    private var pref: SharedPreferences =
        context.getSharedPreferences("${context.applicationContext.packageName}_preferences",
        Context.MODE_PRIVATE)

    private var editor: SharedPreferences.Editor = pref.edit()
    fun setFirst(isFirst: Boolean) {
        editor.putBoolean("check", isFirst)
        editor.apply()
    }

    fun check(): Boolean {
        return pref.getBoolean("check", false)
    }

}