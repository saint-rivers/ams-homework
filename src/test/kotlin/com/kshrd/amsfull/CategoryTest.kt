package com.kshrd.amsfull

import com.kshrd.amsfull.service.category.CategoryRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataJpaTest
@ExtendWith(SpringExtension::class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CategoryTest(@Autowired val categoryRepository: CategoryRepository) {

    @Test
    fun `can fetch by name`() {
        val res = categoryRepository.findAllByNameStartsWith("string", PageRequest.of(0, 5))
        println(res)
//        assertThat(res.content.size).isEqualTo(3)
    }
}