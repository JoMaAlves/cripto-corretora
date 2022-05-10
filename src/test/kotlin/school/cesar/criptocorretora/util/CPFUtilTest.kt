package school.cesar.criptocorretora.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CPFUtilTest {
    private val cpfUtil = CPFUtil();

    @Test
    fun `deve retornar cpf sem simbolos`() {
        assertEquals("17154989092", cpfUtil.limpaCPF("171.549.890-92"))
    }
}