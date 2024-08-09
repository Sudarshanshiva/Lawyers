package com.example.profile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.profile.route.Routes
import com.example.profile.screens.Home
import com.example.profile.screens.AuthenticateScreen.Login
import com.example.profile.screens.Profile
import com.example.profile.screens.AuthenticateScreen.Register
import com.example.profile.screens.ProfileScreen.screens.Profilescreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Login.routes){
        composable(Routes.Login.routes){
            Login(navController)
        }
        composable(Routes.Register.routes){
            Register(navController)
        }
        composable(Routes.Home.routes){
            Home(navController)
        }
        composable(Routes.Profile.routes){
            Profile(navController)
        }
        composable(Routes.Profilescreen.routes){
            Profilescreen(navController)
        }

    }
}