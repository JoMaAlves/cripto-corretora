package school.cesar.criptocorretora.services

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.builders.CriptoBuilder
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.excecoes.CriptoInvalidaException
import school.cesar.criptocorretora.repositories.CriptoRepository
import school.cesar.criptocorretora.validadores.CriptoValidador
import java.math.BigDecimal

internal class CriptoServiceTest {
    private val criptoBuilderMock = mockk<CriptoBuilder>()
    private val criptoValidadorMock = mockk<CriptoValidador>()
    private val criptoRepositoryMock = mockk<CriptoRepository>()
    private val cripto = mockk<Cripto>()


    private val criptoService = CriptoService(criptoBuilderMock, criptoValidadorMock, criptoRepositoryMock)

    @Test
    fun `deve adicionar validar e adicionar usuario`() {
        every { criptoBuilderMock.buildCripto("DogeCoin", BigDecimal(4356)) } returns cripto
        justRun { criptoValidadorMock.valida(cripto) }
        justRun { criptoRepositoryMock.add(cripto) }

        assertDoesNotThrow { criptoService.add("DogeCoin", BigDecimal(4356)) }
    }

    @Test
    fun `deve lancar erro quando cripto nao existir`(){
        every { criptoRepositoryMock.buscarPeloId(555) } returns null

        assertThrows<CriptoInvalidaException> {
            criptoService.buscarPorId(555)
        }.also {
            assertEquals("Id não existente", it.message)
        }
    }

    @Test
    fun `deve buscar usuario`(){
        every { criptoRepositoryMock.buscarPeloId(555) } returns cripto

        assertDoesNotThrow { criptoService.buscarPorId(555) }
    }
}