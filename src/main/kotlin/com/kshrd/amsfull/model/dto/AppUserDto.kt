package com.kshrd.amsfull.model.dto

import com.kshrd.amsfull.model.enum.UserRole
import java.io.Serializable
import java.util.*

data class AppUserDto(val id: UUID, val name: String, val role: UserRole) : Serializable
