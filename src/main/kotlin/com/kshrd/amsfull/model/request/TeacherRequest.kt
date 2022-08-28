package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.model.entity.Teacher
import java.io.Serializable

data class TeacherRequest(val name: String) : Serializable {
    fun toEntity() = Teacher(
        _name = name
    )
}
