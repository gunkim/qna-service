package io.github.gunkim.question.adapter.out.persistence.user

import io.github.gunkim.function.hashCodeOf
import io.github.gunkim.question.domain.User
import javax.persistence.Embeddable

@Embeddable
class UserEmbedded(
    val username: String,
    val password: String,
    val ip: String
) {
    fun convertToDomain(): User {
        return User(
            username = username,
            password = password,
            ip = ip
        )
    }

    override fun equals(other: Any?): Boolean = this === other ||
        other is UserEmbedded &&
        username == other.username &&
        password == other.password &&
        ip == other.ip

    override fun hashCode(): Int = hashCodeOf(username, password, ip)
}