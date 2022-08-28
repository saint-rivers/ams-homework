package com.kshrd.amsfull.model

import java.util.*
import javax.persistence.*

@MappedSuperclass
open class AppUser(_name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @Column(name = "name", nullable = false, unique = true)
    open var name: String? = _name
}