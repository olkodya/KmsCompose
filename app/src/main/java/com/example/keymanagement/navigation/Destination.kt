package com.example.keymanagement.navigation

sealed class Destination(val route: String) {
    object Login : Destination(route = "login")
    object Home : Destination(route = "home")

    companion object {
        fun getStartDestination() = Login.route
    }
}