package school.cesar.criptocorretora.validadores

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.excecoes.CriptoInvalidaException
import java.math.BigDecimal

class CriptoValidadorTest {

    private val criptoValidador = CriptoValidador()
    private val cripto = Cripto(123123123, "Doge Coin", BigDecimal(12345))

    @Test
    fun `deve validar campos obrigatorios`() {
        assertDoesNotThrow {
            criptoValidador.valida(cripto)
        }
    }

    @Test
    fun `deve lancar excecao quando nome for vazio`() {
        assertThrows<CriptoInvalidaException> {
            criptoValidador.valida(cripto.copy(nome = ""))
        }.also {
            assertEquals("O campo nome deve ser preenchido", it.message)
        }
    }
}