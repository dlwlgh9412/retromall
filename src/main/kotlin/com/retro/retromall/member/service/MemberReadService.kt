package com.retro.retromall.member.service

import com.retro.retromall.member.domain.Member
import com.retro.retromall.member.infra.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
@Transactional(readOnly = true)
class MemberReadService(
    private val memberRepository: MemberRepository,
) {
    internal fun findMemberByOAuthId(oauthId: String): Member? {
        return memberRepository.findByOauthId(oauthId).orElse(null)
    }
}