package io.github.gunkim.question.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface AnswerJpaEntityRepository : JpaRepository<AnswerJpaEntity, Long>