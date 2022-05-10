package school.cesar.criptocorretora.repositories

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.entidades.Usuario
import java.math.BigDecimal

class CriptoRepositoryTest {

    private val criptoRepository = CriptoRepository()
    private val cripto = Cripto(123123123, "DogeCoin", BigDecimal(12345))

    @Test
    fun `deve trazer null quando o id de cripto for inexistente`() {
        Assertions.assertEquals(null, criptoRepository.buscarPeloId(123))
    }

    @Test
    fun `deve trazer null quando o nome de cripto for inexistente`() {
        assertEquals(null, criptoRepository.buscarPeloNome("Redstone"))
    }

    @Test
    fun `deve adicionar e trazer cripto por id`(){
        criptoRepository.add(cripto)
        Assertions.assertEquals(cripto, criptoRepository.buscarPeloId(123123123))
    }

    @Test
    fun `deve adicionar e trazer cripto por nome`(){
        criptoRepository.add(cripto)
        Assertions.assertEquals(cripto, criptoRepository.buscarPeloNome("DogeCoin"))
    }

    @Test
    fun `deve remover um usuario`(){
        criptoRepository.add(cripto)
        criptoRepository.excluir(cripto)
        assertEquals(null, criptoRepository.buscarPeloNome("DogeCoin"))
    }
}