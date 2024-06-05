package com.awkrid.oauth2.member.dto

data class ProfileResponse(
    val id: Long,
    val username: String,
    val role: String,
)