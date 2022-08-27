package com.kshrd.amsfull.service.article

import com.kshrd.amsfull.model.entity.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ArticleRepository : JpaRepository<Article, UUID> {

    @Query("select a from Article a where a.isPublished = true")
    fun findAllPublishedArticles(pageable: Pageable): Page<Article>
}