package com.beasthub.greedycashandearn.sharedprefsection

import android.content.Context

// Define a generic function to insert data into SharedPreferences
fun <T> putValueInSharedPreferences(
    context: Context,
    key: String,
    value: T
) {
    val sharedPreferences = context.getSharedPreferences("helo_king", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    when (value) {
        is String -> editor.putString(key, value)
        is Int -> editor.putInt(key, value)
        is Long -> editor.putLong(key, value)
        is Float -> editor.putFloat(key, value)
        is Boolean -> editor.putBoolean(key, value)
        else -> throw IllegalArgumentException("Unsupported data type")
    }

    editor.apply()
}

// Define a generic function to get data from SharedPreferences
inline fun <reified T : Any> getValueFromSharedPreferences(
    context: Context,
    key: String,
    defaultValue: T
): T {
    val sharedPreferences = context.getSharedPreferences("helo_king", Context.MODE_PRIVATE)

    return when (T::class) {
        String::class -> sharedPreferences.getString(key, defaultValue as String) as T
        Int::class -> sharedPreferences.getInt(key, defaultValue as Int) as T
        Long::class -> sharedPreferences.getLong(key, defaultValue as Long) as T
        Float::class -> sharedPreferences.getFloat(key, defaultValue as Float) as T
        Boolean::class -> sharedPreferences.getBoolean(key, defaultValue as Boolean) as T
        else -> throw IllegalArgumentException("Unsupported data type")
    }
}

// Define a function to remove a key from SharedPreferences
fun removeKeyFromSharedPreferences(
    context: Context,
    prefFileName: String,
    key: String
) {
    val sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.remove(key)
    editor.apply()
}

// Example usage:
//fun exampleUsage(context: Context) {
//    val prefFileName = "MyPrefs"
//    val key = "username"
//
//    // Insert data into SharedPreferences
//    putValueInSharedPreferences(context, prefFileName, key, "john_doe")
//
//    // Get data from SharedPreferences
//    val username = getValueFromSharedPreferences(context, prefFileName, key, "")
//
//    // Remove a key from SharedPreferences
//    removeKeyFromSharedPreferences(context, prefFileName, key)
//}
