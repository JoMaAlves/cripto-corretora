package school.cesar.criptocorretora.repositories

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Usuario

class UsuarioRepositoryTest {
    private val usuarioRepository = UsuarioRepository()
    private val usuario = Usuario(
        123123123,
        "17154989092",
        "John Doe",
        "johndoe@test.com",
        "Abcd12345",
        Carteira()
    )

    @Test
    fun `deve trazer null quando usuario for inexistente`(){
        assertEquals(null, usuarioRepository.buscarPorId(123))
    }

    @Test
    fun `deve adicionar e trazer usuario`(){
        usuarioRepository.add(usuario)
        assertEquals(usuario, usuarioRepository.buscarPorId(123123123))
    }

}