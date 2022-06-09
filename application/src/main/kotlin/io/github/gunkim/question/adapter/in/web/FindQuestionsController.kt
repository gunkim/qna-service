package io.github.gunkim.question.adapter.`in`.web

import com.fasterxml.jackson.annotation.JsonFormat
import io.github.gunkim.common.COMMON_DATETIME_FORMAT
import io.github.gunkim.question.application.port.`in`.FindQuestionsUseCase
import io.github.gunkim.question.domain.Question
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

interface FindQuestionsController {
    @GetMapping("/api/v1/questions")
    fun findQuestions(): List<FindQuestionsResponse>
}

@RestController
internal class FindQuestionsControllerImpl(
    private val findQuestionsUseCase: FindQuestionsUseCase
) : FindQuestionsController {
    override fun findQuestions(): List<FindQuestionsResponse> {
        return findQuestionsUseCase.findQuestions()
            .asSequence()
            .map(::convertResponse)
            .toList()
    }

    private fun convertResponse(question: Question): FindQuestionsResponse {
        return question.let {
            FindQuestionsResponse(
                id = it.id!!,
                categoryName = it.getCategoryName(),
                tagNames = it.getTagNames(),
                content = it.content,
                createdDatetime = it.createdAt
            )
        }
    }
}

class FindQuestionsResponse(
    val id: Long,
    val categoryName: String,
    val tagNames: List<String>,
    val content: String,
    @get:JsonFormat(pattern = COMMON_DATETIME_FORMAT)
    val createdDatetime: LocalDateTime
)