package school.cesar.criptocorretora.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EmailUtilTest {
    private val emailUtil = EmailUtil();

    @Test
    fun `deve retornar true quando email for valido`() {
        assertEquals(true, emailUtil.isEmailValido("johndoe@test.com"))
    }

    @Test
    fun `deve retornar false quando email for valido`() {
        assertEquals(false, emailUtil.isEmailValido("janedoe"))
    }
}