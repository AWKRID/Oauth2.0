package com.awkrid.oauth2.domain.oauth2

import com.awkrid.oauth2.domain.oauth2.dto.KakaoToken
import com.awkrid.oauth2.domain.oauth2.dto.UserInfo
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Component
class KakaoClient(
    @Value("\${oauth.kakao.client-id}") private val clientId: String,
    @Value("\${oauth.kakao.auth-url}") private val authUrl: String,
    @Value("\${oauth.kakao.token-url}") private val tokenUrl: String,
    @Value("\${oauth.kakao.client-secret}") private val clientSecret: String,
    @Value("\${oauth.kakao.user-api-url}") private val userApiUrl: String,
    private val restTemplate: RestTemplate
) {
    fun getAuthorizationCodeUrl(): String {
        return "redirect:$authUrl"
    }

    fun requestAccessToken(loginParam: LoginParam): String {
        val request: HttpEntity<MultiValueMap<String, String>> = generateHttpRequests(loginParam)
        val kakaoToken: KakaoToken = restTemplate.postForObject(tokenUrl, request, KakaoToken::class.java)
            ?: throw RuntimeException("Kakao token is null")
        return kakaoToken.accessToken
    }

    fun getUserInfo(accessToken: String): UserInfo {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        headers.set("Authorization", "Bearer $accessToken")
        val request = HttpEntity<MultiValueMap<String, String>>(headers)
        return restTemplate.postForObject(userApiUrl, request, UserInfo::class.java)
            ?: throw RuntimeException("fail to load user info")
    }

    fun generateHttpRequests(loginParam: LoginParam): HttpEntity<MultiValueMap<String, String>> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val body = loginParam.makeBody()
        body.add("grant_type", "authorization_code")
        body.add("client_id", clientId)
        body.add("redirect_uri", "http://localhost:8080/oauth2/kakao")
        body.add("client_secret", clientSecret)
        return HttpEntity(body, headers)

    }
}