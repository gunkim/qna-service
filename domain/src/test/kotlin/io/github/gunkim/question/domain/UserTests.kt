package io.github.gunkim.question.domain

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("유저 도메인 테스트")
class UserTests {
    @ParameterizedTest
    @ValueSource(strings = ["", "홍", "홍길동길동홍길동길동홍"])
    fun `이름이 2글자 미만, 10글자를 초과할 경우 인스턴스 생성에 실패한다`(name: String) {
        assertThrows<IllegalArgumentException>("사용자 이름은 2글자 이상 10글자 이하여야 합니다.")
        { User(name, "12341234", "127.0.0.1") }
    }

    @ParameterizedTest
    @ValueSource(strings = ["홍길동", "홍길동홍", "홍길동길동홍길동길동", "홍길동길동홍길동길동"])
    fun `이름이 3글자 이상이고 10글자 이하일 경우 인스턴스 생성에 성공한다`(name: String) {
        assertDoesNotThrow { User(name, "12341234", "127.0.0.1") }
    }

    @ParameterizedTest
    @ValueSource(strings = ["12345678", "123456789", "12345678912345678912345678912", "123456789123456789123456789123"])
    fun `비밀번호가 8글자 이상이고 30글자 이하일 경우 인스턴스 생성에 성공한다`(password: String) {
        assertDoesNotThrow { User("홍길동", password, "127.0.0.1") }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1234567", "1234567891234567891234567891234"])
    fun `비밀번호가 8글자 미만, 30글자를 초과할 경우 인스턴스 생성에 실패한다`(password: String) {
        assertThrows<IllegalArgumentException>("비밀번호는 8글자 이상 30글자 이하여야 합니다.")
        { User("홍길동", password, "127.0.0.1") }
    }

    @ParameterizedTest
    @ValueSource(strings = ["127", "127.0", "127.0.0", "-1.-1.-1.-1", "256.256.256.256"])
    fun `아이피 주소 형식이 맞지 않으면 인스턴스 생성에 실패한다`(ip: String) {
        assertThrows<IllegalArgumentException>("ip 형식이 맞지 않습니다.")
        { User("홍길동", "12341234", ip) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0.0.0.0", "255.255.255.255"])
    fun `아이피 주소 형식이 맞으면 인스턴스 생성에 성공한다`(ip: String) {
        assertDoesNotThrow { User("홍길동", "12341234", ip) }
    }
}