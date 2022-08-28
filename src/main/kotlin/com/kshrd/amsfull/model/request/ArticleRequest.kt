package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.model.entity.Article
import java.io.Serializable
import java.util.UUID

data class ArticleRequest(
    val title: String,
    val description: String,
    val categories: Set<String>,
    val teacherId: UUID
) : Serializable {
    fun toEntity() = Article(
        title = title,
        description = description
    )
}
