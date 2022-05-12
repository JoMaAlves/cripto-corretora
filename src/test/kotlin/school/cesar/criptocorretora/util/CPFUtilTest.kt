package school.cesar.criptocorretora.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CPFUtilTest {
    private val cpfUtil = CPFUtil();

    @Test
    fun `deve limpar cpf`() {
        assertEquals("17154989092", cpfUtil.limpaCPF("171.549.890-92"))
    }

    @Test
    fun `deve retornar false se cpf for vazio`() {
        assertFalse(cpfUtil.isCPF(""))
    }

    @Test
    fun `deve retornar false se tamanho de cpf for diferente de 11`() {
        assertFalse(cpfUtil.isCPF("123"))
    }

    @Test
    fun `deve retornar false se os numeros forem repetidos`() {
        assertFalse(cpfUtil.isCPF("11111111111"))
    }

    @Test
    fun `deve retornar false se os digitos finais forem diferente do esperado`() {
        assertFalse(cpfUtil.isCPF("11122233344"))
    }

    @Test
    fun `deve retornar cpf valido`() {
        assertTrue(cpfUtil.isCPF("171.549.890-92"))
    }
}