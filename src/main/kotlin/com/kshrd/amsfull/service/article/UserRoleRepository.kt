package com.kshrd.amsfull.service.article

import com.kshrd.amsfull.model.entity.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRoleRepository : JpaRepository<UserRole, Long> {

    fun findByRoleName(roleName: String): Optional<UserRole>
}