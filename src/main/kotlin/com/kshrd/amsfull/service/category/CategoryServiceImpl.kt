package com.kshrd.amsfull.service.category

import com.kshrd.amsfull.exception.common.CommonNotFoundException
import com.kshrd.amsfull.exception.common.CommonResourceAlreadyExistsException
import com.kshrd.amsfull.model.dto.CategoryDto
import com.kshrd.amsfull.model.entity.Category
import com.kshrd.amsfull.model.request.CategoryRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(val categoryRepository: CategoryRepository) : CategoryService {

    private fun fetchExistingCategory(id: Long): Category {
        val category = categoryRepository.findById(id)
        if (category.isEmpty) throw CommonNotFoundException(_resourceName = "category")
        return category.get()
    }

    private fun fetchExistingCategoriesByName(name: String): List<Category> {
        val existingCategories = categoryRepository.findAllByNameStartsWith(name)
        if (!existingCategories.isEmpty())
            throw CommonResourceAlreadyExistsException(_resourceName = "category name")
        return existingCategories
    }

    override fun create(categoryRequest: CategoryRequest): CategoryDto {
        categoryRequest.validate()
        fetchExistingCategoriesByName(categoryRequest.name)
        return categoryRepository.save(categoryRequest.toEntity()).toDto()
    }

    override fun findById(id: Long): CategoryDto? {
        val category = fetchExistingCategory(id)
        return category.toDto()
    }

    override fun findAll(page: Int, size: Int): Page<CategoryDto> {
        val res = categoryRepository.findAll(PageRequest.of(page, size))
        return res.map { it.toDto() }
    }

    override fun delete(id: Long) {
        fetchExistingCategory(id)
        categoryRepository.deleteById(id)
    }

    override fun findManyByName(name: String, page: Int, size: Int): Page<CategoryDto> {
        return categoryRepository
            .findAllByNameStartsWith(name, PageRequest.of(page, size))
            .map { it.toDto() }
    }

    override fun update(id: Long, categoryRequest: CategoryRequest): CategoryDto {
        fetchExistingCategory(id)
        fetchExistingCategoriesByName(categoryRequest.name)

        val category = categoryRequest.toEntity()
        return categoryRepository.save(category).toDto()
    }

}