package io.github.gunkim.question.adapter.persistence

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class TagJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    private val questionId: Long,
    private val name: String,
    @CreatedDate
    private val createdDatetime: LocalDateTime,
    @LastModifiedDate
    private var updatedDatetime: LocalDateTime
)