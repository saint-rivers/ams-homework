package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.exception.InvalidRoleException
import com.kshrd.amsfull.model.entity.AppUser
import com.kshrd.amsfull.model.enum.UserRole
import java.io.Serializable

data class AppUserRequest(
    val name: String,
    val roles: MutableSet<String> = mutableSetOf()
) : Serializable {
    fun toEntity() = AppUser(
        _name = name,
        _roles = try {
            roles.map { UserRole.valueOf(it) }
        } catch (e: Exception) {
            throw InvalidRoleException(
                "valid roles are ${UserRole.READER} and ${UserRole.TEACHER}"
            )
        }
    )
}
