package com.riane.navigation

sealed class Route (val path: String){
    object Home : Route("home")
    object Profile : Route("profile/{userId}") {
        fun createRoute(userId: String) = "profile/$userId"
    }
}