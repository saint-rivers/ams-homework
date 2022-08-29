package com.kshrd.amsfull.service.teacher

import com.kshrd.amsfull.model.entity.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AppUserRepository : JpaRepository<AppUser, UUID> {
}