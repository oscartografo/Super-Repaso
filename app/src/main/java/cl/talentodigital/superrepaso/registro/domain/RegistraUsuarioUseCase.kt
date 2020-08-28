package cl.talentodigital.superrepaso.registro.domain

class RegistraUsuarioUseCase (
    private val registroRepository: RegistroRepository

) {
    suspend fun  execute (registroUsuario: RegistroUsuario) : Boolean{
        return registroRepository.registrarUsuario(registroUsuario)
    }
}
