package com.example.profile.util

import android.content.Context
import android.content.Context.MODE_PRIVATE


object PSharedPref {

    fun storeData(
    image:String,
    userName:String,
    specialization:String,
    city:String,
    gender:String,
    experience:String,
    imgBar:String,
    imgAadhar:String,
    context: Context

    ){
        val psharedPreferences = context.getSharedPreferences("profile", MODE_PRIVATE)
        val editor = psharedPreferences.edit()
        editor.putString("image",image)
        editor.putString("userName",userName)
        editor.putString("specialization",specialization)
        editor.putString("city",city)
        editor.putString("gender",gender)
        editor.putString("experience",experience)
        editor.putString("imgBar",imgBar)
        editor.putString("imgAadhar",imgAadhar)
        editor.apply()

    }
    fun getImage(context: Context):String{
        val psharedPreferences = context.getSharedPreferences("profile", MODE_PRIVATE)
        return psharedPreferences.getString("image", "")!!

    }
    fun getUserName(context: Context):String{
        val psharedPreferences = context.getSharedPreferences("profile", MODE_PRIVATE)
        return psharedPreferences.getString("userName", "")!!
    }
    fun getSpecialization(context: Context):String{
        val psharedPreferences = context.getSharedPreferences("profile", MODE_PRIVATE)
        return psharedPreferences.getString("specialization", "")!!

    }
    fun getCity(context: Context):String {
        val psharedPreferences = context.getSharedPreferences("profile", MODE_PRIVATE)
        return psharedPreferences.getString("city", "")!!
    }
    fun getGender(context: Context):String {
        val psharedPreferences = context.getSharedPreferences("profile", MODE_PRIVATE)
        return psharedPreferences.getString("gender", "")!!
    }
    fun getExperience(context: Context):String {
        val psharedPreferences = context.getSharedPreferences("profile", MODE_PRIVATE)
        return psharedPreferences.getString("experience", "")!!
    }
    fun getImgBar(context: Context):String {
        val psharedPreferences = context.getSharedPreferences("profile", MODE_PRIVATE)
        return psharedPreferences.getString("imgBar", "")!!
    }
    fun getImgAadhar(context: Context):String {
        val psharedPreferences = context.getSharedPreferences("profile", MODE_PRIVATE)
        return psharedPreferences.getString("imgAadhar", "")!!
    }

}