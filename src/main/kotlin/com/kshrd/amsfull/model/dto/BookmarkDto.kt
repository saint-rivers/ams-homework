package com.kshrd.amsfull.model.dto

import java.util.UUID

data class BookmarkDto(val articleId: UUID, val title: String, val description: String, val teacher: AppUserDto)
