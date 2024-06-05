package com.awkrid.oauth2.member.service

import com.awkrid.oauth2.jwt.JwtPlugin
import com.awkrid.oauth2.member.dto.ProfileResponse
import com.awkrid.oauth2.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val jwtPlugin: JwtPlugin,
    private val memberRepository: MemberRepository
) {
    fun loadProfile(accessToken: String): ProfileResponse {
        // TODO(예외처리 필요)
        val jwt = jwtPlugin.validateToken(accessToken)
        val member = memberRepository.findById(jwt.payload.subject.toLong())
            ?: throw RuntimeException("Member with id ${jwt.payload.id} not found")
        return ProfileResponse(
            id = member.id,
            username = member.username,
            role = member.role,
        )
    }
}
