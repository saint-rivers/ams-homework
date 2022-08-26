package com.kshrd.amsfull.service.article;

import com.kshrd.amsfull.model.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ArticleRepository : JpaRepository<Article, UUID> {
}