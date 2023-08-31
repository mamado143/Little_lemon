package com.example.little_lemon_project

interface Destinations {
    val route: String

    companion object {
        const val Onboarding = "Onboarding"
        const val Home = "Home"
        const val Profile = "Profile"
    }
}
object Onboarding : Destinations {
    override val route = "Onboarding"
}

object Home : Destinations {
    override val route = "Home"
}

object Profile : Destinations {
    override val route = "Profile"
}
