package com.kshrd.amsfull.service.teacher

import com.kshrd.amsfull.model.dto.TeacherDto
import com.kshrd.amsfull.model.request.TeacherRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class TeacherServiceImpl(val teacherRepository: TeacherRepository) : TeacherService {
    override fun create(teacherRequest: TeacherRequest): TeacherDto {
        val teacher = teacherRequest.toEntity()
        return teacherRepository.save(teacher).toDto()!!
    }

    override fun fetchBy(page: Int, size: Int): Page<TeacherDto> {
        return teacherRepository.findAll(PageRequest.of(page, size)).map { it.toDto() }
    }

    override fun findById(id: UUID): TeacherDto {
        val teacher = teacherRepository.findById(id)
        return if (teacher.isPresent) {
            teacher.get().toDto()!!
        } else throw NoSuchElementException("teacher not found: id = $id")
    }

    override fun deleteById(id: UUID) {
        val teacher = teacherRepository.findById(id)
        if (teacher.isPresent) {
            teacherRepository.deleteById(id)
        }
    }

    override fun update(id: UUID, teacherRequest: TeacherRequest): TeacherDto {
        val teacher = teacherRequest.toEntity()
        teacher.id = id
        return teacherRepository.save(teacher).toDto()!!
    }


}