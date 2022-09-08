package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.model.entity.Article
import java.io.Serializable
import java.util.UUID

data class ArticleRequest(
    val title: String,
    val description: String,
    val categories: Set<String>,
    val teacherId: UUID,
    val isPublished: Boolean = false
) : Serializable {
    fun toEntity() = Article(
        title = title,
        description = description,
        isPublished = isPublished
    )
}
