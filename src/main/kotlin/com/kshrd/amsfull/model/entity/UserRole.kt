package com.kshrd.amsfull.model.entity

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "roles")
open class UserRole(roleName: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "role_name", nullable = false, unique = true, length = 100)
    open var roleName: String? = roleName

//    @ManyToMany(mappedBy = "userRoles", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
//    open var appUsers: MutableSet<AppUser> = mutableSetOf()
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
//        other as UserRole
//
//        return id != null && id == other.id
//    }

    override fun hashCode(): Int = javaClass.hashCode()
}