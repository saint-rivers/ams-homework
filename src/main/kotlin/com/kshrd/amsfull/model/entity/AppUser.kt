package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.dto.AppUserDto
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "app_users")
open class AppUser(username: String, email: String, profile: String, telephone: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @Column(name = "username", nullable = false, unique = true)
    open var username: String? = username

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
    @JoinTable(
        name = "user_bookmarked_articles",
        joinColumns = [JoinColumn(name = "app_user_id")],
        inverseJoinColumns = [JoinColumn(name = "bookmarked_article_id")]
    )
    open var bookmarkedArticles: MutableSet<Article> = mutableSetOf()

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "app_user_id")],
        inverseJoinColumns = [JoinColumn(name = "user_role_id")]
    )
    open var userRoles: MutableSet<UserRole> = mutableSetOf()

    @Column(name = "email", unique = true)
    open var email: String? = email

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "profile_picture_url", nullable = false)
    open var profile: String? = profile

    @Column(name = "telephone", unique = true)
    open var telephone: String? = telephone

    fun toDto() = username?.let {
        AppUserDto(
            id = id!!,
            username = it,
            email = email!!,
            profile = profile!!,
            telephone = telephone!!,
            roles = userRoles.mapNotNull { role -> role.roleName }.toSet(),
        )
    }

}