package com.awkrid.oauth2.member.repository

import com.awkrid.oauth2.member.Member
import org.springframework.stereotype.Component

@Component
class MemberRepository {
    private val storage = HashMap<Long, Member>()

    fun save(member: Member) {
        storage[member.id] = member

    }
    fun findById(id: Long): Member? {
        return storage[id]
    }
}