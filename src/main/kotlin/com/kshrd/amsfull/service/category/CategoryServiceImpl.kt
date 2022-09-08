package com.kshrd.amsfull.service.category

import com.kshrd.amsfull.model.dto.CategoryDto
import com.kshrd.amsfull.model.request.CategoryRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(val categoryRepository: CategoryRepository) : CategoryService {
    override fun create(categoryRequest: CategoryRequest): CategoryDto {
        return categoryRepository.save(categoryRequest.toEntity()).toDto()
    }

    override fun findById(id: Long): CategoryDto? {
        return categoryRepository.findById(id).orElseThrow().toDto()
    }

    override fun findAll(page: Int, size: Int): Page<CategoryDto> {
        val res = categoryRepository.findAll(PageRequest.of(page, size))
        return res.map { it.toDto() }
    }

    override fun delete(id: Long) {
        categoryRepository.deleteById(id)
    }

    override fun findManyByName(name: String, page: Int, size: Int): Page<CategoryDto> {
        return categoryRepository
            .findAllByNameStartsWith(name, PageRequest.of(page, size))
            .map { it.toDto() }
    }

    override fun update(id: Long, categoryRequest: CategoryRequest): CategoryDto {
        val category = categoryRequest.toEntity()
        return categoryRepository.save(category).toDto()
    }

}