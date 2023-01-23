package com.kshrd.amsfull.model.entity

import java.util.*
import jakarta.persistence.*

@MappedSuperclass
open class Document(
) {
    constructor(
        title: String,
        description: String,
        isPublished: Boolean,
        thumbnail: String
    ) : this() {
        this.title = title
        this.description = description
        this.isPublished = isPublished
        this.thumbnail = thumbnail
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "title")
    var title: String? = null

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String? = null

    @Column(name = "is_published", nullable = false)
    var isPublished: Boolean? = null

    @Column(name = "thumbnail", nullable = false, columnDefinition = "TEXT")
    var thumbnail: String? = null
}