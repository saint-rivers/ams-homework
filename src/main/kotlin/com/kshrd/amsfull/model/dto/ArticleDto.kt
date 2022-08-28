package com.kshrd.amsfull.model.dto

import java.io.Serializable
import java.util.*

data class ArticleDto(
    val id: UUID,
    val title: String,
    val description: String? = null,
    val isPublished: Boolean = false,
    var categories: Set<CategoryDto> = emptySet(),
    var teacher: TeacherDto
) : Serializable
