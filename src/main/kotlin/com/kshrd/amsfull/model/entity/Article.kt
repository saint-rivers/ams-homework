package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.Document
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "articles")
open class Article: Document() {

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "article_categories",
        joinColumns = [JoinColumn(name = "article_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    open var categories: MutableSet<Category> = mutableSetOf()

    @OneToMany(mappedBy = "article", cascade = [CascadeType.ALL], orphanRemoval = true)
    open var teachers: MutableSet<Teacher> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Article

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

}