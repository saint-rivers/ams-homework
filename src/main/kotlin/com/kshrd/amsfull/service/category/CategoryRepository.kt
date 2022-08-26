package com.kshrd.amsfull.service.category;

import com.kshrd.amsfull.model.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
}