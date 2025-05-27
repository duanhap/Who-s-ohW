package com.example.domain.model.Relations

import com.example.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class RelationType (
    val id: Int,
    val relationId: Int,
    val relationName: String,
    val relationGroupId: Int,
    val user: User
)
