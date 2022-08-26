package com.kshrd.amsfull.service.teacher;

import com.kshrd.amsfull.model.entity.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TeacherRepository : JpaRepository<Teacher, UUID> {
}