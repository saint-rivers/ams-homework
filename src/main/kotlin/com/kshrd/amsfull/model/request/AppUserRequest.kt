package com.kshrd.amsfull.model.request

import com.kshrd.amsfull.exception.InvalidRoleException
import com.kshrd.amsfull.model.entity.AppUser
import com.kshrd.amsfull.model.enum.UserRole
import com.kshrd.amsfull.service.validator.isEmail
import com.kshrd.amsfull.service.validator.isUrl
import com.kshrd.amsfull.service.validator.isUserName
import io.swagger.v3.oas.annotations.Hidden
import java.io.Serializable
import java.lang.IllegalStateException

data class AppUserRequest(
    val username: String,
    val email: String,
    val profile: String,
    val telephone: String,
    val roles: MutableSet<String> = mutableSetOf()
) : Serializable {
//    fun toEntity(): AppUser {
//        return if (isValid()) AppUser(
//            username = username,
//            email = email,
//            profile = profile,
//            telephone = telephone,
//        ) else throw DefaultException()
//    }

    fun toEntity() = AppUser(
        username = username,
        email = email,
        profile = profile,
        telephone = telephone,
    )

    private fun containsValidUserRoles(): Boolean {
        val data: List<UserRole>
        try {
            data = roles.map { UserRole.valueOf(it) }
            return data.isNotEmpty()
        } catch (e: Exception) {
            throw InvalidRoleException(
                "valid roles are ${UserRole.READER} and ${UserRole.TEACHER}"
            )
        }
    }
    internal fun validate() {
        isValid()
    }

    @Hidden
    private fun isValid(): Boolean {
        if (!username.isUserName()) throw IllegalStateException("username must not contain spaces or special characters")
        if (!email.isEmail()) throw IllegalStateException("invalid email")
        if (!profile.isUrl()) throw IllegalStateException("invalid profile picture url")
//        if (!telephone.isTelephone()) throw IllegalStateException("invalid phone number")
        containsValidUserRoles()

        return true
    }
}
