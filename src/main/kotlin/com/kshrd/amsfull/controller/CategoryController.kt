package com.kshrd.amsfull.controller

import com.kshrd.amsfull.model.request.CategoryRequest
import com.kshrd.amsfull.model.response.ApiResponse
import com.kshrd.amsfull.service.category.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.webjars.NotFoundException
import java.lang.IllegalStateException

@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(val categoryService: CategoryService) {

    @PostMapping
    fun createCategory(@RequestBody categoryRequest: CategoryRequest) =
        ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "",
                status = "200",
                payload = categoryService.create(categoryRequest)
            )
        )

    @GetMapping
    fun fetch(
        @RequestParam("page", defaultValue = "1") page: Int,
        @RequestParam("size", defaultValue = "5") size: Int
    ): ResponseEntity<Any> {

        if (page < 1) throw IllegalStateException("page cannot be smaller than 1")
        if (size < 1) throw IllegalStateException("size cannot be smaller than 1")

        val payload = categoryService.findAll(page - 1, size)

        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPage(
                message = "successfully fetched categories",
                status = "200",
                payload = payload.content,
                page = page,
                size = payload.numberOfElements,
                totalElements = payload.totalElements,
                totalPages = payload.totalPages
            )
        )
    }

    @GetMapping("/name")
    fun fetch(
        @RequestParam("name", defaultValue = "") name: String,
        @RequestParam("page", defaultValue = "1") page: Int,
        @RequestParam("size", defaultValue = "5") size: Int
    ): ResponseEntity<Any> {

        if (page < 1) throw IllegalStateException("page cannot be smaller than 1")
        if (size < 1) throw IllegalStateException("size cannot be smaller than 1")

        val payload = categoryService.findManyByName(name, page - 1, size)

        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPage(
                message = "successfully fetched categories",
                status = "200",
                payload = payload.content,
                page = page,
                size = payload.numberOfElements,
                totalElements = payload.totalElements,
                totalPages = payload.totalPages
            )
        )
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: Long): ResponseEntity<ApiResponse> {
        val payload = categoryService.findById(id)
        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "fetched category with id: $id",
                status = "200",
                payload = payload ?: throw NotFoundException("cannot find category with id: $id")
            )
        )
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody categoryRequest: CategoryRequest): ResponseEntity<ApiResponse> {
        val payload = categoryService.update(id, categoryRequest)

        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "fetched category with id: $id",
                status = "200",
                payload = payload
            )
        )
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Long): ResponseEntity<ApiResponse> {
        categoryService.delete(id)
        return ResponseEntity.ok().body(
            ApiResponse.Success(
                message = "deleted category with id: $id",
                status = "200"
            )
        )
    }

}