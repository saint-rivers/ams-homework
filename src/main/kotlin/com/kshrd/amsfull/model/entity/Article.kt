package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.Document
import com.kshrd.amsfull.model.dto.ArticleDto
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "articles")
open class Article(title: String, description: String, isPublished: Boolean = false) :
    Document(title, description, isPublished) {

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "article_categories",
        joinColumns = [JoinColumn(name = "article_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    open var categories: MutableSet<Category> = mutableSetOf()


    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "teacher_id", nullable = false)
    open var teacher: Teacher? = null

    @OneToMany(mappedBy = "article", orphanRemoval = true)
    open var comments: MutableSet<Comment> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Article

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    fun toDto() = id?.let {
        ArticleDto(
            id = it,
            title = title,
            description = description,
            categories = categories.map { cat -> cat.toDto() }.toSet(),
            isPublished = isPublished == true,
            teacher = teacher?.toDto()!!,
            comments = comments.map { comment -> comment.toDto() }.toSet()
        )
    }

}