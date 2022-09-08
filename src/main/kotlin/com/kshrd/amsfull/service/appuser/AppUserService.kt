package com.kshrd.amsfull.service.appuser

import com.kshrd.amsfull.model.dto.AppUserDto
import com.kshrd.amsfull.model.request.AppUserRequest
import org.springframework.data.domain.Page
import java.util.*

interface AppUserService {
    fun create(appUserRequest: AppUserRequest): AppUserDto
    fun fetchBy(page: Int, size: Int): Page<AppUserDto>
    fun findById(id: UUID): AppUserDto
    fun deleteById(id: UUID)
    fun update(id: UUID, appUserRequest: AppUserRequest): AppUserDto
}