package school.cesar.criptocorretora.services

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.excecoes.UsuarioInvalidoException
import school.cesar.criptocorretora.repositories.UsuarioRepository
import school.cesar.criptocorretora.validadores.UsuarioValidator

class UsuarioServiceTest {
    private val usuarioValidatorMock = mockk<UsuarioValidator>()
    private val usuarioRepositoryMock = mockk<UsuarioRepository>()
    private val usuario = Usuario(123123, "17154989092", "John Doe", "johndoe@test.com", "Abcd12345", Carteira())


    private val usuarioService = UsuarioService(usuarioValidatorMock, usuarioRepositoryMock)

    @Test
    fun `deve adicionar validar e adicionar usuario`() {
        justRun { usuarioValidatorMock.valida(usuario) }
        justRun { usuarioRepositoryMock.add(usuario) }

        assertDoesNotThrow { usuarioService.adicionar(usuario) }
    }

    @Test
    fun `deve lancar erro quando usuario nao existir`(){
        every { usuarioRepositoryMock.buscarPorId(555) } returns null

        assertThrows<UsuarioInvalidoException> {
            usuarioService.buscarPorId(555)
        }.also {
            Assertions.assertEquals("Id Não encontrado", it.message)
        }
    }

    @Test
    fun `deve buscar usuario`(){
        every { usuarioRepositoryMock.buscarPorId(123123) } returns usuario

        assertDoesNotThrow { usuarioService.buscarPorId(123123) }
    }
}