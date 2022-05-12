package school.cesar.criptocorretora.builders

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import school.cesar.criptocorretora.entidades.Cripto
import java.math.BigDecimal

internal class CriptoBuilderTest {
    private val criptoBuilder = CriptoBuilder();
    private val cripto = Cripto(0, "DogeCoin", BigDecimal(4564))

    @Test
    fun `deve criar cripto`() {
        assertEquals(cripto,criptoBuilder.buildCripto("DogeCoin", BigDecimal(4564)))
    }
}