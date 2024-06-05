package com.awkrid.oauth2.domain.oauth2.dto

import com.fasterxml.jackson.annotation.JsonProperty

class KakaoToken (
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("expires_in") val expiresIn: String,
    @JsonProperty("refresh_token") val refreshToken: String,
    @JsonProperty("refresh_token_expires_in") val refreshTokenExpiresIn: String,
    @JsonProperty("scope") val scope: String,
){
}