package com.example.profile.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.profile.screens.ProfileScreen.data.ProfileModel
import com.example.profile.util.PSharedPref
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.storage

class ProfileViewModel: ViewModel() {
    private val _profilefirebase = MutableLiveData<FirebaseUser?>()
    val profilefirebase: LiveData<FirebaseUser?> = _profilefirebase

    val pauth = FirebaseAuth.getInstance()

    private val pdb = FirebaseDatabase.getInstance()
    val profileRef = pdb.getReference("profile")

    private val storageRef = Firebase.storage.reference
    val imageRef = storageRef.child("profile/${pauth.currentUser!!.uid}.jpg")
    val imageBarRef = storageRef.child("profile/bar/${pauth.currentUser!!.uid}.jpg")
    val imageAadharRef = storageRef.child("profile/aadhar/${pauth.currentUser!!.uid}.jpg")

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        _profilefirebase.value = pauth.currentUser
    }

     fun saveImage(
        image: Uri,
        userName: String,
        specialization: String,
        city: String,
        gender: String,
        experience: String,
        imgBar: Uri,
        imgAadhar: Uri,
        uid: String?,
        context: Context
    ) {
        val uploadTask1 = imageRef.putFile(image)
        uploadTask1.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                SavePData(
                    it,
                    userName,
                    specialization,
                    city,
                    gender,
                    experience,
                    imgBar,
                    imgAadhar,
                    uid,
                    context
                )
            }

        }

    }

    private fun SavePData(
        it: Uri?,
        userName: String,
        specialization: String,
        city: String,
        gender: String,
        experience: String,
        imgBar: Uri,
        imgAadhar: Uri,
        uid: String?,
        context: Context
    ) {
         val profileData = ProfileModel(it.toString(),userName,specialization,city,gender,experience,imgBar.toString(),imgAadhar.toString(),uid!!)
        profileRef.child(uid!!).setValue(profileData)
            .addOnSuccessListener {
                PSharedPref.storeData(
                    it.toString(),
                    userName,
                    specialization,
                    city,
                    gender,
                    experience,
                    imgBar.toString(),
                    imgAadhar.toString(),
                    context
                )
            }
            .addOnFailureListener {

            }
        }
    }


