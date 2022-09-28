package com.kshrd.amsfull.service.article

import com.kshrd.amsfull.model.dto.ArticleDto
import com.kshrd.amsfull.model.dto.CommentDto
import com.kshrd.amsfull.model.request.ArticleRequest
import com.kshrd.amsfull.model.request.CommentRequest
import org.springframework.data.domain.Page
import java.util.UUID

interface ArticleService {
    fun createArticle(articleRequest: ArticleRequest): ArticleDto
    fun fetchBy(page: Int, size: Int): Page<ArticleDto>
    fun findByArticleId(id: UUID): ArticleDto
    fun deleteById(id: UUID)
    fun update(id: UUID, req: ArticleRequest): ArticleDto
    fun fetchPublished(page: Int, size: Int): Page<ArticleDto>
    fun addComment(id: UUID, commentRequest: CommentRequest): CommentDto
    fun fetchCommentsOf(articleId: UUID): List<CommentDto>
    fun publishArticle(id: UUID)
}