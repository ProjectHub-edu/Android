package com.project.hub.domain.model

data class UserData(
    val __v: Int,
    val _id: String,
    val basket: List<Any>,
    val cart: List<Any>,
    val email: String,
    val likedItems: List<Any>,
    val orders: List<Any>,
    val phone: String,
    val role: String,
    val userName: String
)