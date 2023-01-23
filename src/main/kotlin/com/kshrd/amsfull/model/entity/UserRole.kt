package com.kshrd.amsfull.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "roles", uniqueConstraints = [UniqueConstraint(name = "uk_role_name", columnNames = ["role_name"])])
class UserRole() {

    constructor(id: Long?, roleName: String) : this() {
        this.id = id
        this.roleName = roleName
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "role_name", nullable = false, length = 100)
    lateinit var roleName: String

}