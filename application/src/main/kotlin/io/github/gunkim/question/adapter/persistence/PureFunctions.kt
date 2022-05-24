package io.github.gunkim.question.adapter.persistence

fun hashCodeOf(vararg values: Any?): Int = values.fold(0) { acc, value -> (acc * 31) + value.hashCode() }