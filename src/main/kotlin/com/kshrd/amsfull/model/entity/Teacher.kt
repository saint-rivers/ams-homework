package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.AppUser
import javax.persistence.*

@Entity
@Table(name = "teachers")
open class Teacher: AppUser() {
    @ManyToOne(cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    open var article: Article? = null
}