package com.awkrid.oauth2.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin(
    @Value("\${jwt.issuer}") private val issuer: String,
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour: Long,
) {

    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }


    fun generateAccessToken(subject: String, username: String, role: String = "user"): String {
        val claims: Claims = Jwts.claims()
            .add(mapOf("username" to username,"role" to role))
            .build()
        val now = Instant.now()
        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(Duration.ofHours(accessTokenExpirationHour))))
            .claims(claims)
            .signWith(key)
            .compact()

    }
}