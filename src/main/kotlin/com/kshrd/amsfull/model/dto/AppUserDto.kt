package com.kshrd.amsfull.model.dto

import java.io.Serializable
import java.util.*

data class AppUserDto(
    val id: UUID,
    val username: String,
    val email: String,
    val profile: String,
    val telephone: String,
    val roles: Set<String>
) : Serializable
