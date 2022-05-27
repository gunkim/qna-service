package io.github.gunkim.question.domain

import io.github.gunkim.function.hashCodeOf
import java.util.regex.Pattern

class User private constructor(
    val username: String,
    val password: String,
    val ip: String
) {
    companion object {
        //0.0.0.0 ~ 255.255.255.255
        private const val IP_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"

        operator fun invoke(username: String, password: String, ip: String): User {
            return User(
                username.takeIf { it.length in 2..10 }
                    ?: throw IllegalArgumentException("사용자 이름은 2글자 이상 10글자 이하여야 합니다."),
                password.takeIf { it.length in 8..30 }
                    ?: throw IllegalArgumentException("비밀번호는 8글자 이상 30글자 이하여야 합니다."),
                ip.takeIf { Pattern.matches(IP_PATTERN, ip) }
                    ?: throw IllegalArgumentException("ip 형식이 맞지 않습니다.")
            )
        }
    }

    override fun equals(other: Any?): Boolean = this === other ||
            other is User &&
            username == other.username &&
            password == other.password &&
            ip == other.ip

    override fun hashCode(): Int = hashCodeOf(username, password, ip)
}