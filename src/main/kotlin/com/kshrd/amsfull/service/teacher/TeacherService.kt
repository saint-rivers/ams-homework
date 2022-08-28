package com.kshrd.amsfull.service.teacher

import com.kshrd.amsfull.model.dto.TeacherDto
import com.kshrd.amsfull.model.request.TeacherRequest
import org.springframework.data.domain.Page
import java.util.*

interface TeacherService {
    fun create(teacherRequest: TeacherRequest): TeacherDto
    fun fetchBy(page: Int, size: Int): Page<TeacherDto>
    fun findById(id: UUID): TeacherDto
    fun deleteById(id: UUID)
    fun update(id: UUID, teacherRequest: TeacherRequest): TeacherDto
}