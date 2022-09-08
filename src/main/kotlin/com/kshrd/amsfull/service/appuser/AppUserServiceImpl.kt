package com.kshrd.amsfull.service.appuser

import com.kshrd.amsfull.model.dto.AppUserDto
import com.kshrd.amsfull.model.request.AppUserRequest
import com.kshrd.amsfull.service.article.UserRoleRepository
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
        val teacher = appUserRequest.toEntity()
        val validRoles = appUserRequest.roles.map { userRoleRepository.findByRoleName(it).get() }.toMutableSet()
        teacher.userRoles = validRoles
        return appUserRepository.save(teacher).toDto()!!
    }

    override fun fetchBy(page: Int, size: Int): Page<AppUserDto> {
        return appUserRepository.findAll(PageRequest.of(page, size)).map { it.toDto() }
    }

    override fun findById(id: UUID): AppUserDto {
        val teacher = appUserRepository.findById(id)
        return if (teacher.isPresent) {
            teacher.get().toDto()!!
        } else throw NoSuchElementException("teacher not found: id = $id")
    }

    override fun deleteById(id: UUID) {
        val teacher = appUserRepository.findById(id)
        if (teacher.isPresent) {
            appUserRepository.deleteById(id)
        }
    }

    override fun update(id: UUID, appUserRequest: AppUserRequest): AppUserDto {
        val teacher = appUserRequest.toEntity()
        teacher.id = id
        return appUserRepository.save(teacher).toDto()!!
    }


}