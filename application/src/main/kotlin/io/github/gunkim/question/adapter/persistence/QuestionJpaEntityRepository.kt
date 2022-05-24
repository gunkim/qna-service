package io.github.gunkim.question.adapter.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface QuestionJpaEntityRepository : JpaRepository<QuestionJpaEntity, Long>