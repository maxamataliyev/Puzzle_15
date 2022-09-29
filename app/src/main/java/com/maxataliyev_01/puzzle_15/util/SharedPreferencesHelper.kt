package com.maxataliyev_01.puzzle_15.util


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Build
import java.util.*
import kotlin.collections.ArrayList



class SharedPreferencesHelper(var context: Context) {

    private var preferences: SharedPreferences

    private lateinit var editor: SharedPreferences.Editor

    init {
        preferences = context.getSharedPreferences("APP_PREFS_NAME", MODE_PRIVATE)
    }

    /////////////////////////////
    fun setTime(second: Int) {
        editor = preferences.edit()
        editor.putInt("SECOND", second)
        editor.apply()
    }

    fun getSecond() = preferences.getInt("SECOND", 0)

    fun setNightMode(isNightMode: Boolean) {
        editor = preferences.edit()
        editor.putBoolean("IS_NIGHT", isNightMode)
        editor.apply()
    }

    fun getNightMode() = preferences.getBoolean("IS_NIGHT", false)

    fun clearTime() {
        preferences.edit().remove("SECOND").apply()
    }

    fun clearAllData() {
        preferences.edit().clear().apply()
    }

    fun getLanguage() = preferences.getString("LANG", "ru")

    fun loadLocale(context: Context) {
        setLanguage(getLanguage()!!, context)
    }

    fun setLanguage(lang: String, context: Context) {
        editor = preferences.edit()
        editor.putString("LANG", lang)
        editor.apply()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, lang)
        }
        updateResourcesLegacy(context, lang)
    }

    private fun updateResources(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)//bu joyni uchirib kor
        return context.createConfigurationContext(configuration)
    }


    private fun updateResourcesLegacy(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }

    fun setAllNumbers(numbers: ArrayList<Int>) {
        editor = preferences.edit()

        numbers.forEachIndexed { index, number ->
            editor.putInt("NUMBER_${index}", number)
        }
        editor.apply()
    }

    fun getAllNumbers(): ArrayList<Int> {
        val numbers = ArrayList<Int>()
        var t = 1
        for (i in 0..15) {
            if (i == 15) {
                numbers.add(preferences.getInt("NUMBER_${i}", -1))
            } else {
                numbers.add(preferences.getInt("NUMBER_${i}", t++))
            }
        }
        return numbers
    }

}
