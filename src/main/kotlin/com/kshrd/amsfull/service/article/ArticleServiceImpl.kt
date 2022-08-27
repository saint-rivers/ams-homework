package com.kshrd.amsfull.service.article

import com.kshrd.amsfull.model.dto.ArticleDto
import com.kshrd.amsfull.model.request.ArticleRequest
import com.kshrd.amsfull.service.category.CategoryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArticleServiceImpl(
    val articleRepository: ArticleRepository,
    val categoryRepository: CategoryRepository
) : ArticleService {
    override fun createArticle(article: ArticleRequest): ArticleDto {
        val articleReq = article.toEntity()
        articleReq.categories = article.categories
            .map { categoryRepository.findAllByNameStartsWith(it)[0] }
            .toMutableSet()
        return articleRepository.save(articleReq).toDto()!!
    }

    override fun fetchBy(page: Int, size: Int): Page<ArticleDto> {
        return articleRepository.findAll(PageRequest.of(page, size)).map { it.toDto() }
    }

    override fun findByArticleId(id: UUID): ArticleDto {
        return articleRepository.findById(id).orElseThrow().toDto()!!
    }

    override fun deleteById(id: UUID) {
        val article = articleRepository.findById(id)
        if (article.isPresent)
            articleRepository.deleteById(id)
        else throw NoSuchElementException("cannot find article $id")
    }

    override fun update(id: UUID, req: ArticleRequest): ArticleDto {
        val article = req.toEntity()
        article.id = id
        article.categories = req.categories
            .map { categoryRepository.findAllByNameStartsWith(it)[0] }
            .toMutableSet()
        return articleRepository.save(article).toDto()!!
    }

    override fun fetchPublished(page: Int, size: Int): Page<ArticleDto> {
        return articleRepository.findAllPublishedArticles(PageRequest.of(page, size)).map { it.toDto() }
    }

}