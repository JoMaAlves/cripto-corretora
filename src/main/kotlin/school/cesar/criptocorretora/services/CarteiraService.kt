package school.cesar.criptocorretora.services

import java.math.BigDecimal

class CarteiraService(
    private val usuarioService: UsuarioService,
    private val criptoService: CriptoService
) {

    fun comprar(idUsuario: Long, idCripto: Long, valor: BigDecimal) {
        val usuario = usuarioService.buscarPorId(idUsuario)
        val cripto = criptoService.buscarPorId(idCripto)

        val quantidadeCripto = valor / cripto.cotacaoAtualEmDollar
        usuario.carteira.cripto[cripto] = usuario.carteira.cripto[cripto]
            ?.plus(quantidadeCripto) ?: quantidadeCripto
    }

    fun consultarValoresAgrupados(idUsuario: Long) =
        usuarioService.buscarPorId(idUsuario)
            .carteira.cripto
}
