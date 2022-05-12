package school.cesar.criptocorretora.services

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class DollarServiceTest {
    private var dollarService = DollarService()

    @Test
    fun `deve retornar um big decimal entre 4 e 5`() {
        assertTrue(
            BigDecimal(4) <= dollarService.pegarValorDollarAtual()
            || BigDecimal(5) >= dollarService.pegarValorDollarAtual()
        )
    }
}