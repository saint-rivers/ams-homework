package com.kshrd.amsfull.service.appuser

import com.kshrd.amsfull.model.entity.AppUser
import com.kshrd.amsfull.model.entity.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface AppUserRepository : JpaRepository<AppUser, UUID> {

//    @Query("select u.bookmarkedArticles from AppUser u where u.id = :userId")
//    @Query("select u.bookmarkedArticles from AppUser u join u.bookmarkedArticles where u.id = :userId")

    @Query(
        value = "select a.* " +
                "from ams.public.user_bookmarked_articles " +
                "inner join articles a " +
                "on a.id = user_bookmarked_articles.bookmarked_article_id " +
                "where app_user_id = :userId",
        nativeQuery = true
    )
    fun findBookmarksOfUser(userId: UUID, pageable: Pageable): Page<Article>

}