package com.kshrd.amsfull.controller

import com.kshrd.amsfull.model.response.ApiResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception) =
        ApiResponse.Failure(
            message = e.localizedMessage,
            status = "400"
        )
}