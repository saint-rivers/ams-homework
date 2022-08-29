package com.kshrd.amsfull.service.bookmark

import com.kshrd.amsfull.model.dto.BookmarkDto
import com.kshrd.amsfull.model.entity.AppUser
import com.kshrd.amsfull.model.entity.Article
import com.kshrd.amsfull.model.request.BookmarkRequest
import com.kshrd.amsfull.service.article.ArticleRepository
import com.kshrd.amsfull.service.teacher.AppUserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookmarkServiceImpl(
    val appUserRepository: AppUserRepository,
    val articleRepository: ArticleRepository
) : BookmarkService {
    override fun addBookmark(userId: UUID, bookmarkRequest: BookmarkRequest): List<BookmarkDto> {

        val (user, article) = isExistingUserAndArticle(userId, bookmarkRequest.articleId)

        user.bookmarkedArticles.add(article)
        return appUserRepository.save(user).bookmarkedArticles.map { it.toBookmarkDto() }
    }

    override fun fetchAllBookmarks(userId: UUID, page: Int, size: Int): Page<BookmarkDto> {
        if (appUserRepository.findById(userId).isEmpty)
            throw NoSuchElementException("cannot find user id:$userId")

        return appUserRepository
            .findBookmarksOfUser(userId, PageRequest.of(page, size))
            .map { it.toBookmarkDto() }
    }

    override fun remove(userId: UUID, articleId: UUID) {
        val (user, article) = isExistingUserAndArticle(userId, articleId)
        user.bookmarkedArticles.remove(article)
        val savedUser = appUserRepository.save(user)
        if (savedUser.bookmarkedArticles.contains(article)) throw IllegalStateException("Unable to remove article id: $articleId")
    }

    fun isExistingUserAndArticle(userId: UUID, articleId: UUID): Pair<AppUser, Article> {
        val fetchedUser = appUserRepository.findById(userId)
        val fetchedArticle = articleRepository.findById(articleId)

        if (fetchedArticle.isEmpty) throw NoSuchElementException("cannot find article id: $articleId")
        if (fetchedUser.isEmpty) throw NoSuchElementException("cannot find user id: $userId")

        return Pair(fetchedUser.get(), fetchedArticle.get())
    }

}