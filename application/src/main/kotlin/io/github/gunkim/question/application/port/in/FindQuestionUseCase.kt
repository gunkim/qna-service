package io.github.gunkim.question.application.port.`in`

import io.github.gunkim.question.domain.Question

interface FindQuestionUseCase {
    fun findQuestions(): List<Question>
    fun findQuestion(questionId: Long): Question
}