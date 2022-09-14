package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.exception.InvalidUriException
import com.kshrd.amsfull.model.entity.Category
import com.kshrd.amsfull.service.validator.isUrl
import io.swagger.v3.oas.annotations.Hidden
import java.io.Serializable

data class CategoryRequest(val name: String, val imageUrl: String) : Serializable {

    fun toEntity(): Category =
        Category(
            name = this.name,
            imageUrl = this.imageUrl
        )

    @Hidden
    internal fun validate() {
        isValid()
    }

    private fun isValid(): Boolean {
        if (!imageUrl.isUrl()) throw InvalidUriException()
        return true
    }
}
