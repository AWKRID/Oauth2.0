package com.awkrid.oauth2.domain.oauth2.service

import com.awkrid.oauth2.domain.oauth2.KakaoClient
import com.awkrid.oauth2.domain.oauth2.LoginParam
import org.springframework.stereotype.Service



@Service
class OauthService(
    private val kakaoClient: KakaoClient,
) {
    fun login(code: String) : String{
        val loginParam = LoginParam(code)
        return kakaoClient.requestAccessToken(loginParam)
    }

    fun getAuthorizationCode(): String{
        return kakaoClient.getAuthorizationCodeUrl()
    }
}