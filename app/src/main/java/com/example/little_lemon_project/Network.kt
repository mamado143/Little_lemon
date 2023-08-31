package com.example.little_lemon_project

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    // add code here
    @SerialName("menu")
    var menu: List<MenuItemNetwork>

)

@Serializable
data class MenuItemNetwork(
    // add code here
    @SerialName("id")
    var id    : Int?    = null,
    @SerialName("title")
    var title : String? = null,
    @SerialName("description")
    var description: String? = null,
    @SerialName("price")
    var price : String? = null,
    @SerialName("image")
    var image : String? = null,
    @SerialName("category")
    var category : String? = null
){
    fun toMenuItemRoom() = MenuItemRoom(
        id!!,
        title!!,
        description!!,
        price!!.toDouble(),
        image!!,
        category!!
    )
}