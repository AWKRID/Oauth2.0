package com.awkrid.oauth2.domain.oauth2.controller

import com.awkrid.oauth2.domain.oauth2.dto.LoginResponse
import com.awkrid.oauth2.domain.oauth2.service.OauthService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/oauth2")
class OauthController(
    private val oauthService: OauthService
) {

    @GetMapping("/kakao")
    fun getToken(@RequestParam code: String): ResponseEntity<LoginResponse> {
        return ResponseEntity.ok(oauthService.login(code))
    }

    @GetMapping("/kakao/login")
    fun kakaoLogin() : String{
        val str = oauthService.getAuthorizationCode()
        print(str)
        return oauthService.getAuthorizationCode()
    }
}