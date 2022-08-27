package com.kshrd.amsfull.controller

import com.kshrd.amsfull.model.response.ApiResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception) =
        ApiResponse.Failure(
            message = e.localizedMessage,
            status = "400"
        )
}