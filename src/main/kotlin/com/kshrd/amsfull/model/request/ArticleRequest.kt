package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.model.entity.Article
import java.util.UUID

data class ArticleRequest(
    val title: String,
    val description: String,
    val categories: Set<String>,
    val teacherId: UUID,
    val isPublished: Boolean = false,
    val thumbnail: String
)  {
    fun toEntity() = Article(
        title = title,
        description = description,
        isPublished = isPublished,
        thumbnail = thumbnail
    )
}
