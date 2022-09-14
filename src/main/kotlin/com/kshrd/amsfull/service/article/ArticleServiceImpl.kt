package com.kshrd.amsfull.service.article

import com.kshrd.amsfull.model.dto.ArticleDto
import com.kshrd.amsfull.model.dto.CommentDto
import com.kshrd.amsfull.model.request.ArticleRequest
import com.kshrd.amsfull.model.request.CommentRequest
import com.kshrd.amsfull.service.category.CategoryRepository
import com.kshrd.amsfull.service.appuser.AppUserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ArticleServiceImpl(
    val articleRepository: ArticleRepository,
    val categoryRepository: CategoryRepository,
    val commentRepository: CommentRepository,
    val appUserRepository: AppUserRepository,
) : ArticleService {
    override fun createArticle(articleRequest: ArticleRequest): ArticleDto {

        articleRequest.validate()
        val article = articleRequest.toEntity()
        article.createdDate = LocalDateTime.now()
        article.lastModified = article.createdDate

        // set categories for the article
        article.categories = articleRequest.categories
            .map { categoryRepository.findAllByNameStartsWith(it)[0] }
            .toMutableSet()

        // set teacher for the article
        val fetchedTeacher = appUserRepository.findById(articleRequest.teacherId)
        if (fetchedTeacher.isPresent) {
            article.teacher = fetchedTeacher.get()
        } else throw NoSuchElementException("cannot find teacher with id: ${articleRequest.teacherId}")

        // save all changes
        return articleRepository.save(article).toDto()!!
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

        val articleData = articleRepository.findById(id)

        article.id = id
        article.teacher = appUserRepository.findById(req.teacherId).get()
        article.categories = req.categories
            .map { categoryRepository.findAllByNameStartsWith(it)[0] }
            .toMutableSet()

        article.createdDate = articleData.get().createdDate
        article.lastModified = LocalDateTime.now()
        return articleRepository.save(article).toDto()!!
    }

    override fun fetchPublished(page: Int, size: Int): Page<ArticleDto> {
        return articleRepository.findAllPublishedArticles(PageRequest.of(page, size)).map { it.toDto() }
    }

    override fun addComment(id: UUID, commentRequest: CommentRequest): CommentDto {
        val article = articleRepository.findById(id)
        if (article.isPresent) {
            val comment = commentRequest.toEntity()
            comment.article = article.get()
            val savedComment = commentRepository.save(comment)
            val fetchedArticle = article.get()
            fetchedArticle.comments.add(savedComment)
            articleRepository.save(fetchedArticle)
            return savedComment.toDto()
        } else throw NoSuchElementException("cannot find article $id")
    }

    override fun fetchCommentsOf(articleId: UUID): List<CommentDto> {
        val article = articleRepository.findById(articleId)
        if (article.isPresent) {
            val comments = commentRepository.findAllByArticle(article.get())
            return comments.map { it.toDto() }
        } else throw NoSuchElementException("cannot find article $articleId")
    }


}