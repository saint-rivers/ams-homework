package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.model.entity.AppUser
import com.kshrd.amsfull.model.enum.UserRole
import java.io.Serializable

data class AppUserRequest(val name: String, val role: UserRole = UserRole.READER) : Serializable {
    fun toEntity() = AppUser(
        _name = name,
        _role = role
    )
}
