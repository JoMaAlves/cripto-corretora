package school.cesar.criptocorretora.entidades

import java.math.BigDecimal

data class Carteira(
    val cripto: MutableMap<Cripto, BigDecimal> = mutableMapOf()
)
