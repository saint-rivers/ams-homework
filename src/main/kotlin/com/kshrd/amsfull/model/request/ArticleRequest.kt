package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.exception.common.InvalidUriException
import com.kshrd.amsfull.model.entity.Article
import com.kshrd.amsfull.service.validator.isUrl
import io.swagger.v3.oas.annotations.Hidden
import java.util.UUID

data class ArticleRequest(
    val title: String,
    val description: String,
    val categories: Set<String>,
    val teacherId: UUID,
    val isPublished: Boolean = false,
    val thumbnail: String
) {
    fun toEntity(): Article =
        Article(
            title = title,
            description = description,
            isPublished = isPublished,
            thumbnail = thumbnail
        )

    @Hidden
    internal fun validate() {
        isValid()
    }

    private fun isValid(): Boolean {
        if (!thumbnail.isUrl()) throw InvalidUriException()
        return true
    }
}
