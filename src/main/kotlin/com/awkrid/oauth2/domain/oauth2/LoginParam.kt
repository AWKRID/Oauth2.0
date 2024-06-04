package com.awkrid.oauth2.domain.oauth2

import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

class LoginParam(
    private val authorizationCode: String
) {
    fun makeBody() : MultiValueMap<String, String>{
        val body = LinkedMultiValueMap<String, String>()
        body.add("code", authorizationCode)
        return body
    }
}