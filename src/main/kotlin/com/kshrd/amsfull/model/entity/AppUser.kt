package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.dto.AppUserDto
import java.util.*
import jakarta.persistence.*

@Entity
@Table(
    name = "app_users",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_contact_information", columnNames = ["email", "telephone"]
        ),
        UniqueConstraint(
            name = "uk_username", columnNames = ["username"]
        )
    ],
)
class AppUser() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "username", nullable = false)
    var username: String? = null

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "user_bookmarked_articles",
        joinColumns = [JoinColumn(name = "app_user_id", foreignKey = ForeignKey(name = "fk_user_id"))],
        inverseJoinColumns = [JoinColumn(name = "bookmarked_article_id", foreignKey = ForeignKey(name = "fk_article_id"))]
    )
    var bookmarkedArticles: MutableList<Article> = mutableListOf()

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "app_user_id", foreignKey = ForeignKey(name = "fk_user_id"))],
        inverseJoinColumns = [JoinColumn(name = "user_role_id", foreignKey = ForeignKey(name = "fk_role_id"))]
    )
    var userRoles: MutableSet<UserRole> = mutableSetOf()

    @Column(name = "email")
    var email: String? = null

    @Column(name = "profile_picture_url", nullable = false, columnDefinition = "TEXT")
    var profile: String? = null

    @Column(name = "telephone")
    var telephone: String? = null

    constructor(username: String?, email: String?, profile: String?, telephone: String?) : this() {
        this.username = username
        this.email = email
        this.profile = profile
        this.telephone = telephone
    }

    fun toDto() = username?.let {
        AppUserDto(
            id = id!!,
            username = it,
            email = email!!,
            profile = profile!!,
            telephone = telephone!!,
            roles = userRoles.map { role -> role.roleName }.toSet(),
        )
    }

}