package com.kshrd.amsfull.service.bookmark

import com.kshrd.amsfull.exception.ArticleAlreadyBookmarkedException
import com.kshrd.amsfull.exception.BookmarkNotFoundException
import com.kshrd.amsfull.model.dto.BookmarkDto
import com.kshrd.amsfull.model.entity.AppUser
import com.kshrd.amsfull.model.entity.Article
import com.kshrd.amsfull.model.request.BookmarkRequest
import com.kshrd.amsfull.service.article.ArticleRepository
import com.kshrd.amsfull.service.appuser.AppUserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
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

        val isExistingBookmark = articleRepository.findExistingBookmark(user.id!!, article.id!!)
        if (isExistingBookmark) throw ArticleAlreadyBookmarkedException()

        user.bookmarkedArticles.add(article)
        return appUserRepository.save(user).bookmarkedArticles.map { it.toBookmarkDto() }
    }

    override fun fetchAllBookmarks(userId: UUID, page: Int, size: Int): Page<BookmarkDto> {
        if (appUserRepository.findById(userId).isEmpty)
            throw NoSuchElementException("cannot find user id: $userId")

//        val res = appUserRepository
//            .findBookmarksOfUser(userId, PageRequest.of(page, size))

        val res = appUserRepository.findById(userId).get().bookmarkedArticles
        val payload = res.map { it.toBookmarkDto() }
        val pageable = PageRequest.of(page, size)
        val start = pageable.offset.toInt()
        val end = (start + pageable.pageSize).toLong().coerceAtMost(payload.size.toLong()).toInt()

        return PageImpl<BookmarkDto>(payload.subList(start, end), pageable, payload.size.toLong())
    }

    override fun remove(userId: UUID, articleId: UUID) {
        val (user, article) = isExistingUserAndArticle(userId, articleId)

        val isExistingBookmark = articleRepository.findExistingBookmark(user.id!!, article.id!!)
        if (!isExistingBookmark) throw BookmarkNotFoundException()

        user.bookmarkedArticles.remove(article)
        val savedUser = appUserRepository.save(user)

        if (savedUser.bookmarkedArticles.contains(article)) throw IllegalStateException("Unable to remove article id: $articleId")
    }

    private fun isExistingUserAndArticle(userId: UUID, articleId: UUID): Pair<AppUser, Article> {
        val fetchedUser = appUserRepository.findById(userId)
        val fetchedArticle = articleRepository.findById(articleId)

        if (fetchedArticle.isEmpty) throw NoSuchElementException("cannot find article id: $articleId")
        if (fetchedUser.isEmpty) throw NoSuchElementException("cannot find user id: $userId")

        return Pair(fetchedUser.get(), fetchedArticle.get())
    }

}