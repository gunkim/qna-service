package io.github.gunkim.question.domain

import io.github.gunkim.function.hashCodeOf

class User private constructor(
    val username: String,
    val password: String,
    val ip: String
) {
    companion object {
        //0.0.0.0 ~ 255.255.255.255
        private val IP_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$".toRegex()

        operator fun invoke(username: String, password: String, ip: String): User {
            require(username.length in 2..10) {
                "사용자 이름은 2글자 이상 10글자 이하여야 합니다."
            }

            require(password.length in 8..30) {
                "비밀번호는 8글자 이상 30글자 이하여야 합니다."
            }

            require(IP_PATTERN.matches(ip)) {
                "ip 형식이 맞지 않습니다."
            }

            return User(username, password, ip)
        }
    }

    override fun equals(other: Any?): Boolean = this === other ||
            other is User &&
            username == other.username &&
            password == other.password &&
            ip == other.ip

    override fun hashCode(): Int = hashCodeOf(username, password, ip)
}