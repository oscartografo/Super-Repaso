package cl.talentodigital.superrepaso.registro.domain

interface RegistroRepository {
    suspend fun registrarUsuario(registroUsuario: RegistroUsuario): Boolean
}