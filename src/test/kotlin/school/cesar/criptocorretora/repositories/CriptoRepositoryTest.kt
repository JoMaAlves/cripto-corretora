package school.cesar.criptocorretora.repositories

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.entidades.Usuario
import java.math.BigDecimal

class CriptoRepositoryTest {

    private val criptoRepository = CriptoRepository()
    private val cripto = Cripto(555, "DogeCoin", BigDecimal(12345))

    @Test
    fun `deve trazer null quando o id do cripto for inexistente`() {
        assertNull(criptoRepository.buscarPeloId(123))
    }

    @Test
    fun `deve trazer null quando o nome do cripto for inexistente`() {
        assertNull(criptoRepository.buscarPeloNome("Redstone"))
    }

    @Test
    fun `deve trazer null quando o id do cripto for inexistente e o repositorio populado`() {
        criptoRepository.add(cripto)
        assertNull(criptoRepository.buscarPeloId(123))
    }

    @Test
    fun `deve trazer null quando o nome do cripto for inexistente e o repositorio populado`() {
        criptoRepository.add(cripto)
        assertNull(criptoRepository.buscarPeloNome("Redstone"))
    }

    @Test
    fun `deve adicionar e trazer cripto por id`(){
        criptoRepository.add(cripto)
        assertEquals(cripto, criptoRepository.buscarPeloId(555))
    }

    @Test
    fun `deve adicionar e trazer cripto por nome`(){
        criptoRepository.add(cripto)
        assertEquals(cripto, criptoRepository.buscarPeloNome("DogeCoin"))
    }

    @Test
    fun `deve remover um cripto`(){
        criptoRepository.add(cripto)
        criptoRepository.excluir(cripto)
        assertNull( criptoRepository.buscarPeloNome("DogeCoin"))
    }
}