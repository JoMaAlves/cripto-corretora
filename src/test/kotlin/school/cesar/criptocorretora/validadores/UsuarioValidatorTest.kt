package school.cesar.criptocorretora.validadores

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.excecoes.UsuarioInvalidoException
import school.cesar.criptocorretora.util.CPFUtil
import school.cesar.criptocorretora.util.EmailUtil
import school.cesar.criptocorretora.util.SenhaUtil

class UsuarioValidatorTest {

    private val cpfUtilMock = mockk<CPFUtil>()
    private val emailUtilMock = mockk<EmailUtil>()
    private val senhaUtilMock = mockk<SenhaUtil>()

    private val usuarioValidator = UsuarioValidator(cpfUtilMock, emailUtilMock, senhaUtilMock)
    private val usuario = Usuario(
        123123123,
        "17154989092",
        "John Doe",
        "johndoe@test.com",
        "Abcd12345",
        Carteira()
    )

    @Test
    fun `deve lancar excecao quando o nome for vazio`() {
        assertThrows<UsuarioInvalidoException> {
            usuarioValidator.valida(usuario.copy(nome = ""))
        }.also {
            Assertions.assertEquals("O nome deve ser preenchido", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando o cpf for vazio`() {
        assertThrows<UsuarioInvalidoException> {
            usuarioValidator.valida(usuario.copy(cpf = ""))
        }.also {
            Assertions.assertEquals("O cpf deve ser preenchido", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando o email for vazio`() {
        assertThrows<UsuarioInvalidoException> {
            usuarioValidator.valida(usuario.copy(email = ""))
        }.also {
            Assertions.assertEquals("O e-mail deve ser preenchido", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando a senha for vazio`() {
        assertThrows<UsuarioInvalidoException> {
            usuarioValidator.valida(usuario.copy(senha = ""))
        }.also {
            Assertions.assertEquals("O senha deve ser preenchido", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando o nome for muito longo`() {
        assertThrows<UsuarioInvalidoException> {
            usuarioValidator.valida(usuario.copy(nome = "A".repeat(201)))
        }.also {
            Assertions.assertEquals("O campo nome deve ter menos de 200 caracteres", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando o cpf tiver tamanho invalido`() {
        assertThrows<UsuarioInvalidoException> {
            usuarioValidator.valida(usuario.copy(cpf = "cpfCurto"))
        }.also {
            Assertions.assertEquals("O campo cpf deve ter 11 caracteres numericos", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando a senha tiver tamanho menor que o valido`() {
        assertThrows<UsuarioInvalidoException> {
            usuarioValidator.valida(usuario.copy(senha = "abc123"))
        }.also {
            Assertions.assertEquals("O campo confirma????o senha deve ter entre 8 e 15 caracteres", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando a senha tiver tamanho maior que o valido`() {
        assertThrows<UsuarioInvalidoException> {
            usuarioValidator.valida(usuario.copy(senha = "a".repeat(16)))
        }.also {
            Assertions.assertEquals("O campo confirma????o senha deve ter entre 8 e 15 caracteres", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando formato do cpf for invalido`() {
        every { cpfUtilMock.isCPF("11122233344") } returns false

        assertThrows<UsuarioInvalidoException> {
            usuarioValidator.valida(usuario.copy(cpf = "11122233344"))
        }.also {
            Assertions.assertEquals("O cpf ?? invalido", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando formato do email for invalido`() {
        every { cpfUtilMock.isCPF("17154989092") } returns true
        every { emailUtilMock.isEmailValido("emailErrado") } returns false

        assertThrows<UsuarioInvalidoException> {
            usuarioValidator.valida(usuario.copy(email = "emailErrado"))
        }.also {
            Assertions.assertEquals("O a emal deve seguir o formato palavra@palavra.palavra", it.message)
        }
    }

    @Test
    fun `deve lancar excecao quando formato da senha for invalido`() {
        every { cpfUtilMock.isCPF("17154989092") } returns true
        every { emailUtilMock.isEmailValido("johndoe@test.com") } returns true
        every { senhaUtilMock.isFormatoOK("abcd1234") } returns false

        assertThrows<UsuarioInvalidoException> {
            usuarioValidator.valida(usuario.copy(senha = "abcd1234"))
        }.also {
            Assertions.assertEquals("O a senha deve conter numeros, letras maisculas e minusculas", it.message)
        }
    }

    @Test
    fun `deve validar usuario`() {
        every { cpfUtilMock.isCPF("17154989092") } returns true
        every { emailUtilMock.isEmailValido("johndoe@test.com") } returns true
        every { senhaUtilMock.isFormatoOK("Abcd12345") } returns true

        assertDoesNotThrow {
            usuarioValidator.valida(usuario)
        }
    }
}