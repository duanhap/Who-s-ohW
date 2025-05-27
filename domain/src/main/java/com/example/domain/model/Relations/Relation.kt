package com.example.domain.model.Relations

import kotlinx.serialization.Serializable

@Serializable
data class Relation (
    val id: Int,
    val user1Id: Int,
    val user2Id: Int,
    val status: String,
    val createdAt: String,
)
