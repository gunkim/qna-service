package io.github.gunkim.question.application.port.`in`

import io.github.gunkim.question.domain.Answer

interface FindAnswerUseCase {
    fun findAnswers(questionId: Long): List<Answer>
    fun findAnswer(answerId: Long): Answer
}