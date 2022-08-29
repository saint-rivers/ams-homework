package com.kshrd.amsfull.service.article

import com.kshrd.amsfull.model.dto.ArticleDto
import com.kshrd.amsfull.model.dto.CommentDto
import com.kshrd.amsfull.model.request.ArticleRequest
import com.kshrd.amsfull.model.request.CommentRequest
import com.kshrd.amsfull.service.category.CategoryRepository
import com.kshrd.amsfull.service.teacher.TeacherRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArticleServiceImpl(
    val articleRepository: ArticleRepository,
    val categoryRepository: CategoryRepository,
    val commentRepository: CommentRepository,
    val teacherRepository: TeacherRepository
) : ArticleService {
    override fun createArticle(article: ArticleRequest): ArticleDto {
        val articleReq = article.toEntity()
        articleReq.categories = article.categories
            .map { categoryRepository.findAllByNameStartsWith(it)[0] }
            .toMutableSet()

        val fetchedTeacher = teacherRepository.findById(article.teacherId)
        if (fetchedTeacher.isPresent) {
            articleReq.teacher = fetchedTeacher.get()
        } else throw NoSuchElementException("cannot find teacher with id: ${article.teacherId}")
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