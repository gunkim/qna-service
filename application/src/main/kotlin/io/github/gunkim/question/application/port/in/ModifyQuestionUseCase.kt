package io.github.gunkim.question.application.port.`in`

import io.github.gunkim.question.domain.Category
import io.github.gunkim.question.domain.Tag

fun interface ModifyQuestionUseCase {
    fun modifyQuestion(
        questionId: Long,
        category: Category,
        tags: MutableSet<Tag>,
        content: String
    )
}