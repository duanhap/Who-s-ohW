package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Feed (
    val id : Int,
    val userId: Int,
    val communityId: Int,
    val nameUser: String,
    val imageUser: String,
    val nameCommunity: String,
    val content : String,
    val media: String,
    val likeCount: Int,
    val commentCount: Int,
    val createdAt :String
)
