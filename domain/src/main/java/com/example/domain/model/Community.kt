package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Community (
    val id :Int,
    val name :String,
    val image :String,
    val description :String,
    val createdByUser: Int,
    val createdAt :String,
    val isActiveEvent :Boolean,
    val memberCount :Int,

)

