package io.github.gunkim.question.application.port.`in`

import io.github.gunkim.question.domain.Question

fun interface FindQuestionsUseCase {
    fun findQuestions(): List<Question>
}