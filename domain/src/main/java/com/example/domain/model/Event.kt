package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id : Int,
    val name: String,
    val eventDateTime: String,
    val description: String,
    val title :String,
    val createdAt :String
)