package com.kshrd.amsfull.model.entity

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@MappedSuperclass
open class Document(
    title: String,
    description: String,
    isPublished: Boolean,
    thumbnail: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @Column(name = "title")
    open var title: String? = title

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description", columnDefinition = "TEXT")
    open var description: String? = description

    @Column(name = "is_published", nullable = false)
    open var isPublished: Boolean? = isPublished

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "thumbnail", nullable = false)
    open var thumbnail: String? = thumbnail
}