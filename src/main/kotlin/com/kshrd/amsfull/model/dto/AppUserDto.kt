package com.kshrd.amsfull.model.dto

import java.io.Serializable
import java.util.*

data class AppUserDto(val id: UUID, val name: String, val roles: Set<String>) : Serializable
