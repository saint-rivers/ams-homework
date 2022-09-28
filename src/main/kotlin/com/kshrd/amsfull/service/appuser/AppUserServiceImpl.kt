package com.kshrd.amsfull.service.appuser

import com.kshrd.amsfull.exception.user.UserAlreadyExistsException
import com.kshrd.amsfull.exception.user.UserNotFoundException
import com.kshrd.amsfull.model.dto.AppUserDto
import com.kshrd.amsfull.model.request.AppUserRequest
import com.kshrd.amsfull.service.article.UserRoleRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class AppUserServiceImpl(
    val appUserRepository: AppUserRepository,
    val userRoleRepository: UserRoleRepository
) : AppUserService {
    override fun create(appUserRequest: AppUserRequest): AppUserDto {

        appUserRequest.validate()
        val teacher = appUserRequest.toEntity()

        if (appUserRepository.findAll(Example.of(teacher)).size > 0) throw UserAlreadyExistsException()

        val validRoles = appUserRequest.roles.map { userRoleRepository.findByRoleName(it).get() }.toMutableSet()
        teacher.userRoles = validRoles
        return appUserRepository.save(teacher).toDto()!!
    }

    override fun fetchBy(page: Int, size: Int): Page<AppUserDto> {
        return appUserRepository.findAll(PageRequest.of(page, size)).map { it.toDto() }
    }

    override fun findById(id: UUID): AppUserDto {
        val teacher = appUserRepository.findById(id)
        if (teacher.isEmpty) throw UserNotFoundException()
        return teacher.get().toDto()!!
    }

    override fun deleteById(id: UUID) {
        val teacher = appUserRepository.findById(id)
        if (teacher.isEmpty) throw UserNotFoundException()
        appUserRepository.deleteById(id)
    }

    override fun update(id: UUID, appUserRequest: AppUserRequest): AppUserDto {

        val existingUser = appUserRepository.findById(id)
        if (existingUser.isEmpty) throw UserNotFoundException()

        appUserRequest.validate()
        val teacher = appUserRequest.toEntity()
        teacher.id = id

        return appUserRepository.save(teacher).toDto()!!
    }


}