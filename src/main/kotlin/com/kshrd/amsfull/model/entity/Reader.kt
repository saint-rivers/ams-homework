package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.AppUser
import javax.persistence.*

@Entity
@Table(name = "readers")
open class Reader : AppUser() {
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "readers_article_bookmarks",
        joinColumns = [JoinColumn(name = "reader_id")],
        inverseJoinColumns = [JoinColumn(name = "article_id")]
    )
    open var articles: MutableSet<Article> = mutableSetOf()
}