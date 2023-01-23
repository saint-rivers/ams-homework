package com.kshrd.amsfull

import com.kshrd.amsfull.model.entity.UserRole
import com.kshrd.amsfull.service.article.UserRoleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class AmsFullApplication

fun main(args: Array<String>) {
    runApplication<AmsFullApplication>(*args)
}

@Component
class ApplicationStart(val userRoleRepository: UserRoleRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val roles = listOf(
            com.kshrd.amsfull.model.enum.UserRole.READER,
            com.kshrd.amsfull.model.enum.UserRole.TEACHER
        )
        roles.map {
            if (userRoleRepository.findByRoleName(it.name).isEmpty) {
                val role = UserRole(null, it.name)
                userRoleRepository.save(role)
            }
        }
    }

}