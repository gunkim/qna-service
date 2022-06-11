package io.github.gunkim.question.application.port.out

import io.github.gunkim.question.domain.Question

fun interface LoadQuestionPort {
    fun loadQuestion(questionId: Long): Question
}