package school.cesar.criptocorretora.services

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.entidades.Usuario
import java.math.BigDecimal

internal class CarteiraServiceTest {
    private val usuarioServiceMock = mockk<UsuarioService>()
    private val criptoServiceMock = mockk<CriptoService>()
    private val cripto = Cripto(123, "DogeCoin", BigDecimal(4356))
    private val usuario = Usuario(
        123,
        "17154989092",
        "John",
        "johndoe@test.com",
        "Abcd12345",
        Carteira()
    )


    private val carteiraService = CarteiraService(usuarioServiceMock, criptoServiceMock)

    @Test
    fun `deve comprar uma cripto inexistente na carteira`() {
        every { usuarioServiceMock.buscarPorId(123) } returns usuario
        every { criptoServiceMock.buscarPorId(123) } returns cripto

        carteiraService.comprar(123, 123, BigDecimal(4356))

        assertEquals(BigDecimal(1), usuario.carteira.cripto[cripto])
    }

    @Test
    fun `deve comprar uma cripto ja existente na carteira`() {
        every { usuarioServiceMock.buscarPorId(123) } returns usuario
        every { criptoServiceMock.buscarPorId(123) } returns cripto

        carteiraService.comprar(123, 123, BigDecimal(4356))
        carteiraService.comprar(123, 123, BigDecimal(8712))

        assertEquals(BigDecimal(3), usuario.carteira.cripto[cripto])
    }

    @Test
    fun `deve consultar valores agrupados`() {
        every { usuarioServiceMock.buscarPorId(123).carteira.cripto } returns usuario.carteira.cripto

        assertNotNull(carteiraService.consultarValoresAgrupados(123))
    }
}