package io.github.gunkim.question.adapter.`in`.web.docs

import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields

class FindQuestionsDocumentation {
    companion object {
        fun findQuestions(): RestDocumentationResultHandler {
            val response = arrayListOf<FieldDescriptor>(
                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("질문 ID"),
                fieldWithPath("[].categoryName").type(JsonFieldType.STRING).description("카테고리 이름"),
                fieldWithPath("[].tagNames").type(JsonFieldType.ARRAY).description("태그 목록"),
                fieldWithPath("[].content").type(JsonFieldType.STRING).description("질문 내용"),
                fieldWithPath("[].createdDatetime").type(JsonFieldType.STRING).description("질문 작성일자"),

                )
            return document(
                "question/findQuestions",
                responseFields(response)
            )
        }
    }
}