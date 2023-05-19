package com.retro.retromall.product.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ProductHashTagId(
    productId: Long,
    tag: String
) : Serializable{
    companion object {
        const val serialVersionUID = -421954266432L
    }

    @Column(name = "product_id", nullable = false)
    val productId: Long = productId

    @Column(name = "hashtag_name", nullable = false)
    val hashTagName: String = tag

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductHashTagId

        if (productId != other.productId) return false
        return hashTagName == other.hashTagName
    }

    override fun hashCode(): Int {
        var result = productId.hashCode()
        result = 31 * result + hashTagName.hashCode()
        return result
    }
}