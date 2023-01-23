package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.model.entity.Comment
import java.io.Serializable

data class CommentRequest(val caption: String) : Serializable {
    fun toEntity(): Comment = Comment(
        caption = caption
    )

}
