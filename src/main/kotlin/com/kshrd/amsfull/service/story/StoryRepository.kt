package com.kshrd.amsfull.service.story

import com.kshrd.amsfull.model.entity.Story
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface StoryRepository : JpaRepository<Story, Long> {

    @Query(
        nativeQuery = true,
        value = "select * from story s where s.time_expires > :now"
    )
    fun findAllActiveStories(pageable: Pageable, now: LocalDateTime): Page<Story>
}