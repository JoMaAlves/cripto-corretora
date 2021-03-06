package school.cesar.criptocorretora.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SenhaUtilTest {
    private val senhaUtil = SenhaUtil();

    @Test
    fun `deve retornar false se senha nao contem letra minuscula`() {
        assertFalse(senhaUtil.isFormatoOK("ABC123"))
    }

    @Test
    fun `deve retornar false se senha nao contem letra maiuscula`() {
        assertFalse(senhaUtil.isFormatoOK("abc123"))
    }

    @Test
    fun `deve retornar false se senha nao contem numeros`() {
        assertFalse(senhaUtil.isFormatoOK("abcDEF"))
    }

    @Test
    fun `deve retornar senha valida`() {
        assertTrue(senhaUtil.isFormatoOK("Abcd12345"))
    }

    @Test
    fun `deve codificar senha`() {
        assertEquals("e8d95a51f3af4a3b134bf6bb680a213a", senhaUtil.md5("senha"))
    }
}