package com.awkrid.oauth2.member

data class Member(
    val id: Long,
    val username: String,
    val kakaoAccessToken: String,
    val role : String,
)