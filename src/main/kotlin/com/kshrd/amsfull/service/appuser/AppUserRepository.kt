package com.kshrd.amsfull.service.appuser

import com.kshrd.amsfull.model.entity.AppUser
import com.kshrd.amsfull.model.entity.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*
import javax.persistence.ColumnResult
import javax.persistence.ConstructorResult
import javax.persistence.SqlResultSetMapping

@SqlResultSetMapping(
    name = "bookmarkedArticleMapping",
    classes = [
        ConstructorResult(
            targetClass = Article::class,
            columns = [
                ColumnResult(name = "id"),
                ColumnResult(name = "description"),
                ColumnResult(name = "is_published"),
                ColumnResult(name = "thumbnail"),
                ColumnResult(name = "title"),
                ColumnResult(name = "created_date"),
                ColumnResult(name = "last_modified"),
            ]
        )
    ]
)
//@NamedNativeQuery(
//    name = "findBookmarksOfUser",
//    query = "select a.* " +
//            "from ams.public.user_bookmarked_articles " +
//            "inner join articles a " +
//            "on a.id = user_bookmarked_articles.bookmarked_article_id " +
//            "where app_user_id = :userId",
//    resultSetMapping = "groupDetailsMapping"
//)
interface AppUserRepository : JpaRepository<AppUser, UUID> {

//    @Query("select u.bookmarkedArticles from AppUser u where u.id = :userId")
//    @Query("select u.bookmarkedArticles from AppUser u join u.bookmarkedArticles where u.id = :userId")

//    @Query(
//        value = "select a.* " +
//                "from ams.public.user_bookmarked_articles " +
//                "inner join articles a " +
//                "on a.id = user_bookmarked_articles.bookmarked_article_id " +
//                "where app_user_id = :userId",
//        nativeQuery = true,
//    )

    @Query("select u.bookmarkedArticles from AppUser u join u.bookmarkedArticles where u.id = :userId")
    fun findBookmarksOfUser(userId: UUID, pageable: Pageable): Page<Article>

}