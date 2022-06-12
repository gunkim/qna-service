package io.github.gunkim.question.adapter.`in`.web

import com.fasterxml.jackson.annotation.JsonFormat
import io.github.gunkim.common.COMMON_DATETIME_FORMAT
import io.github.gunkim.question.application.port.`in`.FindQuestionUseCase
import io.github.gunkim.question.domain.Question
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class FindQuestionsController(private val useCase: FindQuestionUseCase) {
    @GetMapping("/api/v1/questions")
    fun findQuestions(): List<FindQuestionsResponse> {
        return useCase.findQuestions().map(::convertResponse)
    }

    private fun convertResponse(question: Question): FindQuestionsResponse {
        return question.let(::FindQuestionsResponse)
    }
}

data class FindQuestionsResponse(
    val id: Long,
    val categoryName: String,
    val tagNames: List<String>,
    val content: String,
    @get:JsonFormat(pattern = COMMON_DATETIME_FORMAT)
    val createdDatetime: LocalDateTime
) {
    constructor(question: Question) : this(
        id = question.id!!,
        categoryName = question.categoryName,
        tagNames = question.tagNames,
        content = question.content,
        createdDatetime = question.createdAt
    )
}