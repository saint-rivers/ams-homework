package com.kshrd.amsfull.controller

import com.kshrd.amsfull.exception.ArticleAlreadyPublishedException
import com.kshrd.amsfull.exception.GeneralNotFoundException
import com.kshrd.amsfull.model.response.ApiResponse
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.webjars.NotFoundException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [DuplicateKeyException::class])
    fun handleUserExistsException(e: Exception) = ResponseEntity.badRequest().body(
        ApiResponse.Failure(
            message = "${e.localizedMessage}. please check your request body.", status = "400"
        )
    )

    @ExceptionHandler(value = [IllegalStateException::class])
    fun handleIllegalStateExceptionException(e: Exception) = ResponseEntity.badRequest().body(
        ApiResponse.Failure(
            message = e.localizedMessage, status = "400"
        )
    )

    @ExceptionHandler(value = [NotFoundException::class, GeneralNotFoundException::class])
    fun handleNotFoundException(e: Exception) = ResponseEntity.badRequest().body(
        ApiResponse.Failure(
            message = e.localizedMessage, status = "400"
        )
    )

    @ExceptionHandler(value = [NoSuchElementException::class])
    fun handleNoSuchElementException(e: Exception) = ResponseEntity.badRequest().body(
        ApiResponse.Failure(
            message = e.localizedMessage, status = "400"
        )
    )

    @ExceptionHandler(value = [ArticleAlreadyPublishedException::class])
    fun handleArticleAlreadyPublishedException(e: Exception) = ResponseEntity.badRequest().body(
        ApiResponse.Failure(
            message = e.localizedMessage, status = "400"
        )
    )

    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception) = ResponseEntity.internalServerError().body(
        ApiResponse.Failure(
            message = e.localizedMessage, status = "500"
        )
    )
}