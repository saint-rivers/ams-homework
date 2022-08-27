package com.kshrd.amsfull.model.dto

import java.io.Serializable
import java.util.*

data class ReaderDto(
    val id: UUID,
    val name: String
) : Serializable
