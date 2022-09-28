package com.kshrd.amsfull.controller

import com.kshrd.amsfull.model.request.BookmarkRequest
import com.kshrd.amsfull.model.response.ApiResponse
import com.kshrd.amsfull.service.bookmark.BookmarkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/bookmarks")
class BookmarkController(val bookmarkService: BookmarkService) {

    @PostMapping("/user/{userId}")
    fun addBookmark(
        @RequestBody bookmarkRequest: BookmarkRequest,
        @PathVariable("userId") userId: UUID
    ): ResponseEntity<ApiResponse> {
        val payload = bookmarkService.addBookmark(userId, bookmarkRequest)

        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "added bookmark successfully for user: $userId",
                status = "201",
                payload = payload
            )
        )
    }

    @GetMapping("/user/{userId}")
    fun fetch(
        @PathVariable("userId") userId: UUID,
        @RequestParam("page", defaultValue = "1") page: Int,
        @RequestParam("size", defaultValue = "5") size: Int
    ): ResponseEntity<ApiResponse> {
        if (page < 1) throw IllegalStateException("page cannot be smaller than 1")
        if (size < 1) throw IllegalStateException("size cannot be smaller than 1")

        val payload = bookmarkService.fetchAllBookmarks(userId, page - 1, size)
        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPage(
                message = "successfully fetched bookmarks of user: $userId",
                status = "200",
                payload = payload.content,
                page = page,
                size = payload.numberOfElements,
                totalElements = payload.totalElements,
                totalPages = payload.totalPages
            )
        )
    }

    @DeleteMapping("/user/{userId}")
    fun removeBookmark(
        @PathVariable("userId") userId: UUID,
        @RequestParam("articleId") articleId: UUID
    ): ResponseEntity<ApiResponse> {
        bookmarkService.remove(userId, articleId)

        return ResponseEntity.ok().body(
            ApiResponse.Success(
                message = "successfully removed article id:$articleId from bookmarks",
                status = "200"
            )
        )
    }

}