package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id :Int,
    val name :String,
    val email :String,
    val password :String,
    val avatar :String,
    val biography :String,
    val createdAt :String,
    val isFake :Boolean,
    val mobile :String,
    val dateOfBirth :String
)