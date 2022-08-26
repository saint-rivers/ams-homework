package com.kshrd.amsfull.model

import java.util.*
import javax.persistence.*

@MappedSuperclass
open class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @Column(name = "name", nullable = false, unique = true)
    open var name: String? = null
}