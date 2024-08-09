package com.example.profile.route

sealed class Routes (val routes: String){
    object Login: Routes("login")
    object Register: Routes("register")
    object Home: Routes("home")
    object Profile: Routes("profile")
    object Profilescreen: Routes("profileScreen")
}