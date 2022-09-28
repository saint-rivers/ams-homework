package com.kshrd.amsfull.service.category

import com.kshrd.amsfull.model.entity.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {

    fun findAllByNameStartsWith(name: String, pageable: Pageable): Page<Category>
    fun findAllByNameStartsWith(name: String): List<Category>
    fun findByNameContainingIgnoreCase(name: String): Optional<Category>
}