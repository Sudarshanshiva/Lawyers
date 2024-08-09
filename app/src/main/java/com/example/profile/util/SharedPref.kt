package com.example.profile.util

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SharedPref {

    fun storeData(
        email: String,
        password: String,
        userName: String,
        bio: String,
        image: String,
        context: Context
    ){
        val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.putString("userName", userName)
        editor.putString("bio", bio)
        editor.putString("image", image)
        editor.apply()
    }
    fun getUserName(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userName", "")!!
    }
    fun getEmail(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE)
        return sharedPreferences.getString("email", "")!!
    }
    fun getPassword(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE)
        return sharedPreferences.getString("password", "")!!
    }
    fun getBio(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE)
        return sharedPreferences.getString("bio", "")!!
    }

    fun getImage(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE)
        return sharedPreferences.getString("image", "")!!
    }


}