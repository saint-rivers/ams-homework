package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.model.entity.Article
import java.io.Serializable

data class ArticleRequest(
    val title: String,
    val description: String,
    val categories: Set<String>
) : Serializable {
    fun toEntity() = Article(
        title = title,
        description = description
    )
}
