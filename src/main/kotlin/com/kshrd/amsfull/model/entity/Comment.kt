package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.dto.CommentDto
import java.util.*
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment() {

    constructor(caption: String) : this() {
        this.caption = caption
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "caption")
    lateinit var caption: String

    @ManyToOne(cascade = [CascadeType.MERGE], optional = false)
    @JoinColumn(name = "article_id", nullable = false, foreignKey = ForeignKey(name = "fk_article_id"))
    var article: Article? = null

    fun toDto() = id?.let {
        CommentDto(
            id = it,
            caption = caption,
        )
    }

}