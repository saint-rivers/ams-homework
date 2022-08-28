package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.AppUser
import com.kshrd.amsfull.model.dto.TeacherDto
import javax.persistence.*

@Entity
@Table(name = "teachers")
open class Teacher(_name: String) : AppUser(_name) {
    @OneToMany(mappedBy = "teacher", cascade = [CascadeType.ALL], orphanRemoval = true)
    open var articles: MutableSet<Article> = mutableSetOf()

    fun toDto() = name?.let {
        TeacherDto(
            id = id!!,
            name = it,
        )
    }

}