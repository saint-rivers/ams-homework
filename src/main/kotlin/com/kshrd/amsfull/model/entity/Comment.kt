package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.dto.CommentDto
import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "comments")
open class Comment(_caption: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @Column(name = "caption")
    open var caption: String = _caption

    @ManyToOne(cascade = [CascadeType.MERGE], optional = false)
    @JoinColumn(name = "article_id", nullable = false, foreignKey = ForeignKey(name = "fk_article_id"))
    open var article: Article? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Comment

        return id != null && id == other.id
    }

    fun toDto() = CommentDto(
        id = id!!,
        caption = caption,
    )

    override fun hashCode(): Int = javaClass.hashCode()
}