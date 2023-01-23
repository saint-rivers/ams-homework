package com.kshrd.amsfull.controller

import com.kshrd.amsfull.model.dto.AppUserDto
import com.kshrd.amsfull.model.entity.Story
import com.kshrd.amsfull.model.response.ApiResponse
import com.kshrd.amsfull.service.story.StoryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.UUID

@RestController
@RequestMapping("/api/v1/stories")
class StoryController(val storyService: StoryService) {

    data class StoryRequest(val userId: UUID, val storyImage: String, val caption: String) {
        fun toEntity() = Story(
            storyImage = this.storyImage,
            caption = this.caption,
            timePosted = LocalDateTime.now(),
            isExpired = false
        )
    }

    data class StoryDto(
        val id: Long,
        val storyImage: String,
        val caption: String,
        val postedAt: LocalDateTime,
        val expiresAt: LocalDateTime,
        val storyOwner: AppUserDto?
    )

    @PostMapping
    fun insert(@RequestBody req: StoryRequest): ApiResponse.SuccessWithPayload {
        val story: StoryDto = storyService.createStory(req)
        return ApiResponse.SuccessWithPayload(
            message = "successfully fetched records", status = HttpStatus.OK.name, payload = story
        )
    }

    @PutMapping("/{id}")
    fun update(@RequestBody req: StoryRequest, @PathVariable id: Long): ApiResponse.SuccessWithPayload {
        val story: StoryDto = storyService.update(id, req)
        return ApiResponse.SuccessWithPayload(
            message = "successfully updated record", status = HttpStatus.OK.name, payload = story
        )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ApiResponse {
        val stories = storyService.delete(id)
        return ApiResponse.SuccessWithPayload(
            message = "successfully deleted record", status = HttpStatus.OK.name, payload = stories
        )
    }

    @GetMapping("/active")
    fun getActiveStories(
        @RequestParam("page", defaultValue = "1") page: Int,
        @RequestParam("size", defaultValue = "5") size: Int
    ): ApiResponse {
        val stories = storyService.getActiveStories(page, size)
        return ApiResponse.SuccessWithPage(
            message = "successfully fetched records", status = HttpStatus.OK.name, payload = stories.get(),
            page = page,
            size = size,
            totalElements = stories.totalElements,
            totalPages = stories.totalPages
        )
    }

    @GetMapping
    fun getAll(
        @RequestParam("page", defaultValue = "1") page: Int,
        @RequestParam("size", defaultValue = "5") size: Int
    ): ApiResponse {

        val stories = storyService.getAll(page, size)
        return ApiResponse.SuccessWithPage(
            message = "successfully fetched records", status = HttpStatus.OK.name, payload = stories.get().toList(),
            page = page,
            size = size,
            totalElements = stories.totalElements,
            totalPages = stories.totalPages
        )
    }
}