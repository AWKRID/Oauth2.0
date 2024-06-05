package com.awkrid.oauth2.domain.oauth2.dto

import com.fasterxml.jackson.annotation.JsonProperty

class UserInfo(
    @JsonProperty("id") val id: Long,
    @JsonProperty("connected_at") val connectedAt: String,
    @JsonProperty("properties") val properties: Properties,
    @JsonProperty("kakao_account") val kakaoAccount: KakaoAccount,
)

class Properties(
    @JsonProperty("nickname") val username: String,
)

class KakaoAccount(
    @JsonProperty("profile_nickname_needs_agreement") val profileNicknameNeedsAgreement: Boolean,
    @JsonProperty("profile") val profile: Profile,
)

class Profile(
    @JsonProperty("nickname") val nickname: String,
    @JsonProperty("is_default_nickname") val isDefaultNickname: Boolean,
)