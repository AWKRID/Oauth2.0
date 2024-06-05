package com.awkrid.oauth2.member.controller

import com.awkrid.oauth2.member.dto.ProfileResponse
import com.awkrid.oauth2.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService) {

    @GetMapping("/profile")
    fun getProfile(
        @RequestHeader("Authorization") authHeader: String,
    ): ResponseEntity<ProfileResponse> {
        val accessToken = authHeader.substringAfter("Bearer ")
        return ResponseEntity.ok(memberService.loadProfile(accessToken))
    }
}