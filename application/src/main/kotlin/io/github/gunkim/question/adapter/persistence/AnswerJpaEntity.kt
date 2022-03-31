package io.github.gunkim.question.adapter.persistence

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "answer")
class AnswerJpaEntity(
    @Id
    private var id: Long? = null,
    private var questionId: Long,
    private var content: String,
    private val username: String,
    private val password: String,
    private val ip: String,
    @CreatedDate
    private val createdDatetime: LocalDateTime,
    @LastModifiedDate
    private var updatedDatetime: LocalDateTime
)