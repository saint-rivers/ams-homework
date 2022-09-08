package com.kshrd.amsfull.controller

import com.kshrd.amsfull.model.response.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception) = ResponseEntity.internalServerError().body(
        ApiResponse.Failure(
            message = e.localizedMessage, status = "500"
        )
    )

    @ExceptionHandler(value = [IllegalStateException::class])
    fun handleIllegalStateExceptionException(e: Exception) = ResponseEntity.badRequest().body(
        ApiResponse.Failure(
            message = e.localizedMessage, status = "400"
        )
    )

    @ExceptionHandler(value = [NoSuchElementException::class])
    fun handleNoSuchElementException(e: Exception) = ResponseEntity.badRequest().body(
        ApiResponse.Failure(
            message = e.localizedMessage, status = "206"
        )
    )
}