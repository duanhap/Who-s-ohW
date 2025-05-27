package com.example.domain.model.Relations

import kotlinx.serialization.Serializable

@Serializable
data class RelationGroup (
    val id: Int,
    val name : String,
    val userID: Int,
    val isActiveEvent: Boolean,
    val relationTypes: List<RelationType>
)
