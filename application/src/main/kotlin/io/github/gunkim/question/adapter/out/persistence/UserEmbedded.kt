package io.github.gunkim.question.adapter.out.persistence

import io.github.gunkim.function.hashCodeOf
import javax.persistence.Embeddable

@Embeddable
class UserEmbedded(
    val username: String,
    val password: String,
    val ip: String
) {
    override fun equals(other: Any?): Boolean = this === other ||
            other is UserEmbedded &&
            username == other.username &&
            password == other.password &&
            ip == other.ip

    override fun hashCode(): Int = hashCodeOf(username, password, ip)
}