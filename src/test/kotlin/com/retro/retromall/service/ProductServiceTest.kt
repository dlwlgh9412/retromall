package com.retro.retromall.service

import com.retro.retromall.category.domain.CategoryEntity
import com.retro.retromall.category.domain.repository.CategoryRepository
import com.retro.retromall.member.domain.MemberEntity
import com.retro.retromall.member.enums.OAuthType
import com.retro.retromall.member.repository.MemberRepository
import com.retro.retromall.product.domain.repository.ProductRepository
import com.retro.retromall.product.service.ProductService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
class ProductServiceTest(
    @Autowired
    private val productService: ProductService,

    @Autowired
    private val productRepository: ProductRepository,

    @Autowired
    private val categoryRepository: CategoryRepository,

    @Autowired
    private val memberRepository: MemberRepository

) {
    @BeforeEach
    @Transactional
    fun init() {
        memberRepository.save(MemberEntity(OAuthType.KAKAO, "testestest", "", "", "", ""))
        categoryRepository.save(CategoryEntity(parent = null, name = "PC", id = ""))
    }
}