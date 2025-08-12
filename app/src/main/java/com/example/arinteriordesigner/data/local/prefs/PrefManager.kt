package com.example.arinteriordesigner.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.arinteriordesigner.core.utils.Configs
import androidx.core.content.edit

object PrefManager {
    private const val PREF_NAME = Configs.PREF_APP_PREFS
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        if (!::prefs.isInitialized) {
            prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
    }

    // Boolean
    fun putBoolean(key: String, value: Boolean) {
        prefs.edit { putBoolean(key, value) }
    }
    fun getBoolean(key: String, default: Boolean = false): Boolean {
        return prefs.getBoolean(key, default)
    }

    // String
    fun putString(key: String, value: String) {
        prefs.edit { putString(key, value) }
    }
    fun getString(key: String, default: String? = null): String? {
        return prefs.getString(key, default)
    }

    // Int
    fun putInt(key: String, value: Int) {
        prefs.edit { putInt(key, value) }
    }
    fun getInt(key: String, default: Int = 0): Int {
        return prefs.getInt(key, default)
    }

    // Long
    fun putLong(key: String, value: Long) {
        prefs.edit { putLong(key, value) }
    }
    fun getLong(key: String, default: Long = 0L): Long {
        return prefs.getLong(key, default)
    }

    // Float
    fun putFloat(key: String, value: Float) {
        prefs.edit { putFloat(key, value) }
    }
    fun getFloat(key: String, default: Float = 0f): Float {
        return prefs.getFloat(key, default)
    }

    // String Set
    fun putStringSet(key: String, value: Set<String>) {
        prefs.edit { putStringSet(key, value) }
    }
    fun getStringSet(key: String, default: Set<String>? = null): Set<String>? {
        return prefs.getStringSet(key, default)
    }

    // Remove key
    fun remove(key: String) {
        prefs.edit { remove(key) }
    }

    // Clear all
    fun clear() {
        prefs.edit().clear().apply()
    }
}