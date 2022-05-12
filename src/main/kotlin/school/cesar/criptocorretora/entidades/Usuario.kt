package school.cesar.criptocorretora.entidades

data class Usuario(
    val id: Long,
    val cpf: String,
    val nome: String,
    val email: String,
    val senha: String,
    val carteira: Carteira
)
