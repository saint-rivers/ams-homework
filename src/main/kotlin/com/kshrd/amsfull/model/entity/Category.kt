package com.kshrd.amsfull.model.entity

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "categories")
open class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "name", nullable = false, unique = true, length = 100)
    open var name: String? = null

    @ManyToMany(mappedBy = "categories", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    open var articles: MutableSet<Article> = mutableSetOf()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Category

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}