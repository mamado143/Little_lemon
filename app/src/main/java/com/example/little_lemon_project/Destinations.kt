package com.example.little_lemon_project

interface Destinations {
    val route: String
}

object Home: Destinations {
    override val route: String
        get() = "Home"
}

object Onboarding: Destinations {
    override val route: String
        get() = "Onboarding"
}

object Profile: Destinations {
    override val route: String
        get() = "Profile"
}