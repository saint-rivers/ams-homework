package com.kshrd.amsfull.controller

import com.kshrd.amsfull.model.dto.ArticleDto
import com.kshrd.amsfull.model.request.ArticleRequest
import com.kshrd.amsfull.model.request.CommentRequest
import com.kshrd.amsfull.model.response.ApiResponse
import com.kshrd.amsfull.service.article.ArticleService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/articles")
class ArticleController(val articleService: ArticleService) {

    @GetMapping("/{id}")
    fun find(@PathVariable id: UUID): ResponseEntity<ApiResponse> {
        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "successfully fetched categories",
                status = "200",
                payload = articleService.findByArticleId(id)
            )
        )
    }

    @GetMapping("/published")
    fun fetchPublished(
        @RequestParam("page", defaultValue = "1") page: Int,
        @RequestParam("size", defaultValue = "5") size: Int
    ): ResponseEntity<ApiResponse> {
        if (page < 1) throw IllegalStateException("page cannot be smaller than 1")
        if (size < 1) throw IllegalStateException("size cannot be smaller than 1")

        val payload: Page<ArticleDto> = articleService.fetchPublished(page - 1, size)
        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPage(
                message = "successfully fetched published categories",
                status = "200",
                payload = payload.content,
                page = page,
                size = payload.numberOfElements,
                totalElements = payload.totalElements,
                totalPages = payload.totalPages
            )
        )
    }

    @GetMapping
    fun fetch(
        @RequestParam("page", defaultValue = "1") page: Int,
        @RequestParam("size", defaultValue = "5") size: Int
    ): ResponseEntity<ApiResponse> {
        if (page < 1) throw IllegalStateException("page cannot be smaller than 1")
        if (size < 1) throw IllegalStateException("size cannot be smaller than 1")

        val payload: Page<ArticleDto> = articleService.fetchBy(page - 1, size)
        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPage(
                message = "successfully fetched categories",
                status = "200",
                payload = payload.content,
                page = page,
                size = payload.numberOfElements,
                totalElements = payload.totalElements,
                totalPages = payload.totalPages
            )
        )
    }

    @PostMapping
    fun create(@RequestBody article: ArticleRequest) =
        ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "created a new article",
                status = "201",
                payload = articleService.createArticle(article)
            )
        )

    @PostMapping("/{id}/comments")
    fun commentOnArticle(@RequestBody commentRequest: CommentRequest, @PathVariable id: UUID) =
        ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "commented on article id: $id",
                status = "201",
                payload = articleService.addComment(id, commentRequest)
            )
        )

    @GetMapping("/{id}/comments")
    fun fetch(@PathVariable("id") articleId: UUID): ResponseEntity<ApiResponse> =
        ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "successfully fetched comments of article id: $articleId",
                status = "200",
                payload = articleService.fetchCommentsOf(articleId)
            )
        )

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: UUID): ResponseEntity<ApiResponse> {
        articleService.deleteById(id)
        return ResponseEntity.ok().body(
            ApiResponse.Success(
                message = "deleted article with id: $id",
                status = "200",
            )
        )
    }

    @PutMapping("/{id}")
    fun update(@RequestBody article: ArticleRequest, @PathVariable id: UUID) =
        ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "updated article with id $id",
                status = "200",
                payload = articleService.update(id, article)
            )
        )

}