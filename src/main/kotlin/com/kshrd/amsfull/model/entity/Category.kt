package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.dto.CategoryDto
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "categories")
class Category(name: String) {

    constructor(id: Long, name: String) : this(name) {
        this.id = id
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "name", nullable = false, unique = true, length = 100)
    var name: String? = name

    @ManyToMany(mappedBy = "categories", cascade = [CascadeType.PERSIST, CascadeType.MERGE]) var articles: MutableSet<Article> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Category

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
    fun toDto(): CategoryDto = CategoryDto(id = id!!, name = name!!)

}