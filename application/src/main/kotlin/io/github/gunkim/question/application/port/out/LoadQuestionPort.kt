package io.github.gunkim.question.application.port.out

import io.github.gunkim.question.domain.Question

interface LoadQuestionPort {
    fun loadQuestion(questionId: Long): Question
    fun loadQuestion(): List<Question>
}