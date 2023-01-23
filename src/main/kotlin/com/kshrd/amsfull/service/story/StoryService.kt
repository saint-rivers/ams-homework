package com.kshrd.amsfull.service.story

import com.kshrd.amsfull.controller.StoryController
import org.springframework.data.domain.Page
import java.util.UUID

interface StoryService {
    fun createStory(req: StoryController.StoryRequest): StoryController.StoryDto
    fun update(id: Long, req: StoryController.StoryRequest): StoryController.StoryDto
    fun delete(id: Long): StoryController.StoryDto
    fun getAll(page: Int, size: Int): Page<StoryController.StoryDto>
    fun getActiveStories(page: Int, size: Int): Page<StoryController.StoryDto>
    fun getStoriesByUserId(userId: UUID, requesterId: UUID): Page<StoryController.StoryDto>
}