package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.model.entity.Category
import java.io.Serializable

data class CategoryRequest(val name: String) : Serializable {

    fun toEntity() = Category(
        name = this.name
    )
}
