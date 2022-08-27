package com.kshrd.amsfull.service.category

import com.kshrd.amsfull.model.dto.CategoryDto
import com.kshrd.amsfull.model.request.CategoryRequest
import org.springframework.data.domain.Page

interface CategoryService {
    fun create(categoryRequest: CategoryRequest): CategoryDto

    fun findById(id: Long): CategoryDto?

    fun findAll(page: Int, size: Int): Page<CategoryDto>

    fun delete(id: Long)

    fun findManyByName(name: String, page: Int, size: Int): Page<CategoryDto>

    fun update(id: Long, categoryRequest: CategoryRequest): CategoryDto
}