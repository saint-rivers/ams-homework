package com.kshrd.amsfull.controller

import com.kshrd.amsfull.model.request.TeacherRequest
import com.kshrd.amsfull.model.response.ApiResponse
import com.kshrd.amsfull.service.teacher.TeacherService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/teachers")
class TeacherController(val teacherService: TeacherService) {

    @PostMapping
    fun create(@RequestBody teacherRequest: TeacherRequest) =
        ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "",
                status = "201",
                payload = teacherService.create(teacherRequest)
            )
        )

    @GetMapping
    fun fetch(
        @RequestParam("page", defaultValue = "1") page: Int,
        @RequestParam("size", defaultValue = "5") size: Int
    ): ResponseEntity<ApiResponse> {
        if (page < 1) throw IllegalStateException("page cannot be smaller than 1")
        if (size < 1) throw IllegalStateException("size cannot be smaller than 1")

        val payload = teacherService.fetchBy(page - 1, size)
        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPage(
                message = "successfully fetched teachers",
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
    fun find(@PathVariable id: UUID): ResponseEntity<ApiResponse> {
        return ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "teacher found",
                status = "200",
                payload = teacherService.findById(id)
            )
        )
    }

    @PutMapping("/{id}")
    fun update(@RequestBody teacherRequest: TeacherRequest, @PathVariable id: UUID) =
        ResponseEntity.ok().body(
            ApiResponse.SuccessWithPayload(
                message = "updated teacher successfully",
                status = "200",
                payload = teacherService.update(id, teacherRequest)
            )
        )

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: UUID): ResponseEntity<ApiResponse> {
        teacherService.deleteById(id)
        return ResponseEntity.ok().body(
            ApiResponse.Success(
                message = "deleted article with id: $id",
                status = "200",
            )
        )
    }
}