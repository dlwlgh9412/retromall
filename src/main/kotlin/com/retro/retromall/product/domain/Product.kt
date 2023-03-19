package com.retro.retromall.product.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_product")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    val id: Long? = null,

    @Column(name = "title", length = 255, nullable = false)
    var title: String,

    @Column(name = "content", length = 5000, nullable = true)
    var content: String?,

    @Column(name = "amount", nullable = false)
    var amount: Int,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "author_id")


    @Column(name = "author_id")
    var authorId: Long,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category")
    @Column(name = "category", length = 50)
    var category: String,

//    @ManyToMany(cascade = [CascadeType.MERGE])
//    @JoinTable(
//        name = "tb_product_hashtag",
//        joinColumns = [JoinColumn(name = "product_id", referencedColumnName = "product_id")],
//        inverseJoinColumns = [JoinColumn(name = "hashtag_name", referencedColumnName = "hashtag_name")]
//    )
//    var hashTags: MutableSet<HashTag> = mutableSetOf(),
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var productLikes: MutableList<ProductLike> = mutableListOf(),

    var likes: Long = 0L,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var hashTags: MutableSet<ProductHashTag> = mutableSetOf(),

    @Column(name = "thumbnail", length = 255)
    var thumbnail: String? = null,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var images: MutableSet<ProductImage> = mutableSetOf(),

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "modified_at", nullable = false)
    var modifiedAt: LocalDateTime = LocalDateTime.now(),
) {
    constructor(
        title: String,
        content: String?,
        amount: Int,
        authorId: Long,
        category: String
    ) : this(null, title, content, amount, authorId, category)

    fun addLikes(memberId: Long, productLike: ProductLike?) {
        productLike?.let {
            if (!it.isLiked) {
                it.isLiked = true
                this.likes++
            }
        } ?: run {
            this.productLikes.add(ProductLike(this, memberId))
            this.likes++
        }
    }

    fun removeLikes(productLike: ProductLike?) {
        productLike?.let {
            if (it.isLiked) {
                it.isLiked = false
                this.likes--
            }
        } ?: throw IllegalStateException("해당 상품에 대해 좋아요를 누른적이 없습니다.")
    }

    fun isAuthor(memberId: Long): Boolean {
        if (authorId != memberId)
            return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}