package com.awkrid.oauth2.domain.oauth2.dto

data class LoginResponse(
    val accessToken: String,
    val id : Long,
    val username: String,
    val role: String,
)
