package com.example.taller1.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val image: String,
    val age: Int,
    val gender: String,
    val height: Double,
    val weight: Double,
    val email: String,
    val university: String,
    val company: Company
)