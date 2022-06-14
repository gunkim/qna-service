package io.github.gunkim.question.application.port.`in`

fun interface DeleteQuestionUseCase {
    fun deleteQuestion(questionId: Long)
}