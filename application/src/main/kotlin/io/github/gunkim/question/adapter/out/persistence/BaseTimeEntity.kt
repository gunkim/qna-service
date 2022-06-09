package io.github.gunkim.question.adapter.out.persistence

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
open class BaseTimeEntity(
    @CreatedDate
    var createdAt: LocalDateTime,
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
)