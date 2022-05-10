package school.cesar.criptocorretora.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SenhaUtilTest {
    private val senhaUtil = SenhaUtil();

    @Test
    fun `deve codificar senha`() {
        assertEquals("e8d95a51f3af4a3b134bf6bb680a213a", senhaUtil.md5("senha"))
    }
}