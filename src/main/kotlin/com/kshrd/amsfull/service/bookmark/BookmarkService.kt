package com.kshrd.amsfull.service.bookmark

import com.kshrd.amsfull.model.dto.BookmarkDto
import com.kshrd.amsfull.model.request.BookmarkRequest
import org.springframework.data.domain.Page
import java.util.UUID

interface BookmarkService {

    fun addBookmark(userId: UUID, bookmarkRequest: BookmarkRequest): List<BookmarkDto>

    fun fetchAllBookmarks(userId: UUID, page: Int, size: Int): Page<BookmarkDto>

    fun remove(userId: UUID, articleId: UUID)
}