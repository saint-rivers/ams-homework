package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.controller.StoryController
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "story")
open class Story() {
    constructor(timePosted: LocalDateTime?, storyImage: String?, isExpired: Boolean?, caption: String?):this() {
        this.timePosted = timePosted
        this.storyImage = storyImage
        this.isExpired = isExpired
        this.caption = caption
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "time_posted", nullable = false)
    var timePosted: LocalDateTime? = null
        set(value) {
            field = value
            if (value != null) {
                this.timeExpires = value.plusDays(1)
            }
        }

    @Column(name = "time_expires", nullable = false)
    var timeExpires: LocalDateTime? = null

    @Column(name = "story_image", nullable = false)
    var storyImage: String? = null
        protected set

    @Column(name = "is_expired", nullable = false)
    var isExpired: Boolean? = false

    @ManyToOne(cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "story_owner_id", nullable = false)
    var storyOwner: AppUser? = null

    @Column(name = "caption", length = 1000)
    var caption: String? = null

//    @ManyToMany
//    @JoinTable(
//        name = "story_viewer",
//        joinColumns = [JoinColumn(name = "story_id")],
//        inverseJoinColumns = [JoinColumn(name = "viewer_id")]
//    )
//    var viewers: MutableSet<AppUser> = mutableSetOf()

    fun toDto() = StoryController.StoryDto(
        id = id!!,
        storyImage = storyImage!!,
        caption = caption!!,
        postedAt = timePosted!!,
        expiresAt =  timeExpires !!,
        storyOwner = storyOwner!!.toDto()
    )
}