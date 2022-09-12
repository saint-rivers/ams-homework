package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.dto.ArticleDto
import com.kshrd.amsfull.model.dto.BookmarkDto
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "articles")
open class Article(title: String, description: String, isPublished: Boolean = false, thumbnail: String) :
    Document(title = title, description = description, isPublished = isPublished, thumbnail = thumbnail) {

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
    @JoinTable(
        name = "article_categories",
        joinColumns = [JoinColumn(name = "article_id", foreignKey = ForeignKey(name = "fk_article_id"))],
        inverseJoinColumns = [JoinColumn(name = "category_id", foreignKey = ForeignKey(name = "fk_category_id"))]
    )
    open var categories: MutableSet<Category> = mutableSetOf()

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "teacher_id", nullable = false, foreignKey = ForeignKey(name = "fk_teacher_id"))
    open var teacher: AppUser? = null

    @OneToMany(mappedBy = "article", orphanRemoval = true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
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
            title = title!!,
            description = description,
            categories = categories.map { cat -> cat.toDto() }.toSet(),
            isPublished = isPublished == true,
            teacher = teacher?.toDto()!!,
            comments = comments.map { comment -> comment.toDto() }.toSet(),
            thumbnail = thumbnail!!
        )
    }

    fun toBookmarkDto(): BookmarkDto {
        return BookmarkDto(
            articleId = id!!,
            title = title!!,
            description = description!!,
            teacher = teacher?.toDto()!!
        )
    }

}