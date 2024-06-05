package com.awkrid.oauth2.domain.oauth2.service

import com.awkrid.oauth2.domain.oauth2.KakaoClient
import com.awkrid.oauth2.domain.oauth2.LoginParam
import com.awkrid.oauth2.domain.oauth2.dto.LoginResponse
import com.awkrid.oauth2.jwt.JwtPlugin
import com.awkrid.oauth2.member.Member
import com.awkrid.oauth2.member.repository.MemberRepository
import org.springframework.stereotype.Service


@Service
class OauthService(
    private val kakaoClient: KakaoClient,
    private val memberRepository: MemberRepository,
    private val jwtPlugin: JwtPlugin
) {
    fun login(code: String): LoginResponse {
        val kakaoAccessToken = LoginParam(code)
            .let { kakaoClient.requestAccessToken(it) }
        val userInfo = kakaoClient.getUserInfo(kakaoAccessToken)
        val member = Member(
            id = userInfo.id,
            username = userInfo.properties.username,
            kakaoAccessToken = kakaoAccessToken,
            role = "user"
        )
        memberRepository.save(member)

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(member.id.toString(), member.username),
            id = member.id,
            username = member.username,
            role = member.role
        )
    }

    fun getAuthorizationCode(): String {
        return kakaoClient.getAuthorizationCodeUrl()
    }
}