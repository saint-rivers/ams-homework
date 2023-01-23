package com.kshrd.amsfull.service.story

import com.kshrd.amsfull.controller.StoryController
import com.kshrd.amsfull.service.appuser.AppUserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.util.*

@Service
class StoryServiceImpl(val storyRepository: StoryRepository, val appUserRepository: AppUserRepository) : StoryService {
    override fun createStory(req: StoryController.StoryRequest): StoryController.StoryDto {
        val user = appUserRepository.findById(req.userId).orElseThrow {
            RuntimeException("unable to find user with id " + req.userId)
        }
        val toEntity = req.toEntity()
        toEntity.storyOwner = user
        return storyRepository.save(toEntity).toDto()
    }

    override fun getAll(page: Int, size: Int): Page<StoryController.StoryDto> {
        // jpa starts counting from page 0
        // zero-based index
        return storyRepository.findAll(PageRequest.of(page - 1, size)).map { it.toDto() }
    }

    override fun update(id: Long, req: StoryController.StoryRequest): StoryController.StoryDto {
        val story = storyRepository.findById(id).orElseThrow {
            RuntimeException("unable to find user with id " + req.userId)
        }
        val newStory = req.toEntity()
        newStory.id = story.id
        return storyRepository.save(newStory).toDto()
    }

    override fun delete(id: Long): StoryController.StoryDto {
        val story = storyRepository.findById(id).orElseThrow {
            RuntimeException("unable to find user with id $id")
        }
        return story.toDto()
    }

    override fun getActiveStories(page: Int, size: Int): Page<StoryController.StoryDto> {
        // jpa starts counting from page 0
        // zero-based index
        return storyRepository.findAllActiveStories(
            PageRequest.of(page - 1, size), LocalDateTime.now()
        ).map { it.toDto() }
    }

    override fun getStoriesByUserId(userId: UUID, requesterId: UUID): Page<StoryController.StoryDto> {
        TODO("Not yet implemented")
    }
}
