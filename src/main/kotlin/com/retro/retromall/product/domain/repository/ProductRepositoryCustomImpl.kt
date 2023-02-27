package com.retro.retromall.product.domain.repository

import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.retro.retromall.member.domain.QMember.member
import com.retro.retromall.product.domain.Product
import com.retro.retromall.product.domain.QProduct.product
import com.retro.retromall.product.dto.ProductListResponse
import com.retro.retromall.product.dto.ProductResponse
import com.retro.util.QueryDslUtils
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import java.util.stream.Collectors

@Repository
class ProductRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : ProductRepositoryCustom {
    override fun selectProduct(productId: Long): ProductResponse {

        val data = jpaQueryFactory.select(product, member.nickname)
            .from(product)
            .innerJoin(member).on(product.authorId.eq(member.id))
            .where(product.id.eq(productId))
            .fetchOne()

        data?.let {
            val product = data.get(product)!!
            return ProductResponse(
                productId = product.id!!,
                content = product.content,
                amount = product.amount,
                author = data.get(member.nickname)!!,
                category = product.category,
                hashTags = getHashTags(product),
                images = getImages(product),
                createdAt = product.createdAt,
                modifiedAt = product.modifiedAt
            )
        } ?: throw IllegalArgumentException("요청하신 결과가 없습니다.")
    }

    override fun selectProductList(category: String?, pageable: Pageable): ProductListResponse {
        val query = jpaQueryFactory.select(
            Projections.constructor(
                ProductListResponse.Data::class.java,
                product.id,
                member.nickname,
                product.content,
                product.amount,
                product.thumbnail,
                product.createdAt,
                product.modifiedAt
            )
        )
            .from(product)
            .innerJoin(member).on(product.authorId.eq(member.id))
            .where(eqCategory(category))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong() + 1)
        QueryDslUtils.setOrderBy(query, product.type, product.metadata, pageable)

        val content = query.fetch()

        var hasNext = false
        if (content.size > pageable.pageSize) {
            content.removeAt(pageable.pageSize)
            hasNext = true
        }

        return ProductListResponse(data = SliceImpl(content, pageable, hasNext))


    }

    private fun eqCategory(category: String?): BooleanExpression? {
        return if (StringUtils.hasText(category))
            return product.category.eq(category)
        else null
    }

    private fun getHashTags(product: Product): Set<String> {
        return product.hashTags.stream().map { it.hashTag }.collect(Collectors.toSet())
    }

    private fun getImages(product: Product): Set<String> {
        return product.images.stream().map { it.imageUrl }.collect(Collectors.toSet())
    }
}