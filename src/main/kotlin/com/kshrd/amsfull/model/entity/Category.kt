package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.dto.CategoryDto
import org.hibernate.Hibernate
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "categories", uniqueConstraints = [UniqueConstraint(name = "uk_category_name", columnNames = ["name"])])
class Category(name: String, imageUrl: String) {

//    constructor(id: Long, name: String, imageUrl: String) : this(name, imageUrl) {
//        this.id = id
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "name", nullable = false, length = 100)
    var name: String? = name

//    @ManyToMany(mappedBy = "categories", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
//    var articles: MutableSet<Article> = mutableSetOf()

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "image_url")
    var imageUrl: String? = imageUrl

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Category

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
    fun toDto(): CategoryDto = CategoryDto(id = id!!, name = name!!, imageUrl = imageUrl!!)

}