package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.dto.AppUserDto
import com.kshrd.amsfull.model.enum.UserRole
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "app_users")
open class AppUser(_name: String, _role: UserRole) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @Column(name = "name", nullable = false, unique = true)
    open var name: String? = _name

    @Enumerated(value = EnumType.STRING)
    open var role: UserRole = _role

    @OneToMany(mappedBy = "appUser", cascade = [CascadeType.ALL], orphanRemoval = true)
    open var articles: MutableSet<Article> = mutableSetOf()

    fun toDto() = name?.let {
        AppUserDto(
            id = id!!,
            name = it,
            role = role
        )
    }

}